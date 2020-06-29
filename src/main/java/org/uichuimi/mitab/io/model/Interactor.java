package org.uichuimi.mitab.io.model;

import java.util.Collections;
import java.util.List;

public class Interactor {
	
	@PsiColumn({"identifier"})
	private List<Identifier> primaryIdentifier = Collections.emptyList();
	@PsiColumn({"alternatives", "alternative"})
	private List<Identifier> alternativeIdentifiers = Collections.emptyList();
	@PsiColumn({"alias", "aliases"})
	private List<Alias> aliases = Collections.emptyList();
	@PsiColumn({"organism", "organisms"})
	private List<Organism> organisms = Collections.emptyList();
	@PsiColumn({"biologicalRole", "biorole"})
	private List<BiologicalRole> biologicalRoles = Collections.emptyList();
	@PsiColumn({"experimentalRole", "exprole"})
	private List<ExperimentalRole> experimentalRoles = Collections.emptyList();
	@PsiColumn({"type", "types"})
	private List<Type> types = Collections.emptyList();
	@PsiColumn({"xref", "crossReference", "crossReferences"})
	private List<CrossReference> crossReferences = Collections.emptyList();
	@PsiColumn({"annotation", "annotations"})
	private List<Annotation> annotations = Collections.emptyList();
	@PsiColumn({"checksum"})
	private List<Checksum> checksums = Collections.emptyList();
	@PsiColumn({"feature", "features"})
	private List<Feature> features = Collections.emptyList();
	@PsiColumn({"stoichiometry"})
	private List<Stoichiometry> stoichiometries = Collections.emptyList();
	@PsiColumn({"identificationMethod", "method"})
	private List<IdentificationMethod> identificationMethods = Collections.emptyList();
	@PsiColumn({"biologicalEffect", "effect"})
	private List<BiologicalEffect> biologicalEffects = Collections.emptyList();

	/**
	 * Unique identifier for interactor. Even though identifiers from multiple databases can be
	 * specified, it is recommended to give only one identifier in this column. It is recommended
	 * that proteins be identified by stable identifiers such as their UniProtKB or RefSeq accession
	 * number. Small molecules should have Chebi identifiers, nucleic acids should have
	 * embl/ddbj/genbank identifiers and gene should have entrez gene/locuslink, ensembl, or
	 * ensemblGenome identifiers. This column should never be empty except for describing
	 * intra-molecular interactions or auto-catalysis.
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>database</strong>. Taken from <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI:0444&termName=database%20citation>PSI-MI
	 * controlled vocabulary</a></dd>
	 * <dt>value</dt>
	 * <dd><strong>identifier</strong>. unique primary identifier of the molecule in the database</dd>
	 * </dl>
	 * <p>
	 * Ex: uniprotkb:P12346
	 * @return
	 */
	public List<Identifier> getPrimaryIdentifier() {
		return primaryIdentifier;
	}

	public void setPrimaryIdentifier(List<Identifier> primaryIdentifier) {
		this.primaryIdentifier = primaryIdentifier;
	}

	/**
	 * Alternative identifier for interactor. Multiple identifiers. It is recommended to only give
	 * database identifiers in this column. Other cross references for interactor A such as GO xrefs
	 * should be moved to the column {@link Interactor#getCrossReferences()} and interactor names
	 * such as gene names should be moved to the column {@link Interactor#getAliases()}.
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>database</strong>. Taken from <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI:0444&termName=database%20citation>PSI-MI
	 * controlled vocabulary</a></dd>
	 * <dt>value</dt>
	 * <dd><strong>ac</strong>. Primary identifier of the molecule in the database</dd>
	 * </dl>
	 * <p>
	 * Ex: refseq:NP_001013128|ensembl:ENSRNOP00000012946
	 */
	public List<Identifier> getAlternativeIdentifiers() {
		return alternativeIdentifiers;
	}

	public void setAlternativeIdentifiers(List<Identifier> alternativeIdentifiers) {
		this.alternativeIdentifiers = alternativeIdentifiers;
	}

	/**
	 * Interactor aliases. Multiple names.
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>database</strong>. Taken from <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI:0444&termName=database%20citation>PSI-MI
	 * controlled vocabulary</a>. Use <code>"unknown"</code> if absent.</dd>
	 * <dt>value</dt>
	 * <dd><strong>name</strong>. alias name</dd>
	 * <dt>description</dt>
	 * <dd><strong>type</strong>. As defined in the <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI%3A0300&termName=alias%20type>PSI-MI
	 * controlled vocabulary</a>. <code>'display_short'</code> and <code>'display_long'</code> are used to describe what
	 * name can be used for network display.</dd>
	 * </dl>
	 * <p>
	 * Ex: uniprotkb:Tf(gene name)|uniprotkb:Serotransferrin(recommended name)|uniprotkb:Tf(display_short)
	 */
	public List<Alias> getAliases() {
		return aliases;
	}

	public void setAliases(List<Alias> aliases) {
		this.aliases = aliases;
	}

	/**
	 * NCBI Taxonomy identifier for interactor A. It is recommended to have one organism per
	 * interactor per MITAB line. If both scientific name and common name are given, they should be
	 * represented with : taxid:id1(common name1)|taxid:id1(scientific name1). Note: Currently no
	 * taxonomy identifiers other than NCBI taxid are anticipated, apart from the use of -2 to
	 * indicate "chemical synthesis" and -3 indicates "unknown". It is recommended to always give
	 * this information for proteins and genes. For small molecules and nucleic acids, this
	 * information should be given when available but can be left empty ('-') if not relevant.
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
	public List<Organism> getOrganisms() {
		return organisms;
	}

	public void setOrganisms(List<Organism> organisms) {
		this.organisms = organisms;
	}

	/**
	 * Biological role. Biological roles are recommended by MIMIx so it is recommended to always
	 * give this information. When the participant A does not have any specific biological roles,
	 * the term 'unspecified role' (MI:0499) should be used.
	 *
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>database</strong>. Should always be <code>psi-mi</code>.</dd>
	 * <dt>value</dt>
	 * <dd><strong>identifier</strong>. Taken from <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI%3A0500&termName=biological%20role>PSI-MI
	 * controlled vocabulary</a></dd>
	 * <dt>description</dt>
	 * <dd><strong>name</strong>. Taken from identifier</dd>
	 * </dl>
	 * <p>
	 * Ex: psi-mi:"MI:0684"(ancillary)
	 */
	public List<BiologicalRole> getBiologicalRoles() {
		return biologicalRoles;
	}

	public void setBiologicalRoles(List<BiologicalRole> biologicalRoles) {
		this.biologicalRoles = biologicalRoles;
	}

	/**
	 * Experimental role. Experimental roles are recommended by MIMIx so it is recommended to always
	 * give this information. When the participant A does not have any specific experimental roles,
	 * the term 'unspecified role' (MI:0499) should be used.
	 *
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>database</strong>. Should always be <code>psi-mi</code>.</dd>
	 * <dt>value</dt>
	 * <dd><strong>identifier</strong>. Taken from <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI%3A0495&termName=experimental%20role>PSI-MI
	 * controlled vocabulary</a></dd>
	 * <dt>description</dt>
	 * <dd><strong>name</strong>. Taken from identifier</dd>
	 * </dl>
	 * <p>
	 * Ex: psi-mi:"MI:0496"(bait)
	 */
	public List<ExperimentalRole> getExperimentalRoles() {
		return experimentalRoles;
	}

	public void setExperimentalRoles(List<ExperimentalRole> experimentalRoles) {
		this.experimentalRoles = experimentalRoles;
	}

	/**
	 * Interactor type. It is recommended to always give this information as it can be useful for
	 * recognizing protein-protein, small molecule-proteins, nucleic acids-proteins and
	 * gene-proteins interactions.
	 *
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>database</strong>. Should always be <code>psi-mi</code>.</dd>
	 * <dt>value</dt>
	 * <dd><strong>identifier</strong>. Taken from <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI%3A0313&termName=interactor%20typehttp://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI%3A0313&termName=interactor%20type>PSI-MI
	 * controlled vocabulary</a></dd>
	 * <dt>description</dt>
	 * <dd><strong>name</strong>. Taken from identifier</dd>
	 * </dl>
	 * <p>
	 * Ex: psi-mi:"MI:0326"(protein)
	 */
	public List<Type> getTypes() {
		return types;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}

	/**
	 * Xref for interactor. For example the gene ontology cross references associated. This column
	 * aims at adding more information to describe the interactor A but cannot be used to identify
	 * the interactor A. If some sequence database accessions are ambiguous (Ex : uniprot secondary
	 * accessions that are shared between different uniprot entries and so cannot be used as
	 * identifiers of interactor A), it is possible to report them in this column.
	 *
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>database</strong>. Taken from <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI:0444&termName=database%20citation>PSI-MI
	 * controlled vocabulary</a>.</dd>
	 * <dt>value</dt>
	 * <dd><strong>ac</strong>. Primary accession of the molecule in the database</dd>
	 * <dt>description</dt>
	 * <dd><strong>text</strong>. The text can be used to describe the qualifier type of the cross reference (see the
	 * corresponding <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI%3A0353&termName=cross-reference%20type>PSI-MI
	 * controlled vocabulary</a>) or could be used to give the name of the GO term in case of cross references to
	 * ontology databases</dd>
	 * </dl>
	 * <p>
	 * Ex: go:"GO:0003824"(catalytic activity)
	 */
	public List<CrossReference> getCrossReferences() {
		return crossReferences;
	}

	public void setCrossReferences(List<CrossReference> identifiers) {
		this.crossReferences = identifiers;
	}

	/**
	 * Annotations for interactor.
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>topic</strong>. Name of the topic as defined in the <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI%3A0590&termName=attribute%20name>
	 * PSI-MI controlled vocabulary</a>.</dd>
	 * <dt>value</dt>
	 * <dd><strong>text</strong>: free text associated with the topic (linebreak and other MITAB reserved characters
	 * should be properly escaped, replaced and/or removed). The text is optional and only a topic could be given, e.g.
	 * anti-bacterial.</dd>
	 * </dl>
	 * <p>
	 * Ex: comment:"sequence not available in uniprotKb".
	 */
	public List<Annotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}

	/**
	 * Checksum for interactor A, for instance the ROGID of the interactor which takes into
	 * consideration both the sequence and the organism of the interactor. It is recommended to give
	 * the ROGID and CROGID for proteins and the standard Inchi key for small molecules.
	 *
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>method name</strong>: name of the method used to create the checksum</dd>
	 * <dt>value</dt>
	 * <dd><strong>checksum</strong></dd>
	 * </dl>
	 * <p>
	 * Ex: rogid:UcdngwpTSS6hG/pvQGgpp40u67I9606|crogid:UcdngwpTSS6hG/pvQGgpp40u67I9606
	 */
	public List<Checksum> getChecksums() {
		return checksums;
	}

	public void setChecksums(List<Checksum> checksums) {
		this.checksums = checksums;
	}

	/**
	 * Feature(s) for interactor: describe features for participant A such as binding sites, PTMs,
	 * tags, etc.
	 *
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>feature type</strong>: taken from the <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI%3A0116&termName=feature%20type>
	 * PSI-MI controlled vocabulary</a>. For the PTMs, the MI ontology terms are obsolete and the <a
	 * href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MOD>PSI-MOD ontology</a> should be used
	 * instead.</dd>
	 * <dt>value</dt>
	 * <dd><strong>range</strong>: the use of the following characters is allowed to describe a range position : ‘?’
	 * (undetermined position), ‘n’ (n terminal range), ‘c’ (c-terminal range), ‘>x’ (greater than x), ‘<’ (less than
	 * x), ‘x1..x1’ (fuzzy range position Ex : 5..5-9..10). The character '-' is used to separate start position(s) from
	 * end position(s). Multiple ranges per feature separated by ','. It is not possible to represent linked
	 * features/ranges.</dd>
	 * <dt>description</dt>
	 * <dd><strong>text</strong>: can be used for feature type names, feature names, interpro cross references,
	 * etc.</dd>
	 * </dl>
	 * <p>
	 * Ex: gst tag:n-n(n-terminal region)|sufficient to bind:23-45. or binding site:23..24-46,33-33
	 * <p>
	 * Ex: sufficient to bind:27-195,201-133 (IPR000785).
	 */
	public List<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

	/**
	 * Stoichiometry for interactor: A numerical value describing the count of instance of the
	 * molecule participating in the interaction. If no stoichiometry is available for interactor A,
	 * the column should be empty ('-'), otherwise a positive Integer value should be given. Several
	 * specific cases should be taken into consideration: in case of auto-catalysis, only one
	 * interactor is given and the stoichiometry should be 1. In case of homodimers, homotrimers,
	 * etc., the stoichiometry of one interactor should be 0 and the stoichiometry of the other
	 * should be a valid positive Integer. In case of homo-oligomer, the stoichiometry of both
	 * interactors should be 0. Example: for self interactors e.g. a kinase occluding its kinase
	 * domain by an internal phospho-tyrosine/SH2 domain interaction, only Interactor A column will
	 * show the molecule accession number with the stoichiometry 1. The columns for Interactor B
	 * will be empty.
	 *
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>stoichiometry</strong>: as described before.</dd>
	 * </dl>
	 * <p>
	 * Ex: 4
	 */
	public List<Stoichiometry> getStoichiometries() {
		return stoichiometries;
	}

	public void setStoichiometries(List<Stoichiometry> stoichiometries) {
		this.stoichiometries = stoichiometries;
	}

	/**
	 * Participant identification method for interactor. Participant detection method is recommended
	 * by MIMIx so it is recommended to always give this information.
	 *
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>database</strong>. Should always be <code>psi-mi</code></dd>
	 * <dt>value</dt>
	 * <dd><strong>identifier</strong>. taken from <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI%3A0002&termName=participant%20identification%20method>
	 * PSI-MI controlled vocabulary</a></dd>
	 * <dt>description</dt>
	 * <dd><strong>name</strong>: name as in the PSI-MI controlled vocabulary</dd>
	 * </dl>
	 * <p>
	 * Ex: psi-mi:"MI:0102"(sequence tag identification)
	 */
	public List<IdentificationMethod> getIdentificationMethods() {
		return identificationMethods;
	}

	public void setIdentificationMethods(List<IdentificationMethod> identificationMethods) {
		this.identificationMethods = identificationMethods;
	}

	/**
	 * Biological effect of interactor: The GO term associated with the molecular function of
	 * interactor A that is responsible for its regulatory activity. Also, it is recommended that at
	 * most one GO xref is given as a biological effect.
	 *
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>database</strong>. Should always be <code>go</code></dd>
	 * <dt>value</dt>
	 * <dd><strong>identifier</strong></dd>
	 * <dt>description</dt>
	 * <dd><strong>name</strong></dd>
	 * </dl>
	 * <p>
	 * Ex: go:"GO:0016301"(kinase activity)
	 */
	public List<BiologicalEffect> getBiologicalEffects() {
		return biologicalEffects;
	}

	public void setBiologicalEffects(List<BiologicalEffect> biologicalEffects) {
		this.biologicalEffects = biologicalEffects;
	}
}
