package org.uichuimi.mitab.io;

import org.junit.Test;
import org.uichuimi.mitab.io.input.InteractionReader;
import org.uichuimi.mitab.io.model.Interaction;

import java.io.InputStream;

public class FilterTest {
	
	@Test
	public void filter() {
		final ExportColumn exportColumn = new ExportColumn(null, "method", "", null, null, null);

		final InputStream resource = getClass().getResourceAsStream("/input/sample-interactions.mitab27");
		try (final InteractionReader reader = new InteractionReader(resource)){
			for (Interaction interaction : reader)
				System.out.println(exportColumn.get(interaction));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
