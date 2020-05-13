package org.uichuimi.mitab.io.output;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.uichuimi.mitab.io.InteractionReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class TsvWriterTest {

	@Test
	public void write() throws Exception {
		final InputStream resource = getClass().getResourceAsStream("/input/sample-interactions.mitab27");
		final File tempFile = File.createTempFile("tsv", ".tsv");
		try (final InteractionReader reader = new InteractionReader(resource);
		     final TsvWriter writer = new TsvWriter(tempFile)) {
			reader.forEach(writer);
		}

		try {
			final String expected = IOUtils.toString(getClass().getResourceAsStream("/output/expected_output.tsv"), Charset.defaultCharset());
			final String actual = IOUtils.toString(new FileInputStream(tempFile), Charset.defaultCharset());
			for (int i = 0; i < expected.length(); i++)
				Assert.assertEquals("" + i + " " + expected.substring(Math.max(0, i - 10), i), String.valueOf(expected.charAt(i)), String.valueOf(actual.charAt(i)));
			Assert.assertEquals(expected, actual);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}