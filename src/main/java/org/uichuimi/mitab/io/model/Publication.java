package org.uichuimi.mitab.io.model;

public class Publication extends Field {

	public Publication(String database, String identifier) {
		super(database, identifier);
	}

	public Publication(Field field) {
		super(field);
	}

	public String getDatabase() {
		return super.getXref();
	}

	public String getIdentifier() {
		return super.getValue();
	}

}
