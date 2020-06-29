package org.uichuimi.mitab.io;

import org.uichuimi.mitab.io.consumer.Acceptor;
import org.uichuimi.mitab.io.consumer.Progress;
import org.uichuimi.mitab.io.consumer.Stats;
import org.uichuimi.mitab.io.input.InteractionReader;
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

	private static final List<ExportColumn> DEFAULT_COLUMNS = List.of(
//			new ExportColumn()
	);
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

	@Option(names = {"--column"}, description = "Choose a column to export", arity = "*")
	private List<String> columnSpecs;

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

		final List<ExportColumn> columns = parseColumns();

		final InputStream in = input == null ? System.in : FileUtils.getInputStream(input);
		final OutputStream out = output == null ? System.out : FileUtils.getOutputStream(output);

		final List<Acceptor<Interaction>> consumers = new ArrayList<>();
		final Stats stats = new Stats(console);
		final Progress progress = new Progress(console);
		consumers.add(stats);
		consumers.add(progress);
		consumers.add(new TsvWriter(out));
		if (neo4j != null) consumers.add(new Neo4jWriter(neo4j));

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

	private List<ExportColumn> parseColumns() {
		if (columnSpecs == null || columnSpecs.isEmpty()) return DEFAULT_COLUMNS;
		final List<ExportColumn> exportColumns = new ArrayList<>();
		for (String column : columnSpecs)
			exportColumns.add(parseColumn(column));
		return exportColumns;
	}

	private ExportColumn parseColumn(String configuration) {
		if (configuration.isBlank())
			throw new IllegalArgumentException("column configuration must not be empty");
		
		final String[] fields = configuration.split(":");
		final String name = fields[0].toLowerCase();
		
		if (fields.length > 1) {
			
		}
			return null;
	}


}
