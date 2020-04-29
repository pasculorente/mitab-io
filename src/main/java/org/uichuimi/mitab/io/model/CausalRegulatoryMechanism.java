package org.uichuimi.mitab.io.model;

public class CausalRegulatoryMechanism extends Field {

	CausalRegulatoryMechanism(String xref) {
		super(xref);
	}

	CausalRegulatoryMechanism(String xref, String value) {
		super(xref, value);
	}

	public CausalRegulatoryMechanism(String xref, String value, String description) {
		super(xref, value, description);
	}

	public CausalRegulatoryMechanism(Field field) {
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
