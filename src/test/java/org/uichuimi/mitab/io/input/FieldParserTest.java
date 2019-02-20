package org.uichuimi.mitab.io.input;


import org.junit.Assert;
import org.junit.Test;
import org.uichuimi.mitab.io.model.Field;

public class FieldParserTest {

	@Test
	public void parseValueAndDescriptionQuoted() {
		final String b = "psi-mi:\"MI:0000\"(\"I can now use braces ()()() or pipes ||| here and ::colons::\")";
		final Field field = FieldParser.parse(b);
		Assert.assertEquals("psi-mi", field.getXref());
		Assert.assertEquals("MI:0000", field.getValue());
		Assert.assertEquals("I can now use braces ()()() or pipes ||| here and ::colons::", field.getDescription());
	}

	@Test
	public void parseSimple() {
		final String a = "uniprotkb:Serotransferrin(recommended name)";
		final Field field = FieldParser.parse(a);
		Assert.assertEquals("uniprotkb", field.getXref());
		Assert.assertEquals("Serotransferrin", field.getValue());
		Assert.assertEquals("recommended name", field.getDescription());
	}

	@Test
	public void parseOneField() {
		final String a = "APOB";
		final Field field = FieldParser.parse(a);
		Assert.assertEquals("APOB", field.getXref());
	}

	@Test
	public void parseTwoFields() {
		final String a = "uniprotkb:P12346";
		final Field field = FieldParser.parse(a);
		Assert.assertEquals("uniprotkb", field.getXref());
		Assert.assertEquals("P12346", field.getValue());
	}

	@Test
	public void parseDescriptionQuoted() {
		final String c = "uniprotkb:P12345(\"a \\\"nice\\\" protein\")\n";
		final Field field = FieldParser.parse(c);
		Assert.assertEquals("uniprotkb", field.getXref());
		Assert.assertEquals("P12345", field.getValue());
		Assert.assertEquals("a \\\"nice\\\" protein", field.getDescription());
	}

	@Test
	public void parseValueQuoted() {
		final String a = "psi-mi:\"MI:0000\"(a cv term)";
		final Field field = FieldParser.parse(a);
		Assert.assertEquals("psi-mi", field.getXref());
		Assert.assertEquals("MI:0000", field.getValue());
		Assert.assertEquals("a cv term", field.getDescription());

	}

	@Test(expected = IllegalArgumentException.class)
	public void parseEmptyField() {
		final String text = "-";
		final Field field = FieldParser.parse(text);
	}

}
