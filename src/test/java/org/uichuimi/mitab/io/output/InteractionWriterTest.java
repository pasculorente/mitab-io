package org.uichuimi.mitab.io.output;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.uichuimi.mitab.io.InteractionReader;
import org.uichuimi.mitab.io.InteractionWriter;
import org.uichuimi.mitab.io.model.Interaction;
import org.uichuimi.mitab.io.model.PsiMitabVersion;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

public class InteractionWriterTest {


	public static final String INPUT_NAME = "/input/test.mitab27";

	@Test
	public void test() throws IOException {
		 final File tempFile = File.createTempFile("mitab-io-test", ".mitab27");
		try (OutputStream outputStream = new FileOutputStream(tempFile)) {
			final List<Interaction> interactions = new InteractionReader(getClass().getResourceAsStream(INPUT_NAME)).readAll();
			final InteractionWriter writer = new InteractionWriter(outputStream, PsiMitabVersion.TAB_27);
			interactions.forEach(writer::write);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		try {
			final String expected = IOUtils.toString(getClass().getResourceAsStream(INPUT_NAME), Charset.defaultCharset());
			final String actual = IOUtils.toString(new FileInputStream(tempFile), Charset.defaultCharset());
			for (int i = 0; i < expected.length(); i++)
				Assert.assertEquals("" + i + " " + expected.substring(Math.max(0, i - 10), i), String.valueOf(expected.charAt(i)), String.valueOf(actual.charAt(i)));
			Assert.assertEquals(expected, actual);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
