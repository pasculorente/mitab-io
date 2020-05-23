package org.uichuimi.mitab.io.model;

public class Alias extends Field {
	public Alias(String database, String name, String type) {
		super(database, name, type);
	}

	public Alias(Field field) {
		super(field.getXref(), field.getValue(), field.getDescription());
	}

	public String getDatabase() {
		return super.getXref();
	}

	public String getName() {
		return getValue();
	}

	public String getType() {
		return getDescription();
	}

}
