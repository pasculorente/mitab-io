package org.reactome.server.tools.mitab.io.input;

import org.junit.Assert;
import org.junit.Test;
import org.reactome.server.tools.mitab.io.InteractionReader;
import org.reactome.server.tools.mitab.io.model.ColumnName;
import org.reactome.server.tools.mitab.io.model.Interaction;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class InteractionReaderTest {

	@Test
	public void test() {
		try {
			final List<Interaction> interactions = InteractionReader.read(getClass().getResourceAsStream("test.mitab27"))
					.collect(Collectors.toList());
			Assert.assertEquals(1, interactions.size());
			final Interaction interaction = interactions.get(0);
			Assert.assertEquals(2, interaction.get(ColumnName.ID).size());
			Assert.assertEquals(9, interaction.get(ColumnName.A_ALT_ID).size());
			Assert.assertEquals("2001/01/10", interaction.get(ColumnName.CREATION).get(0).getDatabase());
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
}
