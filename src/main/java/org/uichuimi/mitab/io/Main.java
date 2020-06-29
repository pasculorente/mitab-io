package org.uichuimi.mitab.io;

import org.uichuimi.mitab.io.consumer.Acceptor;
import org.uichuimi.mitab.io.consumer.Progress;
import org.uichuimi.mitab.io.consumer.Stats;
import org.uichuimi.mitab.io.input.InteractionReader;
import org.uichuimi.mitab.io.input.SelectParser;
import org.uichuimi.mitab.io.model.Interaction;
import org.uichuimi.mitab.io.output.Neo4jWriter;
import org.uichuimi.mitab.io.output.TsvWriter;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static picocli.CommandLine.Option;

@Command(name = "mitab",
		version = "mitab version 1.0",
		description = "umpteenth package with tools to work with PSI MITAB files")
public class Main implements Callable<Integer> {

	@Option(names = {"-h", "--help"}, usageHelp = true, description = "display this help message")
	boolean usageHelpRequested;

	@Option(names = {"-v", "--verbose"})
	private boolean verbose;

	@Option(names = {"-i", "--input"}, description = "Input file (PSI-MI TAB format)")
	private File input;

	@Option(names = {"-o", "--output"}, description = "Output file (tsv)")
	private File output;

	@Option(names = {"--neo4j"}, description = "Neo4j directory")
	private File neo4j;

	@Option(names = {"--select"}, arity = "*", split = ",",
			description = "Specifies one or more columns to export")
	private List<String> selection;
	
	@Option(names = {"--stats"}, description = "Show stats at the end of the process. By default")
	private boolean stats;

	private long start;

	public static void main(String[] args) {
		System.exit(new CommandLine(new Main()).execute(args));
	}

	@Override
	public Integer call() throws Exception {
		final PrintStream console = output == null ? System.err : System.out;
		if (verbose) {
			console.println("input : " + input);
			console.println("output: " + output);
		}

		final List<Selector> selectors = parseColumns();

		final InputStream in = input == null ? System.in : FileUtils.getInputStream(input);
		final OutputStream out = output == null ? System.out : FileUtils.getOutputStream(output);

		final List<Acceptor<Interaction>> consumers = new ArrayList<>();
		if (stats)
			consumers.add(new Stats(console));
		if (neo4j != null)
			consumers.add(new Neo4jWriter(neo4j));
		consumers.add(new Progress(console));
		consumers.add(new TsvWriter(out, selectors));

		try (InteractionReader reader = new InteractionReader(in)) {
			consumers.forEach(Acceptor::start);
			for (Interaction interaction : reader)
				for (Acceptor<Interaction> consumer : consumers)
					consumer.accept(interaction);
			console.println();
			consumers.forEach(Acceptor::close);
		}
		return 0;
	}

	private List<Selector> parseColumns() {
		if (selection == null || selection.isEmpty()) return null;
		final List<Selector> exportColumns = new ArrayList<>();
		for (String column : selection)
			exportColumns.add(parseColumn(column));
		return exportColumns;
	}

	private Selector parseColumn(String configuration) {
		if (configuration.isBlank())
			throw new IllegalArgumentException("column configuration must not be empty");
		return SelectParser.parse(configuration);
	}


}
