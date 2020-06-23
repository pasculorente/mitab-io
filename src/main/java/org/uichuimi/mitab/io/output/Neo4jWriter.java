package org.uichuimi.mitab.io.output;

import org.uichuimi.mitab.io.FileUtils;
import org.uichuimi.mitab.io.consumer.Acceptor;
import org.uichuimi.mitab.io.model.Interaction;
import org.uichuimi.mitab.io.model.Interactor;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class Neo4jWriter implements Acceptor<Interaction>, AutoCloseable {

	private static final String SEPARATOR = "\t";
	private final PrintStream nodes;
	private final PrintStream relationships;
	private final TreeSet<String> interactors = new TreeSet<>();
	private final Map<String, List<String>> uniques = new TreeMap<>();

	public Neo4jWriter(File path) throws IOException {
		if (!path.exists() && !path.mkdirs())
			throw new IllegalArgumentException(path + " is not accessible");
		nodes = new PrintStream(FileUtils.getOutputStream(new File(path, "nodes.tsv.gz")));
		relationships = new PrintStream(FileUtils.getOutputStream(new File(path, "relationships.tsv.gz")));
		nodes.println(String.join("\t", Collections.singletonList("identifier:ID(interactor)")));
		relationships.println(String.join("\t", Arrays.asList(":START_ID(gene)", ":END_ID(gene)", "identifier", "type", "method", "score")));
	}

	@Override
	public void start() {
	}

	@Override
	public void close() {
		for (List<String> fields : uniques.values())
			relationships.println(String.join(SEPARATOR, fields));
		for (String interactor : interactors)
			nodes.println(interactor);

		nodes.close();
		relationships.close();
	}

	@Override
	public void accept(Interaction interaction) {
		if (interaction.getInteractorB().getPrimaryIdentifier() == null) return;
		if (interaction.getConfidenceScores().isEmpty()) return;
		final Interactor a = interaction.getInteractorA();
		final Interactor b = interaction.getInteractorB();

//		final String aId = getGeneId(a);
//		final String bId = getGeneId(b);
		final String aId = a.getPrimaryIdentifier().get(0).getIdentifier();
		final String bId = b.getPrimaryIdentifier().get(0).getIdentifier();
		if (aId == null) return;
		if (bId == null) return;
		if (aId.equals(bId)) return;
		String hash = aId.compareTo(bId) > 0 ? aId + ":" + bId : bId + ":" + aId;
		double confidence;
		final String cs = interaction.getConfidenceScores().get(0).getValue();
		try {
			confidence = cs == null ? 0.0 : Double.parseDouble(cs);
		} catch (NumberFormatException ex) {
			return;
		}
		final List<String> row = Arrays.asList(aId, bId,
				interaction.getIdentifiers().get(0).getIdentifier(),
				interaction.getTypes().get(0).getIdentifier(),
				interaction.getDetectionMethods().get(0).getIdentifier(),
				cs);

		final List<String> current = uniques.get(hash);
		if (current == null) uniques.put(hash, row);
		else if (current.get(5) == null || confidence > Double.parseDouble(current.get(5)))
			uniques.put(hash, row);
		interactors.add(aId);
		interactors.add(bId);
	}

}
