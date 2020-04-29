package org.uichuimi.mitab.io.model;

public class Annotation extends Field {

	public Annotation(String topic, String text) {
		super(topic, text);
	}

	public Annotation(Field field) {
		super(field);
	}

	public String getTopic() {
		return getXref();
	}

	public String getText() {
		return getValue();
	}
}
