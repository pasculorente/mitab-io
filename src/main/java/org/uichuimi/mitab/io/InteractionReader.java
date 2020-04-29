package org.uichuimi.mitab.io;

import org.uichuimi.mitab.io.input.PsiInteractionParser;
import org.uichuimi.mitab.io.model.Interaction;
import org.uichuimi.mitab.io.model.PsiMitabVersion;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.zip.GZIPInputStream;

@SuppressWarnings("WeakerAccess")
public class InteractionReader implements AutoCloseable, Iterable<Interaction>, Iterator<Interaction> {

	private final BufferedReader reader;
	private final PsiInteractionParser parser;
	private Interaction next;

	public InteractionReader(File file) throws IOException {
		this(getInputStream(file));
	}

	public InteractionReader(File file, PsiMitabVersion version) throws IOException {
		this(getInputStream(file), version);
	}

	private static InputStream getInputStream(File file) throws IOException {
		if (file.getName().endsWith(".gz"))
			return new GZIPInputStream(new FileInputStream(file));
		else return new FileInputStream(file);
	}

	public InteractionReader(InputStream inputStream, PsiMitabVersion version) throws IOException {
		this.reader = new BufferedReader(new InputStreamReader(inputStream));
		this.reader.readLine();// skip header
		parser = PsiInteractionParser.instance(version);
	}

	public InteractionReader(InputStream inputStream) throws IOException {
		this(inputStream, PsiMitabVersion.getDefault());
	}

	/**
	 * Reads a PSI-MITAB input in TAB_27 version.
	 */
	public static Stream<Interaction> read(InputStream inputStream) throws IOException {
		return read(inputStream, PsiMitabVersion.TAB_27);
	}

	/**
	 * Reads a PSI-MITAB input stream, generating an interaction out of each line.
	 */
	public static Stream<Interaction> read(InputStream inputStream, PsiMitabVersion version) throws IOException {
		final PsiInteractionParser parser = PsiInteractionParser.instance(version);
		final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		reader.readLine(); // Skip header
		return reader.lines().map(parser::toInteraction);
	}

	@Override
	public void close() throws Exception {
		reader.close();
	}

	@Override
	public Iterator<Interaction> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		if (next != null) return true;
		try {
			final String line = reader.readLine();
			if (line == null) return false;
			next = parser.toInteraction(line);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	@Override
	public Interaction next() {
		if (hasNext()) {
			Interaction rtn = next;
			next = null;
			return rtn;
		}
		throw new NoSuchElementException();
	}

	public Stream<Interaction> interactions() {
		return StreamSupport.stream(Spliterators
				.spliteratorUnknownSize(this, Spliterator.ORDERED), false);
	}
}
