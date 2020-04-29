package org.uichuimi.mitab.io.model;

public class CausalStatement extends Field {

	public CausalStatement(String xref, String value, String description) {
		super(xref, value, description);
	}

	public CausalStatement(Field field) {
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
