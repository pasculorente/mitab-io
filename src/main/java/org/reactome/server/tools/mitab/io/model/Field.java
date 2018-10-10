package org.reactome.server.tools.mitab.io.model;

public class Field {
	private final String database, identifier, description;

	public Field(String database, String identifier, String description) {
		this.database = database;
		this.identifier = identifier;
		this.description = description;
	}

	public String getIdentifier() {
		return identifier;
	}

	public String getDescription() {
		return description;
	}

	public String getDatabase() {
		return database;
	}

	@Override
	public String toString() {
		return String.join(":", database, identifier, description);
	}
}
