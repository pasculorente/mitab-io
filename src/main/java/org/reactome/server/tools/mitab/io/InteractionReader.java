package org.reactome.server.tools.mitab.io;

import org.reactome.server.tools.mitab.io.input.PsiInteractionParser;
import org.reactome.server.tools.mitab.io.model.Interaction;
import org.reactome.server.tools.mitab.io.model.PsiMitabVersion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

@SuppressWarnings("WeakerAccess")
public class InteractionReader {

	/**
	 * Reads a PSI-MITAB input in TAB_27 version.
	 */
	public static Stream<Interaction> read(InputStream inputStream) throws IOException {
		return read(inputStream, PsiMitabVersion.TAB_27);
	}

	/**
	 * Reads a PSI-MITAB input stream, generating an interaction out of each
	 * line.
	 */
	public static Stream<Interaction> read(InputStream inputStream, PsiMitabVersion version) throws IOException {
		final PsiInteractionParser parser = PsiInteractionParser.instance(version);
		final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		reader.readLine(); // Skip header
		return reader.lines().map(parser::toInteraction);
	}

}
