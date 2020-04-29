package org.uichuimi.mitab.io.model;

public class ConfidenceScore extends Field {

	ConfidenceScore(String type, String value) {
		super(type, value);
	}

	public ConfidenceScore(Field field) {
		super(field);
	}

	public String getType() {
		return getXref();
	}

}
