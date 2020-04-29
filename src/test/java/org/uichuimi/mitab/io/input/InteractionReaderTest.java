package org.uichuimi.mitab.io.input;

import org.junit.Assert;
import org.junit.Test;
import org.uichuimi.mitab.io.InteractionReader;
import org.uichuimi.mitab.io.model.Interaction;

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
			Assert.assertEquals(2, interaction.getIdentifiers().size());
			Assert.assertEquals(9, interaction.getInteractorA().getAlternativeIdentifiers().size());
			Assert.assertEquals("2001/01/10", interaction.getCreation().get(0).getXref());
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
}
