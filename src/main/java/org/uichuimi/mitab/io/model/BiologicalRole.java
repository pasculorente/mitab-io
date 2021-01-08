package org.uichuimi.mitab.io.model;

public class BiologicalRole extends Field {

	public BiologicalRole(String database, String identifier, String description) {
		super(database, identifier, description);
	}

	public BiologicalRole(Field field) {
		super(field);
	}

	public String getDatabase() {
		return super.getXref();
	}

	public String getIdentifier() {
		return super.getValue();
	}

}
