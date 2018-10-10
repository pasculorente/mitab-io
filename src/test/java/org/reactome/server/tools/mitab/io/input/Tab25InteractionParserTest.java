package org.reactome.server.tools.mitab.io.input;


import org.junit.Assert;
import org.junit.Test;
import org.reactome.server.tools.mitab.io.model.Field;
import org.reactome.server.tools.mitab.io.model.PsiMitabVersion;

import java.util.List;

public class Tab25InteractionParserTest {

	@Test
	public void getFields() {
		final String a = "refseq:NP_001013128|ensembl:ENSRNOP00000012946";
		final List<Field> fields = PsiInteractionParser.instance(PsiMitabVersion.TAB_25).parseField(a);
		Assert.assertEquals(2, fields.size());
		Assert.assertEquals("refseq", fields.get(0).getDatabase());
		Assert.assertEquals("ENSRNOP00000012946", fields.get(1).getIdentifier());
	}

	@Test
	public void getQuotedFields() {
		final String a = "refseq:\"|NP_001013128\"|ensembl:ENSRNOP00000012946";
		final List<Field> fields = PsiInteractionParser.instance(PsiMitabVersion.TAB_25).parseField(a);
		Assert.assertEquals(2, fields.size());
		Assert.assertEquals("|NP_001013128", fields.get(0).getIdentifier());
		Assert.assertEquals("ensembl", fields.get(1).getDatabase());

	}

	@Test
	public void getOneField() {
		final String a = "uniprotkb";
		final List<Field> fields = PsiInteractionParser.instance(PsiMitabVersion.TAB_26).parseField(a);
		Assert.assertEquals(1, fields.size());
		Assert.assertEquals("uniprotkb", fields.get(0).getDatabase());
		Assert.assertNull(fields.get(0).getIdentifier());
	}

}
