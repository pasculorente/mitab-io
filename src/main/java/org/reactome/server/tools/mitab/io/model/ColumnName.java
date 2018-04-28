package org.reactome.server.tools.mitab.io.model;

public enum ColumnName {
	A_ID("ID(s) interactor A"),
	B_ID("ID(s) interactor B"),
	A_ALT_ID("Alt. ID(s) interactor A"),
	B_ALT_ID("Alt. ID(s) interactor B"),
	A_ALIAS("Alias(es) interactor A"),
	B_ALIAS("Alias(es) interactor B"),
	DETECTION_METHOD("Interaction detection method(s)"),
	AUTHOR("Publication 1st author(s)"),
	PUBLICATION("Publication Identifier(s)"),
	A_ORGANISM("Taxid interactor A"),
	B_ORGANISM("Taxid interactor B"),
	TYPE("Interaction type(s)"),
	DATABASE("Source database(s)"),
	ID("Interaction identifier(s)"),
	CONFIDENCE("Confidence value(s)"),
	EXPANSION_METHOD("Expansion method(s)"),
	A_BIO_ROLE("Biological role(s) interactor A"),
	B_BIO_ROLE("Biological role(s) interactor B"),
	A_EXP_ROLE("Experimental role(s) interactor A"),
	B_EXP_ROLE("Experimental role(s) interactor B"),
	A_TYPE("Type(s) interactor A"),
	B_TYPE("Type(s) interactor B"),
	A_XREF("Xref(s) interactor A"),
	B_XREF("Xref(s) interactor B"),
	XREF("Interaction Xref(s)"),
	A_ANNOTATION("Annotation(s) interactor A"),
	B_ANNOTATION("Annotation(s) interactor B"),
	ANNOTATION("Interaction annotation(s)"),
	ORGANISM("Host organism(s)"),
	PARAMETERS("Interaction parameter(s)"),
	CREATION("Creation date"),
	UPDATE("Update date"),
	A_CHECKSUM("Checksum(s) interactor A"),
	B_CHECKSUM("Checksum(s) interactor B"),
	CHECKSUM("Interaction Checksum(s)"),
	NEGATIVE("Negative"),
	A_FEATURES("Feature(s) interactor A"),
	B_FEATURES("Feature(s) interactor B"),
	A_STOICHIOMETRY("Stoichiometry(s) interactor A"),
	B_STOICHIOMETRY("Stoichiometry(s) interactor B"),
	A_DETECTION_METHOD("Identification method participant A"),
	B_DETECTION_METHOD("Identification method participant B");

	private final String name;

	ColumnName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
