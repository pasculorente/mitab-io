package org.uichuimi.mitab.io.consumer;

import org.uichuimi.mitab.io.model.Interaction;

import java.io.PrintStream;

public class Progress implements Acceptor<Interaction> {

	private long start;
	private final PrintStream console;
	private long line = 0;

	public Progress(PrintStream console) {
		this.console = console;
	}

	@Override
	public void start() {
		start = System.currentTimeMillis();
	}

	@Override
	public void accept(Interaction interaction) {
		if (++line % 1000 == 0)
			console.printf("\r%s\t%,d", time(System.currentTimeMillis() - start), line);
	}


	private String time(long millis) {
		long seconds = millis / 1000;
		long minutes = seconds / 60;
		seconds -= minutes * 60;
		long hours = minutes / 60;
		minutes -= hours * 60;
		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}

	@Override
	public void close() {
		console.printf("\r%s\t%,d", time(System.currentTimeMillis() - start), line);
	}
}
