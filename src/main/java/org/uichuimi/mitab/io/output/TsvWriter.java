package org.uichuimi.mitab.io.output;

import org.uichuimi.mitab.io.Selector;
import org.uichuimi.mitab.io.consumer.Acceptor;
import org.uichuimi.mitab.io.model.Interaction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class TsvWriter implements Acceptor<Interaction>, AutoCloseable {

	private static final List<Selector> DEFAULT_COLUMNS = List.of(
			new Selector(null, "identifier", "value", 0, false, null),
			new Selector(null, "type", "value", 0, false, null),
			new Selector(null, "method", "value", 0, false, null),
			new Selector(null, "organism", "value", 0, false, null),
			new Selector(null, "score", "value", 0, false, null),
			new Selector("a", "identifier", "value", 0, false, null),
			new Selector("b", "identifier", "value", 0, false, null),
			new Selector("a", "biorole", "value", 0, false, null),
			new Selector("b", "biorole", "value", 0, false, null),
			new Selector("a", "exprole", "value", 0, false, null),
			new Selector("b", "exprole", "value", 0, false, null)
	);

	private static final String SEPARATOR = "\t";
	private static final List<String> HEADERS = Arrays.asList("ID", "TYPE", "METHOD", "ORGANISM", "SCORE", "A_ID", "B_ID", "A_BIO_ROLE", "B_BIO_ROLE", "A_EXP_ROLE", "B_EXP_ROLE");

	private final Consumer<Interaction> withoutHeader = this::writePrivate;
	private final Consumer<Interaction> withHeader = this::writeWithHeader;
	private final List<Selector> selectors;
	private long line = 0;
	private Consumer<Interaction> writer = withHeader;

	private final PrintStream output;

	public TsvWriter(File output, List<Selector> selectors) throws FileNotFoundException {
		this(new PrintStream(output), selectors);
	}
	
	public TsvWriter(File output) throws  FileNotFoundException {
		this(output, DEFAULT_COLUMNS);
	}

	public TsvWriter(OutputStream outputStream, List<Selector> selectors) {
		this(new PrintStream(outputStream), selectors);
	}
	
	public TsvWriter(OutputStream outputStream) {
		this(outputStream, DEFAULT_COLUMNS);
	}

	public TsvWriter(PrintStream output, List<Selector> selectors) {
		this.output = output;
		this.selectors = selectors;
	}
	
	public TsvWriter(PrintStream output) {
		this(output, DEFAULT_COLUMNS);
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
			final List<String> fields = new ArrayList<>(selectors.size());
			for (Selector selector : selectors) {
				final String value = selector.select(interaction);
				fields.add(value);
			}
			output.println(String.join(SEPARATOR, fields));
			line++;
		} catch (RuntimeException any) {
			throw new RuntimeException("At line " + line + ": " + interaction, any);
		}
	}

}
