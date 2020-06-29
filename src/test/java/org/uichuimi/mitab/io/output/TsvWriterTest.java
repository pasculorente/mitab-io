package org.uichuimi.mitab.io.output;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.uichuimi.mitab.io.input.InteractionReader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class TsvWriterTest {

	@Test
	public void write() throws Exception {
		final InputStream resource = getClass().getResourceAsStream("/input/sample-interactions.mitab27");
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try (final InteractionReader reader = new InteractionReader(resource);
		     final TsvWriter writer = new TsvWriter(outputStream)) {
			reader.forEach(writer);
		}
		try {
			final String expected = IOUtils.toString(getClass().getResourceAsStream("/output/expected_output.tsv"), Charset.defaultCharset());
			final String actual = outputStream.toString();
			Assert.assertEquals(expected, actual);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}