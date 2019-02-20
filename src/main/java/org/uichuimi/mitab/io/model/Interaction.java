package org.uichuimi.mitab.io.model;

import java.util.Collections;
import java.util.List;

/**
 * Represents a MITAB line. An interaction is an array of lists of Fields: <code>List&lt;Field&gt;[]</code>. To access
 * a column use {@link Interaction#get(int)} or {@link Interaction#get(ColumnName)}. This class can also be used for
 * writing, using setters.
 */
public class Interaction {

	private final List[] values;

	private Interactor interactorA;
	private Interactor interactorB;
	private List<Author> authors = Collections.emptyList();
	private List<DetectionMethod> detectionMethods = Collections.emptyList();
	private List<Publication> publications = Collections.emptyList();
	private List<Type> types = Collections.emptyList();
	private List<Database> databases = Collections.emptyList();
	private List<Identifier> identifiers = Collections.emptyList();
	private List<CrossReference> crossReferences = Collections.emptyList();
	private List<Annotation> annotations = Collections.emptyList();
	private List<Species> organism = Collections.emptyList();
	private List<ConfidenceScore> confidenceScores = Collections.emptyList();
	private List<ComplexExpansion> complexExpansion = Collections.emptyList();
	private List<Parameter> parameters = Collections.emptyList();
	private List<Date> creation = Collections.emptyList();
	private List<Date> update = Collections.emptyList();
	private List<Checksum> checksums = Collections.emptyList();
	private List<Negative> negative = Collections.emptyList();



	private Interaction(Interactor interactorA, Interactor interactorB) {
		this.interactorA = interactorA;
		this.interactorB = interactorB;
		values = new List[0];
	}

	public Interactor getInteractorA() {
		return interactorA;
	}

	public Interactor getInteractorB() {
		return interactorB;
	}

	public void setInteractorA(Interactor interactorA) {
		this.interactorA = interactorA;
	}

	public void setInteractorB(Interactor interactorB) {
		this.interactorB = interactorB;
	}

	public Interaction(int size) {
		this.values = new List[size];
	}

	public void set(int column, List<Field> value) {
		values[column] = value;
	}

	public void set(ColumnName columnName, List<Field> value ) {
		columnName.set(this, value);
	}

	public List<Author> getAuthors() {
		return authors;
	}

	/**
	 * @return a list of the fields in column. If no fields are present, returns
	 * an empty list.
	 */
	public List<Field> get(int column) {
		return values[column] == null
				? Collections.emptyList()
				: values[column];
	}

	/**
	 * @return a list of the fields in columnName. If no fields are present,
	 * returns an empty list.
	 */
	public List<Field> get(ColumnName columnName) {
		final List<Field> fields = columnName.get(this);
		if (fields == null) return Collections.emptyList();
		return fields;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public List<DetectionMethod> getDetectionMethods() {
		return detectionMethods;
	}

	public void setDetectionMethods(List<DetectionMethod> detectionMethods) {
		this.detectionMethods = detectionMethods;
	}

	public List<Publication> getPublications() {
		return publications;
	}

	public void setPublications(List<Publication> publications) {
		this.publications = publications;
	}

	public List<Type> getTypes() {
		return types;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}

	public List<Database> getDatabases() {
		return databases;
	}

	public void setDatabases(List<Database> databases) {
		this.databases = databases;
	}

	public List<Identifier> getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(List<Identifier> identifiers) {
		this.identifiers = identifiers;
	}

	public List<CrossReference> getCrossReferences() {
		return crossReferences;
	}

	public void setCrossReferences(List<CrossReference> crossReferences) {
		this.crossReferences = crossReferences;
	}

	public List<Annotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}

	public List<Species> getOrganism() {
		return organism;
	}

	public void setOrganism(List<Species> organism) {
		this.organism = organism;
	}

	public List<ConfidenceScore> getConfidenceScores() {
		return confidenceScores;
	}

	public void setConfidenceScores(List<ConfidenceScore> confidenceScores) {
		this.confidenceScores = confidenceScores;
	}

	public List<ComplexExpansion> getComplexExpansion() {
		return complexExpansion;
	}

	public void setComplexExpansion(List<ComplexExpansion> complexExpansion) {
		this.complexExpansion = complexExpansion;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public List<Date> getCreation() {
		return creation;
	}

	public void setCreation(List<Date> creation) {
		this.creation = creation;
	}

	public List<Date> getUpdate() {
		return update;
	}

	public void setUpdate(List<Date> update) {
		this.update = update;
	}

	public List<Checksum> getChecksums() {
		return checksums;
	}

	public void setChecksums(List<Checksum> checksums) {
		this.checksums = checksums;
	}

	public List<Negative> getNegative() {
		return negative;
	}

	public void setNegative(List<Negative> negative) {
		this.negative = negative;
	}
}
