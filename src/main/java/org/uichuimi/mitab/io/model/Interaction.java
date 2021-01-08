package org.uichuimi.mitab.io.model;

import java.util.Collections;
import java.util.List;

/**
 * Represents a MITAB line. An interaction is an array of lists of Fields:
 * <code>List&lt;Field&gt;[]</code>.
 */
public class Interaction {

	private Interactor interactorA = new Interactor();
	private Interactor interactorB = new Interactor();
	@PsiColumn({"author", "authors"})
	private List<Author> authors = Collections.emptyList();
	@PsiColumn({"method", "methods"})
	private List<DetectionMethod> detectionMethods = Collections.emptyList();
	@PsiColumn({"publication", "publications"})
	private List<Publication> publications = Collections.emptyList();
	@PsiColumn({"type", "types"})
	private List<Type> types = Collections.emptyList();
	@PsiColumn({"database", "databases"})
	private List<Database> databases = Collections.emptyList();
	@PsiColumn({"identifier", "identifiers"})
	private List<Identifier> identifiers = Collections.emptyList();
	@PsiColumn({"crossRef", "xref", "crossReferences"})
	private List<CrossReference> crossReferences = Collections.emptyList();
	@PsiColumn({"annotation", "annotations"})
	private List<Annotation> annotations = Collections.emptyList();
	@PsiColumn({"organism"})
	private List<Organism> organism = Collections.emptyList();
	@PsiColumn({"score", "confidence"})
	private List<ConfidenceScore> confidenceScores = Collections.emptyList();
	@PsiColumn({"expansion", "complexExpansion"})
	private List<ComplexExpansion> complexExpansion = Collections.emptyList();
	@PsiColumn({"parameter", "parameters"})
	private List<Parameter> parameters = Collections.emptyList();
	@PsiColumn({"creation"})
	private Date creation;
	@PsiColumn({"update"})
	private Date update;
	@PsiColumn({"checksum"})
	private List<Checksum> checksums = Collections.emptyList();
	@PsiColumn({"negative"})
	private Negative negative;
	@PsiColumn({"causalRegulatoryMechanism", "regulatoryMechanism", "mechanism"})
	private List<CausalRegulatoryMechanism> causalRegulatoryMechanism;
	@PsiColumn({"causalStatement", "statement"})
	private List<CausalStatement> causalStatement;

	public Interaction(Interactor interactorA, Interactor interactorB) {
		this.interactorA = interactorA;
		this.interactorB = interactorB;
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

	public Interaction() {
	}

	/**
	 * First author surname(s) of the publication(s) followed by 'et al.' and the publication year
	 * in parenthesis, e.g. Ciferri et al.(2005).
	 */
	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	/**
	 * Interaction detection methods. It is recommended by MIMIx and can be used for scoring
	 * interactions so it is recommended to always give this information. It is also recommended to
	 * give one interaction detection method per MITAB line (for clustering purposes).
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>database</strong>. Should always be <code>psi-mi</code>.</dd>
	 * <dt>value</dt>
	 * <dd><strong>identifier</strong>. Taken from the corresponding <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI:0001&termName=interaction%20detection%20method>
	 * PSI-MI controlled Vocabulary</a>.</dd>
	 * <dt>description</dt>
	 * <dd><strong>method name</strong>. The name corresponding to identifier.</dd>
	 * </dl>
	 * <p>
	 * Ex: psi-mi:"MI:0006"(anti bait coimmunoprecipitation)
	 */
	public List<DetectionMethod> getDetectionMethods() {
		return detectionMethods;
	}

	public void setDetectionMethods(List<DetectionMethod> detectionMethods) {
		this.detectionMethods = detectionMethods;
	}

	/**
	 * Identifier of the publication in which this interaction has been shown. The publication
	 * identifier is used for clustering interactions from different data providers in PSICQUIC and
	 * can be used for scoring interactions so it is recommended to always give this information. It
	 * is recommended to give one pubmed id per MITAB line and IMEx ids can be added.
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>database</strong>. Taken from <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI:0444&termName=database%20citation>PSI-MI
	 * controlled vocabulary</a>.</dd>
	 * <dt>value</dt>
	 * <dd><strong>identifier</strong></dd>
	 * </dl>
	 * <p>
	 * Ex: pubmed:16980971|imex:IM-1
	 */
	public List<Publication> getPublications() {
		return publications;
	}

	public void setPublications(List<Publication> publications) {
		this.publications = publications;
	}

	/**
	 * Interaction types, taken from the corresponding. Interaction type can be used for scoring
	 * interactions so it is recommended to always give this information. It is also recommended to
	 * give one interaction type per MITAB line (for clustering purposes).
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>database</strong>. Should always be <code>psi-mi</code>.</dd>
	 * <dt>value</dt>
	 * <dd><strong>identifier</strong>. Taken from <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI:0190&termName=interaction%20type>PSI-MI
	 * controlled vocabulary</a></dd>
	 * <dt>description</dt>
	 * <dd><strong>type</strong>. name of the interaction type. Corresponding to the identifier.</dd>
	 * </dl>
	 * <p>
	 * Ex: psi-mi:"MI:0914"(association)
	 */
	public List<Type> getTypes() {
		return types;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}

	/**
	 * Source databases and identifiers. As the detection methods are taken from the PSI-MI
	 * ontology, the database name is 'psi-mi'. When the interaction has been imported and reported
	 * by different sources, it is recommended to give the original source plus the source that
	 * currently reports the interaction.
	 *
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>database</strong>. Should always be <code>psi-mi</code>.</dd>
	 * <dt>value</dt>
	 * <dd><strong>identifier</strong>. Taken from <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI:0444&termName=database%20citation>PSI-MI
	 * controlled vocabulary</a></dd>
	 * <dt>description</dt>
	 * <dd><strong>name</strong>. database name.</dd>
	 * </dl>
	 * <p>
	 * Ex: psi-mi:"MI:0469"(intact)|psi-mi:"MI:0923"(irefindex)
	 */
	public List<Database> getDatabases() {
		return databases;
	}

	public void setDatabases(List<Database> databases) {
		this.databases = databases;
	}

	/**
	 * Interaction identifier(s) in the corresponding source database. It is recommended to always
	 * give a unique identifier per interaction (binary and n-ary) so in case of complexes which
	 * have been expanded, it would be possible to retrieve and re-build the original complex based
	 * on the interaction identifier. IrefIndex IDs go into the column {@link
	 * Interaction#getChecksums()}. Other interaction references such as GO references go into the
	 * column {@link Interaction#getCrossReferences()}.
	 *
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>database</strong>. database name.</dd>
	 * <dt>value</dt>
	 * <dd><strong>identifier</strong></dd>
	 * </dl>
	 * <p>
	 * Ex: intact:EBI-1547321|imex:IM-11865-3
	 */
	public List<Identifier> getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(List<Identifier> identifiers) {
		this.identifiers = identifiers;
	}

	/**
	 * Xref for the interaction. For example the gene ontology cross references associated
	 * (components, etc.) or OMIM cross references. Note, that this field is not meant to store
	 * interaction accessions or interaction checksums.
	 *
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>database</strong>. Taken from <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI:0444&termName=database%20citation>PSI-MI
	 * controlled vocabulary</a>.</dd>
	 * <dt>value</dt>
	 * <dd><strong>ac</strong>. Primary accession of the interaction in the database</dd>
	 * <dt>description</dt>
	 * <dd><strong>text</strong>. The text can be used to describe the qualifier type of the cross reference (see the
	 * corresponding <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI%3A0353&termName=cross-reference%20type>PSI-MI
	 * controlled vocabulary</a>) or could be used to give the name of the GO term in case of cross references to
	 * ontology databases</dd>
	 * </dl>
	 * <p>
	 * Ex: go:"GO:0005643"(nuclear pore)
	 */
	public List<CrossReference> getCrossReferences() {
		return crossReferences;
	}

	public void setCrossReferences(List<CrossReference> crossReferences) {
		this.crossReferences = crossReferences;
	}

	/**
	 * Annotations for the interaction. This column would also be used for tagging interactions and
	 * in such a case, topics for tags are also defined in the <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI%3A1045&termName=curation%20content>PSI-MI
	 * controlled vocabulary</a> except for the complex expansion tags that have their own column
	 * ({@link Interaction#getComplexExpansion()}).
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>topic</strong>. Name of the topic as defined in the <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI%3A0590&termName=attribute%20name>
	 * PSI-MI controlled vocabulary</a>.</dd>
	 * <dt>value</dt>
	 * <dd><strong>text</strong>: free text associated with the topic (linebreak and other MITAB reserved characters
	 * should be properly escaped, replaced and/or removed). The text is optional and only a topic could be given.</dd>
	 * </dl>
	 * <p>
	 * Ex: figure legend:"Supp Tables 1 and 2"
	 * <p>
	 * Ex: internally-curated
	 */
	public List<Annotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}

	/**
	 * NCBI Taxonomy identifier for the host organism. Cells and tissues cannot be described in this
	 * column. If both scientific name and common name are given, they should be represented with :
	 * taxid:id1(common name1)|taxid:id1(scientific name1). Note: Currently no taxonomy identifiers
	 * other than NCBI taxid are anticipated, apart from the use of -1 to indicate "in vitro", -2 to
	 * indicate "chemical synthesis", -3 indicates "unknown", -4 indicates "in vivo" and -5
	 * indicates "in silico".
	 *
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>taxid</strong>. Should always be <code>taxid</code>.</dd>
	 * <dt>value</dt>
	 * <dd><strong>identifier</strong>. taxon id of the organism taxon id of the organism.</dd>
	 * <dt>description</dt>
	 * <dd><strong>organism name</strong>. Either the common name or scientific name.</dd>
	 * </dl>
	 * <p>
	 * Ex: <code>taxid:9606(Human)|taxid:9606(Homo sapiens)</code>
	 */
	public List<Organism> getOrganism() {
		return organism;
	}

	public void setOrganism(List<Organism> organism) {
		this.organism = organism;
	}

	/**
	 * Confidence score.
	 *
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>type</strong>. Taken from <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI%3A1064&termName=interaction%20confidence>PSI-MI
	 * controlled vocabulary</a></dd>
	 * <dt>value</dt>
	 * <dd><strong>value</strong></dd>
	 * </dl>
	 * <p>
	 * Ex: author-score:0.60|author-score:high|intact-miscore:0.36784992
	 */
	public List<ConfidenceScore> getConfidenceScores() {
		return confidenceScores;
	}

	public void setConfidenceScores(List<ConfidenceScore> confidenceScores) {
		this.confidenceScores = confidenceScores;
	}

	/**
	 * Complex expansion: Model used to convert n-ary interactions into binary interactions for
	 * purpose of export in MITAB file. It is recommended to always give this information when n-ary
	 * interactions have been expanded. In case of true binary interactions, this column should be
	 * empty ('-').
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>database</strong>. Should always be <code>psi-mi</code>.</dd>
	 * <dt>value</dt>
	 * <dd><strong>identifier</strong>. Taken from <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI%3A1059&termName=complex%20expansion>PSI-MI
	 * controlled vocabulary</a></dd>
	 * <dt>description</dt>
	 * <dd><strong>name</strong>. Taken from identifier.</dd>
	 * </dl>
	 * <p>
	 * Ex: psi-mi:"MI:1060"(spoke expansion)
	 */
	public List<ComplexExpansion> getComplexExpansion() {
		return complexExpansion;
	}

	public void setComplexExpansion(List<ComplexExpansion> complexExpansion) {
		this.complexExpansion = complexExpansion;
	}

	/**
	 * Parameters of the interaction, for example kinetics.
	 *
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>type</strong>: can be taken from the corresponding <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI%3A0640&termName=parameter%20type>PSI-MI
	 * controlled vocabulary</a>.</dd>
	 * <dt>value</dt>
	 * <dd><strong>value</strong>: syntax format is <em>factor x base ^ exponent ~uncertainty</em>.</dd>
	 * <dt>description</dt>
	 * <dd><strong>text</strong>: free text</dd>
	 * </dl>
	 * <p>
	 * Ex: kd:"4.0x2^5 ~0.3"|kd:2.0
	 */
	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	/**
	 * Creation date: when the curation of the publication started. This field is equivalent to the
	 * release date in the PSI-XML schema.
	 *
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>date</strong>: as yyyy/mm/dd</dd>
	 * </dl>
	 * Ex:2010/10/17
	 *
	 * @return creation date
	 */
	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	/**
	 * when the interaction was updated for the last time. This field does not have any equivalence
	 * in PSI-XML (could be the release date as well) and helps to keep track of changes in curated
	 * data.
	 *
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>date</strong>: as yyyy/mm/dd</dd>
	 * </dl>
	 * Ex:2011/12/13
	 *
	 * @return update date
	 */
	public Date getUpdate() {
		return update;
	}

	public void setUpdate(Date update) {
		this.update = update;
	}

	/**
	 * Checksum for interaction, for instance the RIGID of the interaction.
	 *
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>method name</strong>: name of the method used to create the checksum</dd>
	 * <dt>value</dt>
	 * <dd><strong>checksum</strong></dd>
	 * </dl>
	 * <p>
	 * Ex: rigid:"+++94o2VtVJcuk6jD3H2JZXaVYc"
	 */
	public List<Checksum> getChecksums() {
		return checksums;
	}

	public void setChecksums(List<Checksum> checksums) {
		this.checksums = checksums;
	}

	/**
	 * negative: Boolean value to distinguish positive interactions (false) from negative
	 * interactions (true). A molecular interaction between A and B entities is considered as
	 * negative (true) when an isoform of A interacts/binds to B and not A itself. For that
	 * particular isoform of A that interacts with entity B, in the corresponding binary interaction
	 * row, the negative value should be set to false. By default, if the column is empty ('-'), the
	 * negative value is considered to be false (positive interaction).
	 *
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>negative</strong>: true if negative, false or empty if positive</dd>
	 * </dl>
	 * <p>
	 * Ex: true
	 *
	 * @return true if negative, false or null if positive
	 */
	public Negative getNegative() {
		return negative;
	}

	public void setNegative(Negative negative) {
		this.negative = negative;
	}

	/**
	 * Causal regulatory mechanism: This column describes the regulatory mechanism of indirect
	 * causal interactions, where the entity A is not immediately upstream of the entity B. When the
	 * column {@link Interaction#getTypes()} is equal to the psi-mi term:
	 * psi-mi:"MI:2286"(functional association), then the causal regulatory mechanism must be
	 * absolutely filled. Otherwise it can be empty.
	 *
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>database</strong>. Should always be <code>psi-mi</code></dd>
	 * <dt>value</dt>
	 * <dd><strong>identifier</strong>. taken from <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI%3A2245>
	 * PSI-MI controlled vocabulary</a></dd>
	 * <dt>description</dt>
	 * <dd><strong>regulatory mechanism</strong>: name as in the PSI-MI controlled vocabulary</dd>
	 * </dl>
	 * <p>
	 * Ex: psi-mi:"MI:2247"(transcriptional regulation)
	 */
	public List<CausalRegulatoryMechanism> getCausalRegulatoryMechanism() {
		return causalRegulatoryMechanism;
	}

	public void setCausalRegulatoryMechanism(List<CausalRegulatoryMechanism> causalRegulatoryMechanism) {
		this.causalRegulatoryMechanism = causalRegulatoryMechanism;
	}


	/**
	 * Causal statement: This column describes the effect of modulator entity A on a modulated
	 * entity B.
	 *
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>database</strong>. Should always be <code>psi-mi</code></dd>
	 * <dt>value</dt>
	 * <dd><strong>identifier</strong>. taken from <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI%3A2234>
	 * PSI-MI controlled vocabulary</a></dd>
	 * <dt>description</dt>
	 * <dd><strong>causal statement</strong>: name as in the PSI-MI controlled vocabulary</dd>
	 * </dl>
	 * <p>
	 * Ex: psi-mi:"MI:2240"(down regulates)
	 */
	public List<CausalStatement> getCausalStatement() {
		return causalStatement;
	}

	public void setCausalStatement(List<CausalStatement> causalStatement) {
		this.causalStatement = causalStatement;
	}

	@Override
	public String toString() {
		return "Interaction{" +
				"identifier=" + (identifiers.size() > 0 ? identifiers.get(0).getIdentifier() : "NA") +
				", interactorA=" + interactorA.getPrimaryIdentifier().get(0).getIdentifier() +
//				", interactorB=" + (interactorB == null ? "null" : interactorB.getPrimaryIdentifier().getIdentifier()) +
				'}';
	}
}
