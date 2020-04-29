package org.uichuimi.mitab.io.model;

public class Identifier extends Field {

	public Identifier(String database, String identifier) {
		super(database, identifier);
	}

	public Identifier(Field field) {
		super(field);
	}

	public String getDatabase() {
		return super.getXref();
	}

	public String getIdentifier() {
		return super.getValue();
	}

}
