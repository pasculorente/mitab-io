package org.uichuimi.mitab.io.output;

import org.uichuimi.mitab.io.consumer.Acceptor;
import org.uichuimi.mitab.io.model.Interaction;
import org.uichuimi.mitab.io.model.Interactor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class TsvWriter implements Acceptor<Interaction>, AutoCloseable {

	private static final String SEPARATOR = "\t";
	private static final List<String> HEADERS = Arrays.asList("ID", "TYPE", "METHOD", "ORGANISM", "SCORE", "A_ID", "B_ID", "A_BIO_ROLE", "B_BIO_ROLE", "A_EXP_ROLE", "B_EXP_ROLE");

	private final Consumer<Interaction> withoutHeader = this::writePrivate;
	private final Consumer<Interaction> withHeader = this::writeWithHeader;
	private long line = 0;
	private Consumer<Interaction> writer = withHeader;

	private final PrintStream output;

	public TsvWriter(File output) throws FileNotFoundException {
		this(new PrintStream(output));
	}

	public TsvWriter(OutputStream outputStream) {
		this(new PrintStream(outputStream));
	}

	public TsvWriter(PrintStream output) {
		this.output = output;
	}

	@Override
	public void start() {

	}

	@Override
	public void accept(Interaction interaction) {
		writer.accept(interaction);
	}

	@Override
	public void close() {
		output.close();
	}

	private void writeHeader() {
		output.println(String.join(SEPARATOR, HEADERS));
	}

	private void writeWithHeader(Interaction interaction) {
		writeHeader();
		writePrivate(interaction);  
		this.writer = withoutHeader;
	}

	private void writePrivate(Interaction interaction) {
		try {
			final List<String> fields = new ArrayList<>(9);
			final String identifier = interaction.getIdentifiers().get(0).getIdentifier();
			fields.add(identifier);
			fields.add(interaction.getTypes().get(0).getIdentifier());
			fields.add(interaction.getDetectionMethods().get(0).getIdentifier());
			if (interaction.getOrganism().isEmpty()) fields.add("");
			else fields.add(interaction.getOrganism().get(0).getIdentifier());
			if (interaction.getConfidenceScores().isEmpty()) {
//				final String msg = String.format("No confidence score for interaction %s at line %d", identifier, line);
//				Logger.getLogger("mitab").log(Level.WARNING, msg);
				fields.add("");
			} else fields.add(interaction.getConfidenceScores().get(0).getValue());
			final Interactor a = interaction.getInteractorA();
			final Interactor b = interaction.getInteractorB().getPrimaryIdentifier() == null ? a : interaction.getInteractorB();
			fields.add(a.getPrimaryIdentifier().getIdentifier());
			fields.add(b.getPrimaryIdentifier().getIdentifier());

			if (a.getBiologicalRoles().isEmpty()) fields.add("");
			else fields.add(a.getBiologicalRoles().get(0).getIdentifier());

			if (b.getBiologicalRoles().isEmpty()) fields.add("");
			else fields.add(b.getBiologicalRoles().get(0).getIdentifier());

			if (a.getExperimentalRoles().isEmpty()) fields.add("");
			else fields.add(a.getExperimentalRoles().get(0).getIdentifier());
			if (b.getExperimentalRoles().isEmpty()) fields.add("");
			else fields.add(b.getExperimentalRoles().get(0).getIdentifier());

			output.println(String.join(SEPARATOR, fields));
			line++;
		} catch (RuntimeException any) {
			throw new RuntimeException("At line " + line + ": " + interaction, any);
		}
	}

}
