package org.uichuimi.mitab.io.model;

public class Organism extends Field {

	/**
	 * creates a taxid:identifier Species
	 *
	 * @param identifier taxid identifier
	 */
	public Organism(String identifier) {
		super("taxid", identifier);
	}

	public Organism(String database, String identifier, String name) {
		super(database, identifier, name);
	}

	public Organism(Field field) {
		super(field);
	}

	public String getDatabase() {
		return getXref();
	}

	public String getIdentifier() {
		return getValue();
	}

	public String getName() {
		return getDescription();
	}

}
