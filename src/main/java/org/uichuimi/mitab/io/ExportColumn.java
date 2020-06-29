package org.uichuimi.mitab.io;

import org.reflections.Reflections;
import org.uichuimi.mitab.io.input.PsiInteractionParser;
import org.uichuimi.mitab.io.model.Field;
import org.uichuimi.mitab.io.model.Interaction;
import org.uichuimi.mitab.io.model.Interactor;
import org.uichuimi.mitab.io.model.PsiColumn;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.function.Function;

public class ExportColumn {

	private final Function<Interaction, List<? extends Field>> fieldsExtractor;
	private final Function<List<? extends Field>, List<? extends Field>> rangeExtractor;
	private final Function<Field, String> valueExtractor;

	private final static Map<String, Class<? extends Field>> interactionColumnTypes;
	private final static Map<String, Class<? extends Field>> interactorColumnTypes;
	private final static Map<String, Function<Interaction, List<? extends Field>>> interactionIndex;
	private final static Map<String, Function<Interactor, List<? extends Field>>> interactorIndex;
	private final static Map<Class<? extends Field>, Map<String, Function<Field, String>>> propertyIndex;

	static {
		interactionColumnTypes = indexTypes(Interaction.class);
		interactorColumnTypes = indexTypes(Interactor.class);
		interactionIndex = indexColumns(Interaction.class);
		interactorIndex = indexColumns(Interactor.class);
		propertyIndex = indexProperties();
	}

	private static Map<Class<? extends Field>, Map<String, Function<Field, String>>> indexProperties() {
		final Map<Class<? extends Field>, Map<String, Function<Field, String>>> propertyIndex = new HashMap<>();
		final Reflections reflections = new Reflections("org.uichuimi.mitab.io.model");
		for (Class<? extends Field> fieldClass : reflections.getSubTypesOf(Field.class)) {
			for (Method method : fieldClass.getDeclaredMethods()) {
				final String name = method.getName().replace("get", "").toLowerCase();
				final Function<Field, String> function = field -> {
					try {
						return (String) method.invoke(field);
					} catch (IllegalAccessException | InvocationTargetException e) {
						throw new RuntimeException("Cannot invoke getter for field " + name, e);
					}
				};
				propertyIndex.computeIfAbsent(fieldClass, c -> new HashMap<>()).put(name, function);
			}
		}
		return propertyIndex;
	}

	private static <T> Map<String, Class<? extends Field>> indexTypes(Class<T> aClass) {
		final Map<String, Class<? extends Field>> map = new HashMap<>();
		for (java.lang.reflect.Field field : aClass.getDeclaredFields()) {
			if (field.isAnnotationPresent(PsiColumn.class)) {
				final java.lang.reflect.Type genericType = field.getGenericType();
				final Class<? extends Field> target;
				if (genericType instanceof ParameterizedType) {
					target = (Class<? extends Field>) ((ParameterizedType) genericType).getActualTypeArguments()[0];
				} else {
					target = (Class<? extends Field>) genericType;
				}
				for (String value : field.getAnnotation(PsiColumn.class).value()) {
					map.put(value, target);
				}
			}
		}
		return map;
	}

	private static <T> Map<String, Function<T, List<? extends Field>>> indexColumns(Class<T> tClass) {
		final Map<String, Function<T, List<? extends Field>>> map = new TreeMap<>();
		for (java.lang.reflect.Field field : tClass.getDeclaredFields()) {
			if (field.isAnnotationPresent(PsiColumn.class)) {
				try {
					final Method getter = new PropertyDescriptor(field.getName(), tClass).getReadMethod();
					final Function<T, List<? extends Field>> function = interactor -> {
						try {
							final Object val = getter.invoke(interactor);
							if (val == null) return List.of();
							else if (val instanceof List) return (List<? extends Field>) val;
							else return List.of((Field) val);
						} catch (IllegalAccessException | InvocationTargetException e) {
							throw new RuntimeException("Cannot invoke getter for field " + field.getName(), e);
						}
					};
					for (String value : field.getAnnotation(PsiColumn.class).value()) {
						if (map.containsKey(value))
							throw new IllegalStateException("Duplicated column annotation " + value);
						map.put(value, function);
					}
				} catch (IntrospectionException e) {
					throw new RuntimeException(field.getName() + " field must have a getter", e);
				}
			}
		}
		return map;
	}

	public ExportColumn(String interactor, String column, String property, Integer from, Boolean ranged, Integer to) {
		this.fieldsExtractor = createFieldsExtractor(column, interactor);
		this.valueExtractor = createPropertyExtractor(gettype(column, interactor), property);
		this.rangeExtractor = createRangeExtractor(from, ranged, to);
	}

	private Class<? extends Field> gettype(String column, String interactor) {
		return interactor == null
				? interactionColumnTypes.get(column)
				: interactorColumnTypes.get(column);
	}

	private Function<Interaction, List<? extends Field>> createFieldsExtractor(String column, String interactor) {
		if (interactor == null) {
			return interactionIndex.get(column);
		} else {
			final Function<Interaction, Interactor> interactorGetter = interactor.equals("a")
					? Interaction::getInteractorA
					: Interaction::getInteractorB;
			return interactorGetter.andThen(interactorIndex.get(column));
		}
	}

	private Function<List<? extends Field>, List<? extends Field>> createRangeExtractor(Integer from, Boolean ranged, Integer to) {
		// By default, return the same
		if (from == null && ranged == null)
			return fields -> fields;
		// From can be: null, positive or negative
		// ranged can be: true or false
		// Negative can be: null, positive or negative
		// 18 combinations
		// if ranged is false, negative is null -> 16
		return fields -> {
			int start = from == null ? 0 : from;
			if (start < 0) start = fields.size() + start;
			int end = to == null ? ranged ? fields.size() : start + 1 : to;
			if (end < 0) end = fields.size() + end;
			return getRange(fields, start, end);
		};
	}

	private <T> List<T> getRange(List<T> fields, int from, int to) {
		final List<T> rtn = new ArrayList<>();
		int i = from;
		while (i < fields.size() && i < to) rtn.add(fields.get(i++));
		return rtn;
	}

	private Function<Field, String> createPropertyExtractor(Class<? extends Field> tClass, String property) {
		if (property == null) return field -> PsiInteractionParser.instance().toString(field);
		final Function<Field, String> function;
		switch (property) {
			case "xref" -> function = Field::getXref;
			case "value" -> function = Field::getValue;
			case "description" -> function = Field::getDescription;
			default -> {
				if (!propertyIndex.get(tClass).containsKey(property))
					throw new IllegalArgumentException(tClass.getSimpleName()
							+ " does not have " + property + ": " 
							+ propertyIndex.get(tClass).keySet());
				function = propertyIndex.get(tClass).get(property);
			}
		}
		return function;
	}


	String get(Interaction interaction) {
		// 1: get a list of fields, using name and interactor
		List<? extends Field> fields = fieldsExtractor.apply(interaction);
		// 2: get first n elements
		fields = rangeExtractor.apply(fields);
		// 3: get value from each element
		List<String> values = extractValues(fields);
		return String.join("|", values);
	}

	private List<String> extractValues(List<? extends Field> fields) {
		final List<String> rtn = new ArrayList<>();
		for (Field field : fields) rtn.add(valueExtractor.apply(field));
		return rtn;
	}
}
