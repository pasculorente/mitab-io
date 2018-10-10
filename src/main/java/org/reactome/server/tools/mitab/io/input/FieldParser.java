package org.reactome.server.tools.mitab.io.input;

import org.reactome.server.tools.mitab.io.model.Field;

class FieldParser {

	private FieldParser() {
	}

	static Field parse(String string) throws IllegalArgumentException {
		try {
			return parsePrivate(string);
		} catch (StringIndexOutOfBoundsException ex) {
			throw new IllegalArgumentException(string, ex);
		}
	}

	private static Field parsePrivate(String string) {
		if (string == null || string.equals("") || string.equals("-"))
			return null;
		// We will cover 3 cases, all quoted and unquoted:
		// xref
		// xref:value
		// xref:value(description)
		String xref, value = null, desc = null;

		// 1: xref
		// Quoted
		if (string.charAt(0) == '"') {
			int end = closingQuotes(string);
			xref = string.substring(1, end);
			string = string.substring(end + 1);
		}
		// unquoted
		else {
			final int end = string.indexOf(':');
			if (end == -1) {
				xref = string;
				return new Field(xref, null, null);
			}
			xref = string.substring(0, end);
			string = string.substring(end);
		}
		// 2: Value
		if (!string.isEmpty() && string.charAt(0) == ':') {
			// Quoted
			string = string.substring(1);
			if (string.isEmpty()) {
				value = "";
			} else {
				if (string.charAt(0) == '"') {
					int end = closingQuotes(string);
					value = string.substring(1, end);
					string = string.substring(end + 1);
				}
				// unquoted
				else {
					final int end = string.indexOf('(');
					if (end == -1) {
						value = string;
						return new Field(xref, value, null);
					}
					value = string.substring(0, end);
					string = string.substring(end);
				}
			}
		}
		// 3: description
		if (!string.isEmpty() && string.charAt(0) == '(') {
			string = string.substring(1);
			// Quoted
			if (string.charAt(0) == '"') {
				int end = closingQuotes(string);
				desc = string.substring(1, end);

			}
			// unquoted
			else {
				final int end = string.indexOf(')');
				desc = string.substring(0, end);
			}
		}
		return new Field(xref, value, desc);
	}

	/**
	 * Get the position of the next unescaped quote
	 *
	 * @param string field string
	 * @return position of next unescaped quote or -1 if none
	 */
	private static int closingQuotes(String string) {
		int end = string.indexOf('"', 1);
		while (string.charAt(end - 1) == '\\')
			end = string.indexOf('"', end + 1);
		return end;
	}
}
