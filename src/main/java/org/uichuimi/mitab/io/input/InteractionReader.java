package org.uichuimi.mitab.io.input;

import org.uichuimi.mitab.io.model.Interaction;
import org.uichuimi.mitab.io.model.PsiMitabVersion;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.zip.GZIPInputStream;

@SuppressWarnings("WeakerAccess")
public class InteractionReader implements AutoCloseable, Iterable<Interaction>, Iterator<Interaction> {

	private final BufferedReader reader;
	private final PsiInteractionParser parser;
	private Interaction next;
	private long line = 0;

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
			try {
				next = parser.toInteraction(line);
				this.line += 1;
			} catch (RuntimeException ex) {
				throw new RuntimeException("At line " + this.line, ex);
			}
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

	public List<Interaction> readAll() {
		return interactions().collect(Collectors.toList());
	}
}
