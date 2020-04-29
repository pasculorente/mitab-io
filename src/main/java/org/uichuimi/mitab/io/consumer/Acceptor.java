package org.uichuimi.mitab.io.consumer;

import java.util.function.Consumer;

public interface Acceptor<T> extends Consumer<T> {

	void start();

	void close();
}
