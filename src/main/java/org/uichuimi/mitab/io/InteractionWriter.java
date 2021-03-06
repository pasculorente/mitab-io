package org.uichuimi.mitab.io;

import org.uichuimi.mitab.io.input.PsiInteractionParser;
import org.uichuimi.mitab.io.model.Interaction;
import org.uichuimi.mitab.io.model.PsiMitabVersion;

import java.io.*;

public class InteractionWriter implements AutoCloseable {

	private final PrintStream printStream;
	private PsiInteractionParser parser;

	public InteractionWriter(OutputStream outputStream, PsiMitabVersion version) {
		printStream = new PrintStream(outputStream);
		parser = PsiInteractionParser.instance(version);
		printStream.println("#" + parser.headerLine());
	}

	public InteractionWriter(OutputStream outputStream) {
		printStream = new PrintStream(outputStream);
		parser = PsiInteractionParser.instance();
	}

	public InteractionWriter(File file) throws FileNotFoundException {
		this(new FileOutputStream(file));
	}

	public void write(Interaction interaction) {
		printStream.println(parser.toString(interaction));
	}

	@Override
	public void close() {
		printStream.close();
	}
}
