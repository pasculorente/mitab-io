package org.reactome.server.tools.mitab.io.input;


import org.junit.Assert;
import org.junit.Test;
import org.reactome.server.tools.mitab.io.model.Field;

public class FieldParserTest {

	@Test
	void parseValueAndDescriptionQuoted() {
		final String b = "psi-mi:\"MI:0000\"(\"I can now use braces ()()() or pipes ||| here and ::colons::\")";
		final Field field = FieldParser.parse(b);
		Assert.assertEquals("psi-mi", field.getDatabase());
		Assert.assertEquals("MI:0000", field.getIdentifier());
		Assert.assertEquals("I can now use braces ()()() or pipes ||| here and ::colons::", field.getDescription());
	}

	@Test
	void parseSimple() {
		final String a = "uniprotkb:Serotransferrin(recommended name)";
		final Field field = FieldParser.parse(a);
		Assert.assertEquals("uniprotkb", field.getDatabase());
		Assert.assertEquals("Serotransferrin", field.getIdentifier());
		Assert.assertEquals("recommended name", field.getDescription());
	}

	@Test
	void parseOneField() {
		final String a = "APOB";
		final Field field = FieldParser.parse(a);
		Assert.assertEquals("APOB", field.getDatabase());
	}

	@Test
	void parseTwoFields() {
		final String a = "uniprotkb:P12346";
		final Field field = FieldParser.parse(a);
		Assert.assertEquals("uniprotkb", field.getDatabase());
		Assert.assertEquals("P12346", field.getIdentifier());
	}

	@Test
	void parseDescriptionQuoted() {
		final String c = "uniprotkb:P12345(\"a \\\"nice\\\" protein\")\n";
		final Field field = FieldParser.parse(c);
		Assert.assertEquals("uniprotkb", field.getDatabase());
		Assert.assertEquals("P12345", field.getIdentifier());
		Assert.assertEquals("a \\\"nice\\\" protein", field.getDescription());
	}

	@Test
	void parseValueQuoted() {
		final String a = "psi-mi:\"MI:0000\"(a cv term)";
		final Field field = FieldParser.parse(a);
		Assert.assertEquals("psi-mi", field.getDatabase());
		Assert.assertEquals("MI:0000", field.getIdentifier());
		Assert.assertEquals("a cv term", field.getDescription());

	}

	@Test
	void parseEmptyField() {
		final String text = "-";
		final Field field = FieldParser.parse(text);
		Assert.assertNull(field);
	}

}
