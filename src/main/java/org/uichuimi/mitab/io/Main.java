package org.uichuimi.mitab.io;

import org.uichuimi.mitab.io.consumer.Acceptor;
import org.uichuimi.mitab.io.consumer.Neo4jWriter;
import org.uichuimi.mitab.io.consumer.Progress;
import org.uichuimi.mitab.io.consumer.Stats;
import org.uichuimi.mitab.io.model.Interaction;
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

	@Option(names = {"-i", "--input"})
	private File input;

	@Option(names = {"-o", "--output"})
	private File output;

	@Option(names = {"--neo4j"})
	private File neo4j;

	private long start;

	public static void main(String[] args) {
		int exitCode = new CommandLine(new Main()).execute(args);
		System.exit(exitCode);
	}

	@Override
	public Integer call() throws Exception {
		final PrintStream console = output == null ? System.err : System.out;
		if (verbose) {
			console.println("input : " + input);
			console.println("output: " + output);
		}

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


}
