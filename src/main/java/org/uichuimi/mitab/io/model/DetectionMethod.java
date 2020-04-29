package org.uichuimi.mitab.io.model;

public class DetectionMethod extends Field {

	public DetectionMethod(String database, String identifier, String name) {
		super(database, identifier, name);
	}

	public DetectionMethod(Field field) {
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
