package org.uichuimi.mitab.io.model;

public class CrossReference extends Field {


	public CrossReference(String database, String identifier, String text) {
		super(database, identifier, text);
	}

	public CrossReference(Field field) {
		super(field);
	}

	public String getDatabase() {
		return getXref();
	}

	public String getIdentifier() {
		return getValue();
	}

	public String getText() {
		return getDescription();
	}
}
