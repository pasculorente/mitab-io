package org.uichuimi.mitab.io.model;

public class Date extends Field{

	public Date(String date) {
		super(date);
	}

	public Date(Field field) {
		super(field);
	}

	public String getDate() {return getXref();}
}
