package org.uichuimi.mitab.io.input;

import org.junit.Assert;
import org.junit.Test;
import org.uichuimi.mitab.io.model.Interaction;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class InteractionReaderTest {

	@Test
	public void test() {
		try {
			InputStream resource = getClass().getResourceAsStream("/input/test.mitab27");
			final List<Interaction> interactions = new InteractionReader(resource).readAll();
			Assert.assertEquals(1, interactions.size());
			final Interaction interaction = interactions.get(0);
			Assert.assertEquals(2, interaction.getIdentifiers().size());
			Assert.assertEquals(9, interaction.getInteractorA().getAlternativeIdentifiers().size());
			Assert.assertEquals("2001/01/10", interaction.getCreation().getDate());
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
}
