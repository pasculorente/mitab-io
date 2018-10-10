package org.reactome.server.tools.mitab.io.model;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Allows named access to interaction fields.
 */
public enum ColumnName {

	 // NOTE for developers: although we can use ColumnName.ordinal(), it is strongly discouraged.
	A_ID("ID(s) interactor A", 0),
	B_ID("ID(s) interactor B", 1),
	A_ALT_ID("Alt. ID(s) interactor A", 2),
	B_ALT_ID("Alt. ID(s) interactor B", 3),
	A_ALIAS("Alias(es) interactor A", 4),
	B_ALIAS("Alias(es) interactor B", 5),
	DETECTION_METHOD("Interaction detection method(s)", 6),
	AUTHOR("Publication 1st author(s)", 7),
	PUBLICATION("Publication Identifier(s)", 8),
	A_ORGANISM("Taxid interactor A", 9),
	B_ORGANISM("Taxid interactor B", 10),
	TYPE("Interaction type(s)", 11),
	DATABASE("Source database(s)", 12),
	ID("Interaction identifier(s)", 13),
	CONFIDENCE("Confidence value(s)", 14),
	EXPANSION_METHOD("Expansion method(s)", 15),
	A_BIO_ROLE("Biological role(s) interactor A", 16),
	B_BIO_ROLE("Biological role(s) interactor B", 17),
	A_EXP_ROLE("Experimental role(s) interactor A", 18),
	B_EXP_ROLE("Experimental role(s) interactor B", 19),
	A_TYPE("Type(s) interactor A", 20),
	B_TYPE("Type(s) interactor B", 21),
	A_XREF("Xref(s) interactor A", 22),
	B_XREF("Xref(s) interactor B", 23),
	XREF("Interaction Xref(s)", 24),
	A_ANNOTATION("Annotation(s) interactor A", 25),
	B_ANNOTATION("Annotation(s) interactor B", 26),
	ANNOTATION("Interaction annotation(s)", 27),
	ORGANISM("Host organism(s)", 28),
	PARAMETERS("Interaction parameter(s)", 29),
	CREATION("Creation date", 30),
	UPDATE("Update date", 31),
	A_CHECKSUM("Checksum(s) interactor A", 32),
	B_CHECKSUM("Checksum(s) interactor B", 33),
	CHECKSUM("Interaction Checksum(s)", 34),
	NEGATIVE("Negative", 35),
	A_FEATURES("Feature(s) interactor A", 36),
	B_FEATURES("Feature(s) interactor B", 37),
	A_STOICHIOMETRY("Stoichiometry(s) interactor A", 38),
	B_STOICHIOMETRY("Stoichiometry(s) interactor B", 39),
	A_IDENTIFICATION_METHOD("Identification method participant A", 40),
	B_IDENTIFICATION_METHOD("Identification method participant B", 41),
	A_BIOLOGICAL_EFFECT("Biological effect of interactor A", 42),
	B_BIOLOGICAL_EFFECT("Biological effect of interactor B", 43),
	CASUAL_REGULATORY_MECHANISM("Causal regulatory mechanism", 44),
	CAUSAL_STATEMENT("Causal statement", 45);

	private final String name;
	private final Function<Interaction, List<Field>> getter;
	private final BiConsumer<Interaction, List<Field>> setter;

	/**
	 * Shorthand for creating a column with getter and setter based only on index.
	 */
	ColumnName(String name, int index) {
		this(name, interaction -> interaction.get(index), ((interaction, fields) -> interaction.set(index, fields)));
	}

	/**
	 * Genral ColumnName constructor. By using this constructor you will be able to retrieve fields not only based on
	 * its index, but you can merge, filter or create fields. Imagination is the limit.
	 */
	ColumnName(String name, Function<Interaction, List<Field>> getter, BiConsumer<Interaction, List<Field>> setter) {
		this.name = name;
		this.getter = getter;
		this.setter = setter;
	}

	public String getName() {
		return name;
	}

	public List<Field> get(Interaction interaction) {
		return getter.apply(interaction);
	}

	public void set(Interaction interaction, List<Field> fields) {
		setter.accept(interaction, fields);
	}

}
