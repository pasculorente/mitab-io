package org.reactome.server.tools.mitab.io.input;

import org.reactome.server.tools.mitab.io.model.Field;
import org.reactome.server.tools.mitab.io.model.Interaction;

import java.util.List;

public interface InteractionParser {

	Interaction toInteraction(String line);

	List<Field> parseField(String field);

	String toString(Interaction interaction);

	int getNumberOfColumns();

}
