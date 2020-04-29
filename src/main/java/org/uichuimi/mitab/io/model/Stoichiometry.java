package org.uichuimi.mitab.io.model;

public class Stoichiometry extends Field {

	public Stoichiometry(String stoichiometry) {
		super(stoichiometry);
	}

	public Stoichiometry(Field field) {
		super(field);
	}

	public String getStoichiometry() {
		return getXref();
	}
}
