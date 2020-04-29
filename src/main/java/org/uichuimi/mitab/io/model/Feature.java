package org.uichuimi.mitab.io.model;

public class Feature extends Field {
	public Feature(String type, String range, String text) {
		super(type, range, text);
	}

	public Feature(Field field) {
		super(field);
	}

	public String getType() {
		return getXref();
	}

	public String getRange() {
		return getValue();
	}

	public String getText() {
		return getDescription();
	}
}
