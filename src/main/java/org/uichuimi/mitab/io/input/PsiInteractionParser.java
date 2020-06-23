package org.uichuimi.mitab.io.input;

import org.uichuimi.mitab.io.model.Date;
import org.uichuimi.mitab.io.model.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PsiInteractionParser {

	private static final List<String> RESERVED = Arrays.asList("|", "\t", "(", ")", ":");
	private static final String COLUMN_SEPARATOR = "\t";
	private static final String FIELD_SEPARATOR = "|";
	private static final String EMPTY = "-";
	private static final char QUOTE = '"';

	private static final List<Column> COLUMNS = List.of(
			new Column("ID(s) interactor A",
					(interaction, fields) -> interaction.getInteractorA().setPrimaryIdentifier(transform(Identifier.class, fields)),
					interaction -> interaction.getInteractorA().getPrimaryIdentifier()),
			new Column("ID(s) interactor B",
					(interaction, fields) -> interaction.getInteractorB().setPrimaryIdentifier(transform(Identifier.class, fields)),
					interaction -> interaction.getInteractorB().getPrimaryIdentifier()),
			new Column("Alt. ID(s) interactor A",
					((interaction, fields) -> interaction.getInteractorA().setAlternativeIdentifiers(fields.stream().map(Identifier::new).collect(Collectors.toList()))),
					interaction -> interaction.getInteractorA().getAlternativeIdentifiers()),
			new Column("Alt. ID(s) interactor B",
					((interaction, fields) -> interaction.getInteractorB().setAlternativeIdentifiers(fields.stream().map(Identifier::new).collect(Collectors.toList()))),
					interaction -> interaction.getInteractorB().getAlternativeIdentifiers()),
			new Column("Alias(es) interactor A",
					((interaction, fields) -> interaction.getInteractorA().setAliases(fields.stream().map(Alias::new).collect(Collectors.toList()))),
					interaction -> interaction.getInteractorA().getAliases()),
			new Column("Alias(es) interactor B",
					((interaction, fields) -> interaction.getInteractorB().setAliases(fields.stream().map(Alias::new).collect(Collectors.toList()))),
					interaction -> interaction.getInteractorB().getAliases()),
			new Column("Interaction detection method(s)",
					(interaction, fields) -> interaction.setDetectionMethods(fields.stream().map(DetectionMethod::new).collect(Collectors.toList())),
					Interaction::getDetectionMethods),
			new Column("Publication 1st author(s)",
					(interaction, fields) -> interaction.setAuthors(fields.stream().map(Author::new).collect(Collectors.toList())),
					Interaction::getAuthors),
			new Column("Publication Identifier(s)",
					((interaction, fields) -> interaction.setPublications(fields.stream().map(Publication::new).collect(Collectors.toList()))),
					Interaction::getPublications),
			new Column("Taxid interactor A",
					(interaction, fields) -> interaction.getInteractorA().setOrganisms(fields.stream().map(Organism::new).collect(Collectors.toList())),
					interaction -> interaction.getInteractorA().getOrganisms()),
			new Column("Taxid interactor B",
					(interaction, fields) -> interaction.getInteractorB().setOrganisms(fields.stream().map(Organism::new).collect(Collectors.toList())),
					interaction -> interaction.getInteractorB().getOrganisms()),
			new Column("Interaction type(s)",
					(interaction, fields) -> interaction.setTypes(fields.stream().map(Type::new).collect(Collectors.toList())),
					Interaction::getTypes),
			new Column("Source database(s)",
					(interaction, fields) -> interaction.setDatabases(fields.stream().map(Database::new).collect(Collectors.toList())),
					Interaction::getDatabases),
			new Column("Interaction identifier(s)",
					(interaction, fields) -> interaction.setIdentifiers(fields.stream().map(Identifier::new).collect(Collectors.toList())),
					Interaction::getIdentifiers),
			new Column("Confidence value(s)",
					(interaction, fields) -> interaction.setConfidenceScores(fields.stream().map(ConfidenceScore::new).collect(Collectors.toList())),
					Interaction::getConfidenceScores),
			new Column("Expansion method(s)",
					(interaction, fields) -> interaction.setComplexExpansion(fields.stream().map(ComplexExpansion::new).collect(Collectors.toList())),
					Interaction::getComplexExpansion),
			new Column("Biological role(s) interactor A",
					(interaction, fields) -> interaction.getInteractorA().setBiologicalRoles(fields.stream().map(BiologicalRole::new).collect(Collectors.toList())),
					interaction -> interaction.getInteractorA().getBiologicalRoles()),
			new Column("Biological role(s) interactor B",
					(interaction, fields) -> interaction.getInteractorB().setBiologicalRoles(fields.stream().map(BiologicalRole::new).collect(Collectors.toList())),
					interaction -> interaction.getInteractorB().getBiologicalRoles()),
			new Column("Experimental role(s) interactor A",
					(interaction, fields) -> interaction.getInteractorA().setExperimentalRoles(fields.stream().map(ExperimentalRole::new).collect(Collectors.toList())),
					interaction -> interaction.getInteractorA().getExperimentalRoles()),
			new Column("Experimental role(s) interactor B",
					(interaction, fields) -> interaction.getInteractorB().setExperimentalRoles(fields.stream().map(ExperimentalRole::new).collect(Collectors.toList())),
					interaction -> interaction.getInteractorB().getExperimentalRoles()),
			new Column("Type(s) interactor A",
					(interaction, fields) -> interaction.getInteractorA().setTypes(fields.stream().map(Type::new).collect(Collectors.toList())),
					interaction -> interaction.getInteractorA().getTypes()),
			new Column("Type(s) interactor B",
					(interaction, fields) -> interaction.getInteractorB().setTypes(fields.stream().map(Type::new).collect(Collectors.toList())),
					interaction -> interaction.getInteractorB().getTypes()),
			new Column("Xref(s) interactor A",
					(interaction, fields) -> interaction.getInteractorA().setCrossReferences(fields.stream().map(CrossReference::new).collect(Collectors.toList())),
					interaction -> interaction.getInteractorA().getCrossReferences()),
			new Column("Xref(s) interactor B",
					(interaction, fields) -> interaction.getInteractorB().setCrossReferences(fields.stream().map(CrossReference::new).collect(Collectors.toList())),
					interaction -> interaction.getInteractorB().getCrossReferences()),
			new Column("Interaction Xref(s)",
					(interaction, fields) -> interaction.setCrossReferences(fields.stream().map(CrossReference::new).collect(Collectors.toList())),
					Interaction::getCrossReferences),
			new Column("Annotation(s) interactor A",
					(interaction, fields) -> interaction.getInteractorA().setAnnotations(fields.stream().map(Annotation::new).collect(Collectors.toList())),
					interaction -> interaction.getInteractorA().getAnnotations()),
			new Column("Annotation(s) interactor B",
					(interaction, fields) -> interaction.getInteractorB().setAnnotations(fields.stream().map(Annotation::new).collect(Collectors.toList())),
					interaction -> interaction.getInteractorB().getAnnotations()),
			new Column("Interaction annotation(s)",
					(interaction, fields) -> interaction.setAnnotations(fields.stream().map(Annotation::new).collect(Collectors.toList())),
					Interaction::getAnnotations),
			new Column("Host organism(s)",
					(interaction, fields) -> interaction.setOrganism(fields.stream().map(Organism::new).collect(Collectors.toList())),
					Interaction::getOrganism),
			new Column("Interaction parameter(s)",
					(interaction, fields) -> interaction.setParameters(fields.stream().map(Parameter::new).collect(Collectors.toList())),
					Interaction::getParameters),
			new Column("Creation date",
					(interaction, fields) -> interaction.setCreation(fields.isEmpty() ? null : new Date(fields.get(0))),
					interaction -> List.of(interaction.getCreation())),
			new Column("Update date",
					(interaction, fields) -> interaction.setUpdate(fields.isEmpty() ? null : new Date(fields.get(0))),
					interaction -> List.of(interaction.getUpdate())),
			new Column("Checksum(s) interactor A",
					(interaction, fields) -> interaction.getInteractorA().setChecksums(fields.stream().map(Checksum::new).collect(Collectors.toList())),
					interaction -> interaction.getInteractorA().getChecksums()),
			new Column("Checksum(s) interactor B",
					(interaction, fields) -> interaction.getInteractorB().setChecksums(fields.stream().map(Checksum::new).collect(Collectors.toList())),
					interaction -> interaction.getInteractorB().getChecksums()),
			new Column("Interaction Checksum(s)",
					(interaction, fields) -> interaction.setChecksums(fields.stream().map(Checksum::new).collect(Collectors.toList())),
					Interaction::getChecksums),
			new Column("Negative",
					(interaction, fields) -> interaction.setNegative(fields.isEmpty() ? null : new Negative(fields.get(0))),
					interaction -> interaction.getNegative() == null ? List.of() : List.of(interaction.getNegative())),
			new Column("Feature(s) interactor A",
					(interaction, fields) -> interaction.getInteractorA().setFeatures(fields.stream().map(Feature::new).collect(Collectors.toList())),
					interaction -> interaction.getInteractorA().getFeatures()),
			new Column("Feature(s) interactor B",
					(interaction, fields) -> interaction.getInteractorB().setFeatures(fields.stream().map(Feature::new).collect(Collectors.toList())),
					interaction -> interaction.getInteractorB().getFeatures()),
			new Column("Stoichiometry(s) interactor A",
					(interaction, fields) -> interaction.getInteractorA().setStoichiometries(fields.stream().map(Stoichiometry::new).collect(Collectors.toList())),
					interaction -> interaction.getInteractorA().getStoichiometries()),
			new Column("Stoichiometry(s) interactor B",
					(interaction, fields) -> interaction.getInteractorB().setStoichiometries(fields.stream().map(Stoichiometry::new).collect(Collectors.toList())),
					interaction -> interaction.getInteractorB().getStoichiometries()),
			new Column("Identification method participant A",
					(interaction, fields) -> interaction.getInteractorA().setIdentificationMethods(fields.stream().map(IdentificationMethod::new).collect(Collectors.toList())),
					interaction -> interaction.getInteractorA().getIdentificationMethods()),
			new Column("Identification method participant B",
					(interaction, fields) -> interaction.getInteractorB().setIdentificationMethods(fields.stream().map(IdentificationMethod::new).collect(Collectors.toList())),
					interaction -> interaction.getInteractorB().getIdentificationMethods()),
			new Column("Biological effect of interactor A",
					(interaction, fields) -> interaction.getInteractorA().setBiologicalEffects(fields.stream().map(BiologicalEffect::new).collect(Collectors.toList())),
					interaction -> interaction.getInteractorA().getBiologicalEffects()),
			new Column("Biological effect of interactor B",
					(interaction, fields) -> interaction.getInteractorB().setBiologicalEffects(fields.stream().map(BiologicalEffect::new).collect(Collectors.toList())),
					interaction -> interaction.getInteractorB().getBiologicalEffects()),
			new Column("Causal regulatory mechanism",
					(interaction, fields) -> {
						if (fields != null && !fields.isEmpty())
							interaction.setCausalRegulatoryMechanism(new CausalRegulatoryMechanism(fields.get(0)));
					},
					interaction -> List.of(interaction.getCausalRegulatoryMechanism())),
			new Column("Causal statement",
					(interaction, fields) -> {
						if (fields != null && !fields.isEmpty())
							interaction.setCausalStatement(new CausalStatement(fields.get(0)));
					},
					interaction -> List.of(interaction.getCausalStatement()))

	);
	
	private static <T extends Field> List<T> transform(Class<T> target, List<Field> fields) {
		final Constructor<T> constructor;
		try {
			constructor = target.getDeclaredConstructor(Field.class);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
		List<T> list = new ArrayList<>();
		try {
			for (Field field : fields) list.add(constructor.newInstance(field));
		} catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	/**
	 * Map containing single instances for every {@link PsiMitabVersion}. Instances are created only
	 * at request through {@link PsiInteractionParser#instance()} or {@link
	 * PsiInteractionParser#instance(PsiMitabVersion)} methods.
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
		final Interaction interaction = new Interaction();
		final List<String> columns = splitSafe(line, COLUMN_SEPARATOR);
		for (int i = 0; i < columns.size(); i++) {
			try {
				final List<Field> fields = parseField(columns.get(i));
				COLUMNS.get(i).set(interaction, fields);
			} catch (StringIndexOutOfBoundsException ex) {
				throw new RuntimeException(String.format("For column %s, value [%s]", COLUMNS.get(i).getName(), columns.get(i)), ex);
			}
		}
		return interaction;
	}

	public String headerLine() {
		return COLUMNS.stream()
				.limit(numberOfColumns)
				.map(Column::getName)
				.collect(Collectors.joining(COLUMN_SEPARATOR));
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
			if (line.startsWith(separator, pos) && !quoted) {
				rtn.add(line.substring(start, pos));
				start = pos + 1;
			}
			pos += 1;

		}
		rtn.add(line.substring(start));
		return rtn;
	}

	/**
	 * Transforms the value inside field into an object of type Field.
	 *
	 * @param field string value of a field
	 * @return a list of Fields. If fields is the empty value (-) or the empty string, an empty list
	 * is returned
	 */
	final List<Field> parseField(String field) {
		field = field.strip();
		if (field.equals("-") || field.isEmpty()) return Collections.emptyList();
		final List<Field> fields = new LinkedList<>();
		final List<String> strings = splitSafe(field, FIELD_SEPARATOR);
		for (String string : strings) fields.add(FieldParser.parse(string));
		return fields;
	}

	public String toString(Interaction interaction) {
		return COLUMNS.stream()
				.limit(numberOfColumns)
				.map(column -> column.get(interaction))
				.map(this::toString)
				.collect(Collectors.joining(COLUMN_SEPARATOR));
	}

	private String toString(List<? extends Field> fields) {
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
		for (String reserved : RESERVED)
			if (value.contains(reserved))
				return true;
		return false;
	}

	private static class Column {

		private final String name;
		private final BiConsumer<Interaction, List<Field>> setter;
		private final Function<Interaction, List<? extends Field>> getter;

		private Column(String name, BiConsumer<Interaction, List<Field>> setter, Function<Interaction, List<? extends Field>> getter) {
			this.name = name;
			this.setter = setter;
			this.getter = getter;
		}

		String getName() {
			return name;
		}

		void set(Interaction interaction, List<Field> fields) {
			setter.accept(interaction, fields);
		}

		List<? extends Field> get(Interaction interaction) {
			return getter.apply(interaction);
		}
	}
}
