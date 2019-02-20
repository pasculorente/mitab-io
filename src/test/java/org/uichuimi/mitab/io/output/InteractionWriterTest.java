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
import java.util.stream.Collectors;

public class InteractionWriterTest {

	private static final File FILE = new File(InteractionWriterTest.class.getResource("/org/uichuimi/mitab/io/output/test-output.mitab27").getPath());

	@Test
	public void test() {
		try (OutputStream outputStream = new FileOutputStream(FILE)) {
			final List<Interaction> interactions = InteractionReader.read(getClass().getResourceAsStream("test.mitab27"))
					.collect(Collectors.toList());
			final InteractionWriter writer = new InteractionWriter(outputStream, PsiMitabVersion.TAB_27);
			interactions.forEach(writer::write);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		try {
			final String expected = IOUtils.toString(getClass().getResourceAsStream("test.mitab27"), Charset.defaultCharset());
			final String actual = IOUtils.toString(new FileInputStream(FILE), Charset.defaultCharset());
			for (int i = 0; i < expected.length(); i++)
				Assert.assertEquals("" + i + " " + expected.substring(Math.max(0,i - 10), i), String.valueOf(expected.charAt(i)),  String.valueOf(actual.charAt(i)));
			Assert.assertEquals(expected, actual);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
