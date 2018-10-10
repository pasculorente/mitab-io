package org.reactome.server.tools.mitab.io.model;

import java.util.Collections;
import java.util.List;

/**
 * Represents a MITAB line. An interaction is an array of lists of Fields: <code>List&lt;Field&gt;[]</code>. To access
 * a column use {@link Interaction#get(int)} or {@link Interaction#get(ColumnName)}. This class can also be used for
 * writing, using setters.
 */
public class Interaction {

	private final List[] values;

	public Interaction(int size) {
		this.values = new List[size];
	}

	public void set(int column, List<Field> value) {
		values[column] = value;
	}

	public void set(ColumnName columnName, List<Field> value ) {

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
		final List<Field> fields = columnName.get(this);
		if (fields == null) return Collections.emptyList();
		return fields;
	}

}
