package org.uichuimi.mitab.io.model;

public class Type extends Field {

	public Type(String database, String identifier, String name) {
		super(database, identifier, name);
	}

	public Type(Field field) {
		super(field);
	}

	public String getDatabase() {
		return getXref();
	}

	public String getIdentifier() {
		return getValue();
	}

	public String getName() {
		return getDescription();
	}
}
