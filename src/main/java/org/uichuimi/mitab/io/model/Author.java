package org.uichuimi.mitab.io.model;

public class Author extends Field {

	public Author(String author) {
		super(author);
	}

	public Author(Field field) {
		super(field.getXref());
	}

	public String getAuthor() {
		return getXref();
	}

}
