package org.uichuimi.mitab.io.output;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.uichuimi.mitab.io.FileUtils;
import org.uichuimi.mitab.io.InteractionReader;
import org.uichuimi.mitab.io.consumer.Neo4jWriter;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.zip.GZIPInputStream;

public class Neo4jWriterTest {

	@Test
	public void write() throws Exception {
		final InputStream resource = getClass().getResourceAsStream("/input/sample-interactions.mitab27");
		final File path = Files.createTempDirectory("output-neo4j").toFile();
		try (InteractionReader reader = new InteractionReader(resource);
		     Neo4jWriter writer = new Neo4jWriter(path);) {
			reader.forEach(writer);
		}
		String actual = IOUtils.toString(new GZIPInputStream(getClass().getResourceAsStream("/output/nodes.tsv.gz")), Charset.defaultCharset());
		String expected = IOUtils.toString(FileUtils.getInputStream(new File(path, "nodes.tsv.gz")), Charset.defaultCharset());
		Assert.assertEquals(expected, actual);

		actual = IOUtils.toString(new GZIPInputStream(getClass().getResourceAsStream("/output/relationships.tsv.gz")), Charset.defaultCharset());
		expected = IOUtils.toString(FileUtils.getInputStream(new File(path, "relationships.tsv.gz")), Charset.defaultCharset());
		Assert.assertEquals(expected, actual);

	}
}
