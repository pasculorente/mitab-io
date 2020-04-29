package org.uichuimi.mitab.io.model;

public class BiologicalEffect extends Field {

	public BiologicalEffect(String database, String identifier, String name) {
		super(database, identifier, name);
	}

	public BiologicalEffect(Field field) {
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
