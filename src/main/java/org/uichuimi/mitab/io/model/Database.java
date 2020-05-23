package org.uichuimi.mitab.io.model;

public class Database extends Field {
	public Database(String database, String identifier, String name) {
		super(database, identifier, name);
	}

	public Database(Field field) {
		super(field);
	}

	public String getDatabase() {
		return super.getXref();
	}

	public String getIdentifier() {
		return super.getValue();
	}

	public String getName() {
		return super.getDescription();
	}

}
