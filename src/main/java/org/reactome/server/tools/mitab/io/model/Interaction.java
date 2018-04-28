package org.reactome.server.tools.mitab.io.model;

import java.util.Collections;
import java.util.List;

public class Interaction {

	private final List[] values;

	public Interaction(int size) {
		this.values = new List[size];
	}

	void set(ColumnName name, List<Field> value) {
		values[name.ordinal()] = value;
	}

	public void set(int column, List<Field> value) {
		values[column] = value;
	}

	/**
	 * @return a list of the fields in column. If no fields are present, returns
	 * an empty list.
	 */
	public List<Field> get(int column) {
		return values[column] == null
				? Collections.emptyList()
				: values[column];
	}

	/**
	 * @return a list of the fields in columnName. If no fields are present,
	 * returns an empty list.
	 */
	public List<Field> get(ColumnName columnName) {
		return values[columnName.ordinal()] == null
				? Collections.emptyList()
				: values[columnName.ordinal()];
	}

}
