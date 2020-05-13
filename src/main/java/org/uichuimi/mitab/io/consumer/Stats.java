package org.uichuimi.mitab.io.consumer;

import org.uichuimi.mitab.io.model.*;

import java.io.PrintStream;
import java.util.*;

public class Stats implements Acceptor<Interaction> {

	private static final String KEY_VALUE_SEPARATOR = "=";
	private static final String NEWLINE = System.lineSeparator();
	private final PrintStream console;
	private final Collection<String> interactors = new TreeSet<>();
	private final Map<String, String> bio = new TreeMap<>();
	private final Map<String, String> exp = new TreeMap<>();
	private final Map<String, String> types = new TreeMap<>();
	private final Map<String, String> methods = new TreeMap<>();
	private long lines = 0;

	public Stats(PrintStream console) {
		this.console = console;
	}

	@Override
	public void start() {

	}

	@Override
	public void accept(Interaction interaction) {
		lines++;
		final List<DetectionMethod> methods = interaction.getDetectionMethods();
		if (!methods.isEmpty())
			this.methods.putIfAbsent(methods.get(0).getIdentifier(), methods.get(0).getName());

		final List<Type> types = interaction.getTypes();
		if (!types.isEmpty())
			this.types.putIfAbsent(types.get(0).getIdentifier(), types.get(0).getName());

		accept(interaction.getInteractorA());
		accept(interaction.getInteractorB());
	}

	private void accept(Interactor interactor) {
		if (interactor.getPrimaryIdentifier() == null) return;
		interactors.add(interactor.getPrimaryIdentifier().getIdentifier());

		final List<BiologicalRole> bioRoles = interactor.getBiologicalRoles();
		if (!bioRoles.isEmpty())
			bio.putIfAbsent(bioRoles.get(0).getIdentifier(), bioRoles.get(0).getDescription());

		final List<ExperimentalRole> expRoles = interactor.getExperimentalRoles();
		if (!expRoles.isEmpty())
			exp.putIfAbsent(expRoles.get(0).getIdentifier(), expRoles.get(0).getName());
	}

	@Override
	public void close() {
		console.println(summary());
	}

	public String summary() {
		final StringBuilder builder = new StringBuilder();
		builder.append(interactors.size()).append(" interactors").append(NEWLINE)
				.append(lines).append(" interactions").append(NEWLINE)
				.append("Methods (").append(methods.size()).append(")").append(NEWLINE);
		methods.forEach((key, value) -> append(builder, key, value));
		builder.append("Types (").append(types.size()).append(")").append(NEWLINE);
		types.forEach((key, value) -> append(builder, key, value));
		builder.append("Biological roles (").append(bio.size()).append(")").append(NEWLINE);
		bio.forEach((key, value) -> append(builder, key, value));
		builder.append("Experimental roles (").append(exp.size()).append(")").append(NEWLINE);
		exp.forEach((key, value) -> append(builder, key, value));
		return builder.toString();
	}

	private void append(StringBuilder builder, String key, String value) {
		builder.append("\t").append(key).append(KEY_VALUE_SEPARATOR).append(value).append(NEWLINE);
	}
}
