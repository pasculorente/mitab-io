package org.reactome.server.tools.mitab.io;

import org.reactome.server.tools.mitab.io.input.TabInteractionParser;
import org.reactome.server.tools.mitab.io.model.Interaction;
import org.reactome.server.tools.mitab.io.model.PsiMitabVersion;

import java.io.OutputStream;
import java.io.PrintStream;

public class InteractionWriter {

	private final PrintStream printStream;
	private TabInteractionParser parser;

	public InteractionWriter(OutputStream outputStream, PsiMitabVersion version) {
		printStream = new PrintStream(outputStream);
		parser = TabInteractionParser.instance(version);
		printStream.println("#" + parser.headerLine());
	}

	public InteractionWriter(OutputStream outputStream) {
		printStream = new PrintStream(outputStream);
		parser = TabInteractionParser.instance();
	}

	public void write(Interaction interaction) {
		printStream.println(parser.toString(interaction));
	}

}
