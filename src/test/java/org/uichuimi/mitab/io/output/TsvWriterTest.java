package org.uichuimi.mitab.io.output;

import org.apache.commons.io.output.NullOutputStream;
import org.junit.Test;
import org.uichuimi.mitab.io.FileUtils;
import org.uichuimi.mitab.io.InteractionReader;

import java.io.File;

public class TsvWriterTest {

	@Test
	public void write() {
		final File resource = new File("/input/test.mitab27");
		try (final InteractionReader reader = new InteractionReader(FileUtils.getInputStream(resource));
		     final TsvWriter writer = new TsvWriter(new NullOutputStream())) {
			reader.forEach(writer::accept);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}