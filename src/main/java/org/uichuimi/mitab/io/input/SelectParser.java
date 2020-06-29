package org.uichuimi.mitab.io.input;

import org.uichuimi.mitab.io.Selector;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SelectParser {
	private static final Pattern PATTERN = Pattern.compile("(?:(?<interactor>[ab])\\.)?(?<column>\\w+)(?:\\.(?<property>\\w+))?(?:\\[(?<range>.+)])?");
	private static final Pattern RANGE = Pattern.compile("(?<from>-?\\d+)?((?<ranged>:)(?<to>-?\\d+)?)?");

	private SelectParser() {
	}

	public static Selector parse(String text) {
		final Matcher matcher = PATTERN.matcher(text);
		if (!matcher.matches())
			throw new IllegalArgumentException("Selector '" + text + "' is not valid");
		final String interactor = matcher.group("interactor");
		final String column = matcher.group("column");
		final String property = matcher.group("property");
		final String range = matcher.group("range");
		final Integer from;
		final Integer to;
		final boolean ranged;
		if (range != null) {
			final Matcher rangeMatcher = RANGE.matcher(range);
			if (!rangeMatcher.matches()) 
				throw new IllegalArgumentException("Range '" + range + "' is malformed");
			final String f = matcher.group("from");
			from = f == null ? null : Integer.parseInt(f);
			final String t = matcher.group("to");
			to = t == null ? null : Integer.parseInt(t);
			ranged = matcher.group("ranged") != null;
		} else {
			from = to = null;
			ranged = false;
		}
		return new Selector(interactor, column, property, from, ranged, to);
	}
}
