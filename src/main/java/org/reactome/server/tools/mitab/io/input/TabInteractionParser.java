package org.reactome.server.tools.mitab.io.input;

import org.reactome.server.tools.mitab.io.model.ColumnName;
import org.reactome.server.tools.mitab.io.model.Field;
import org.reactome.server.tools.mitab.io.model.Interaction;
import org.reactome.server.tools.mitab.io.model.PsiMitabVersion;

import java.util.*;
import java.util.stream.Collectors;

public class TabInteractionParser {

	private static final String COLUMN_SEPARATOR = "\t";
	private static final String FIELD_SEPARATOR = "|";
	private static final Map<PsiMitabVersion, TabInteractionParser> parsers = new TreeMap<>();
	private static final String EMPTY = "-";
	private final int numberOfColumns;

	private TabInteractionParser(int numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
	}

	public static TabInteractionParser instance(PsiMitabVersion version) {
		return parsers.computeIfAbsent(version, v -> new TabInteractionParser(v.getColumns()));
	}

	/**
	 * Gets the default interaction parser (TAB_27).
	 */
	public static TabInteractionParser instance() {
		return instance(PsiMitabVersion.TAB_27);
	}

	public Interaction toInteraction(String line) {
		final String[] columns = line.split(COLUMN_SEPARATOR);
		final Interaction interaction = new Interaction(numberOfColumns);
		for (int i = 0; i < columns.length; i++) {
			try {
				final List<Field> fields = parseField(columns[i]);
				if (fields != null)
					interaction.set(i, fields);
			} catch (StringIndexOutOfBoundsException ex) {
				throw new RuntimeException(String.format("For column %s, value [%s]", ColumnName.values()[i].getName(), columns[i]), ex);
			}
		}
		return interaction;
	}

	final List<Field> parseField(String field) {
		if (field.equals("-") || field.isEmpty())
			return Collections.emptyList();
		final List<Field> fields = new LinkedList<>();
		int start = 0;
		int pos = start;
		boolean quoted = false;
		while (pos < field.length()) {
			if (field.charAt(pos) == '"' && (pos == 0 || field.charAt(pos - 1) != '\\'))
				quoted = !quoted;
			if (field.charAt(pos) == '|' && !quoted) {
				fields.add(FieldParser.parse(field.substring(start, pos)));
				start = pos + 1;
			}
			pos += 1;
		}
		try {
			fields.add(FieldParser.parse(field.substring(start, pos)));
		} catch (IllegalArgumentException ex) {
			System.err.println("Couldn't parse field: " + field.substring(start, pos) + " in line ");
		}
		return fields;
	}

	public String toString(Interaction interaction) {
		final List<String> stringedColumns = Arrays.stream(ColumnName.values())
				.limit(numberOfColumns)
				.map(interaction::get)
				.map(this::toString)
				.collect(Collectors.toList());
		return String.join(COLUMN_SEPARATOR, stringedColumns);
	}

	private String toString(List<Field> fields) {
		if (fields.isEmpty())
			return EMPTY;
		final List<String> stringed = fields.stream()
				.map(this::toString)
				.collect(Collectors.toList());
		return String.join(FIELD_SEPARATOR, stringed);
	}

	private String toString(Field field) {
		if (field.getIdentifier() == null) {
			return field.getDatabase();
		} else {
			String r = quote(field.getDatabase());
			if (field.getIdentifier() != null)
				r += ":" + quote(field.getIdentifier());
			if (field.getDescription() != null)
				r += "(" + quote(field.getDescription()) + ")";
			return r;
		}
	}

	private String quote(String value) {
		if (mustBeQuoted(value))
			return '"' + value + '"';
		return value;
	}

	private boolean mustBeQuoted(String value) {
		for (String c : Arrays.asList("|", "\t", "(", ")", ":"))
			if (value.contains(c))
				return true;
		return false;
	}


	public String headerLine() {
		return Arrays.stream(ColumnName.values())
				.limit(numberOfColumns)
				.map(ColumnName::getName)
				.collect(Collectors.joining(COLUMN_SEPARATOR));
	}
}
