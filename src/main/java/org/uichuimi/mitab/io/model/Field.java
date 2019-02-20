package org.uichuimi.mitab.io.model;

public class Field {
	private final String xref;
	private final String value;
	private final String description;

	Field(String xref) {
		this(xref, null);
	}

	Field(String xref, String value) {
		this(xref, value, null);
	}

	public Field(String xref, String value, String description) {
		this.xref = xref;
		this.value = value;
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

	public String getXref() {
		return xref;
	}

	@Override
	public String toString() {
		return String.join(":", xref, value, description);
	}
}
