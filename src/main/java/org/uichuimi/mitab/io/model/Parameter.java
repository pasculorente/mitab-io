package org.uichuimi.mitab.io.model;

public class Parameter extends Field {
	Parameter(String type, String value, String text) {
		super(type, value, text);
	}

	public Parameter(Field field) {
		super(field);
	}

	public String getType() {
		return getXref();
	}

	public String getText() {
		return super.getDescription();
	}
}
