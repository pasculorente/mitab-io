package org.uichuimi.mitab.io.input;

import org.uichuimi.mitab.io.model.ColumnName;
import org.uichuimi.mitab.io.model.Field;
import org.uichuimi.mitab.io.model.Interaction;
import org.uichuimi.mitab.io.model.PsiMitabVersion;

import java.util.*;
import java.util.stream.Collectors;

public class PsiInteractionParser {

	private static final List<String> RESERVED = Arrays.asList("|", "\t", "(", ")", ":");
	private static final String COLUMN_SEPARATOR = "\t";
	private static final String FIELD_SEPARATOR = "|";
	private static final String EMPTY = "-";
	private static final char QUOTE = '"';

	/**
	 * Map containing single instances for every {@link PsiMitabVersion}. Instances are created only at request through
	 * {@link PsiInteractionParser#instance()} or {@link PsiInteractionParser#instance(PsiMitabVersion)} methods.
	 */
	private static final Map<PsiMitabVersion, PsiInteractionParser> parsers = new TreeMap<>();

	/**
	 * The only difference in MITAB format is the number of columns in the file.
	 */
	private final int numberOfColumns;

	private PsiInteractionParser(int numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
	}

	/**
	 * Get an instance of {@link PsiInteractionParser} for a determined version.
	 *
	 * @param version MITAB version to parse input
	 * @return a {@link PsiInteractionParser} instance
	 */
	public static PsiInteractionParser instance(PsiMitabVersion version) {
		return parsers.computeIfAbsent(version, v -> new PsiInteractionParser(v.getColumns()));
	}

	/**
	 * Gets the default interaction parser (TAB_27).
	 */
	public static PsiInteractionParser instance() {
		return instance(PsiMitabVersion.TAB_27);
	}

	public Interaction toInteraction(String line) {
		final Interaction interaction = new Interaction(numberOfColumns);
		final List<String> columns = splitSafe(line, COLUMN_SEPARATOR);
		for (int i = 0; i < columns.size(); i++) {
			try {
				final List<Field> fields = parseField(columns.get(i));
				if (fields != null)
					interaction.set(i, fields);
			} catch (StringIndexOutOfBoundsException ex) {
				throw new RuntimeException(String.format("For column %s, value [%s]", ColumnName.values()[i].getName(), columns.get(i)), ex);
			}
		}
		return interaction;
	}

	private List<String> splitSafe(String line, String separator) {
		final List<String> rtn = new ArrayList<>();
		int start = 0;
		int pos = start;
		boolean quoted = false;
		while (pos < line.length()) {
			// condition to change context.
			// Only separators out of a quote context are used to split fields.
			// Context is changed every time a quote " is found in the string that is not preceded by a backslash \.
			if (line.charAt(pos) == QUOTE && (pos == 0 || line.charAt(pos - 1) != '\\'))
				quoted = !quoted;
			if (line.substring(pos, pos + separator.length()).equals(separator) && !quoted) {
				rtn.add(line.substring(start, pos));
				start = pos + 1;
			}
			pos += 1;

		}
		rtn.add(line.substring(start));
		return rtn;
	}

	final List<Field> parseField(String field) {
		if (field.equals("-") || field.isEmpty())
			return Collections.emptyList();
		final List<Field> fields = new LinkedList<>();
		final List<String> strings = splitSafe(field, FIELD_SEPARATOR);
		for (String string : strings) {
			fields.add(FieldParser.parse(string));
		}
		return fields;
	}

	public String toString(Interaction interaction) {
		return Arrays.stream(ColumnName.values())
				.limit(numberOfColumns)
				.map(interaction::get)
				.map(this::toString)
				.collect(Collectors.joining(COLUMN_SEPARATOR));
	}

	private String toString(List<Field> fields) {
		if (fields.isEmpty()) return EMPTY;
		return fields.stream()
				.map(this::toString)
				.collect(Collectors.joining(FIELD_SEPARATOR));
	}

	private String toString(Field field) {
		if (field.getValue() == null) {
			return field.getXref();
		} else {
			String r = quote(field.getXref());
			if (field.getValue() != null)
				r += ":" + quote(field.getValue());
			if (field.getDescription() != null)
				r += "(" + quote(field.getDescription()) + ")";
			return r;
		}
	}

	private String quote(String value) {
		if (mustBeQuoted(value))
			return QUOTE + value + QUOTE;
		return value;
	}

	private boolean mustBeQuoted(String value) {
		return RESERVED.stream().anyMatch(value::contains);
	}


	public String headerLine() {
		return Arrays.stream(ColumnName.values())
				.limit(numberOfColumns)
				.map(ColumnName::getName)
				.collect(Collectors.joining(COLUMN_SEPARATOR));
	}
}
