package org.uichuimi.mitab.io.model;

public class Negative extends Field {

	Negative(String negative) {
		super(negative);
	}

	public Negative(Field field) {
		super(field);
	}
	public String getNegative() {
		return getXref();
	}
}
