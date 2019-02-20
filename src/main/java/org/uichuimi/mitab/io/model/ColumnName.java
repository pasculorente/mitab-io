package org.uichuimi.mitab.io.model;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Allows named access to interaction fields.
 */
public enum ColumnName {

	// NOTE for developers: although we can use ColumnName.ordinal(), it is strongly discouraged.
	// All descriptions taken from https://psicquic.github.io/MITAB28Format.html.
	/**
	 * Unique identifier for interactor A. Even though identifiers from multiple databases can be specified, it is
	 * recommended to give only one identifier in this column. It is recommended that proteins be identified by stable
	 * identifiers such as their UniProtKB or RefSeq accession number. Small molecules should have Chebi identifiers,
	 * nucleic acids should have embl/ddbj/genbank identifiers and gene should have entrez gene/locuslink, ensembl, or
	 * ensemblGenome identifiers. This column should never be empty except for describing intra-molecular interactions
	 * or auto-catalysis.
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>database</strong>. Taken from <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI:0444&termName=database%20citation>PSI-MI
	 * controlled vocabulary</a></dd>
	 * <dt>value</dt>
	 * <dd><strong>identifier</strong>. unique primary identifier of the molecule in the database</dd>
	 * </dl>
	 * <p>
	 * Ex: uniprotkb:P12346
	 */
	A_ID("ID(s) interactor A", 0),

	/**
	 * Unique identifier for interactor B. Even though identifiers from multiple databases can be specified, it is
	 * recommended to give only one identifier in this column. It is recommended that proteins be identified by stable
	 * identifiers such as their UniProtKB or RefSeq accession number. Small molecules should have Chebi identifiers,
	 * nucleic acids should have embl/ddbj/genbank identifiers and gene should have entrez gene/locuslink, ensembl, or
	 * ensemblGenome identifiers. This column should never be empty except for describing intra-molecular interactions
	 * or auto-catalysis.
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>database</strong>. Taken from <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI:0444&termName=database%20citation>PSI-MI
	 * controlled vocabulary</a></dd>
	 * <dt>value</dt>
	 * <dd><strong>identifier</strong>. unique primary identifier of the molecule in the database</dd>
	 * </dl>
	 * <p>
	 * Ex: uniprotkb:P12346
	 */
	B_ID("ID(s) interactor B", 1),

	/**
	 * Alternative identifier for interactor A. Multiple identifiers. It is recommended to only give database
	 * identifiers in this column. Other cross references for interactor A such as GO xrefs should be moved to the
	 * column {@link ColumnName#A_XREF} and interactor names such as gene names should be moved to the column {@link
	 * ColumnName#A_ALIAS}.
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
	A_ALT_ID("Alt. ID(s) interactor A", 2),

	/**
	 * Alternative identifier for interactor B. Multiple identifiers. It is recommended to only give database
	 * identifiers in this column. Other cross references for interactor B such as GO xrefs should be moved to the
	 * column {@link ColumnName#B_XREF} and interactor names such as gene names should be moved to the column {@link
	 * ColumnName#B_ALIAS}.
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
	B_ALT_ID("Alt. ID(s) interactor B", 3),

	/**
	 * Aliases for A. Multiple names.
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
	A_ALIAS("Alias(es) interactor A", 4),

	/**
	 * Aliases for B. Multiple names.
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
	B_ALIAS("Alias(es) interactor B", 5),

	/**
	 * Interaction detection methods. It is recommended by MIMIx and can be used for scoring interactions so it is
	 * recommended to always give this information. It is also recommended to give one interaction detection method per
	 * MITAB line (for clustering purposes).
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
	DETECTION_METHOD("Interaction detection method(s)", 6),

	/**
	 * First author surname(s) of the publication(s) followed by 'et al.' and the publication year in parenthesis, e.g.
	 * Ciferri et al.(2005).
	 */
	AUTHOR("Publication 1st author(s)", 7),

	/**
	 * Identifier of the publication in which this interaction has been shown. The publication identifier is used for
	 * clustering interactions from different data providers in PSICQUIC and can be used for scoring interactions so it
	 * is recommended to always give this information. It is recommended to give one pubmed id per MITAB line and IMEx
	 * ids can be added.
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
	PUBLICATION("Publication Identifier(s)", 8),

	/**
	 * NCBI Taxonomy identifier for interactor A. It is recommended to have one organism per interactor per MITAB line.
	 * If both scientific name and common name are given, they should be represented with : taxid:id1(common
	 * name1)|taxid:id1(scientific name1). Note: Currently no taxonomy identifiers other than NCBI taxid are
	 * anticipated, apart from the use of -2 to indicate "chemical synthesis" and -3 indicates "unknown". It is
	 * recommended to always give this information for proteins and genes. For small molecules and nucleic acids, this
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
	A_ORGANISM("Taxid interactor A", 9),

	/**
	 * NCBI Taxonomy identifier for interactor B. It is recommended to have one organism per interactor per MITAB line.
	 * If both scientific name and common name are given, they should be represented with : taxid:id1(common
	 * name1)|taxid:id1(scientific name1). Note: Currently no taxonomy identifiers other than NCBI taxid are
	 * anticipated, apart from the use of -2 to indicate "chemical synthesis" and -3 indicates "unknown". It is
	 * recommended to always give this information for proteins and genes. For small molecules and nucleic acids, this
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
	B_ORGANISM("Taxid interactor B", 10),

	/**
	 * Interaction types, taken from the corresponding. Interaction type can be used for scoring interactions so it is
	 * recommended to always give this information. It is also recommended to give one interaction type per MITAB line
	 * (for clustering purposes).
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
	TYPE("Interaction type(s)", 11),

	/**
	 * Source databases and identifiers. As the detection methods are taken from the PSI-MI ontology, the database name
	 * is 'psi-mi'. When the interaction has been imported and reported by different sources, it is recommended to give
	 * the original source plus the source that currently reports the interaction.
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
	DATABASE("Source database(s)", 12),

	/**
	 * Interaction identifier(s) in the corresponding source database. It is recommended to always give a unique
	 * identifier per interaction (binary and n-ary) so in case of complexes which have been expanded, it would be
	 * possible to retrieve and re-build the original complex based on the interaction identifier. IrefIndex IDs go into
	 * the column {@link ColumnName#CHECKSUM}. Other interaction references such as GO references go into the column
	 * {@link ColumnName#XREF}.
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
	ID("Interaction identifier(s)", 13),

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
	CONFIDENCE("Confidence value(s)", 14),

	/**
	 * Complex expansion: Model used to convert n-ary interactions into binary interactions for purpose of export in
	 * MITAB file. It is recommended to always give this information when n-ary interactions have been expanded. In case
	 * of true binary interactions, this column should be empty ('-').
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
	EXPANSION_METHOD("Expansion method(s)", 15),

	/**
	 * Biological role A. Biological roles are recommended by MIMIx so it is recommended to always give this
	 * information. When the participant A does not have any specific biological roles, the term 'unspecified role'
	 * (MI:0499) should be used.
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
	A_BIO_ROLE("Biological role(s) interactor A", 16),

	/**
	 * Biological role B. Biological roles are recommended by MIMIx so it is recommended to always give this
	 * information. When the participant B does not have any specific biological roles, the term 'unspecified role'
	 * (MI:0499) should be used.
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
	B_BIO_ROLE("Biological role(s) interactor B", 17),

	/**
	 * Experimental role A. Experimental roles are recommended by MIMIx so it is recommended to always give this
	 * information. When the participant A does not have any specific experimental roles, the term 'unspecified role'
	 * (MI:0499) should be used.
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
	A_EXP_ROLE("Experimental role(s) interactor A", 18),

	/**
	 * Experimental role B. Experimental roles are recommended by MIMIx so it is recommended to always give this
	 * information. When the participant B does not have any specific experimental roles, the term 'unspecified role'
	 * (MI:0499) should be used.
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
	B_EXP_ROLE("Experimental role(s) interactor B", 19),

	/**
	 * Interactor type A. It is recommended to always give this information as it can be useful for recognizing
	 * protein-protein, small molecule-proteins, nucleic acids-proteins and gene-proteins interactions.
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
	A_TYPE("Type(s) interactor A", 20),

	/**
	 * Interactor type B. It is recommended to always give this information as it can be useful for recognizing
	 * protein-protein, small molecule-proteins, nucleic acids-proteins and gene-proteins interactions.
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
	B_TYPE("Type(s) interactor B", 21),

	/**
	 * Xref for interactor A. For example the gene ontology cross references associated. This column aims at adding more
	 * information to describe the interactor A but cannot be used to identify the interactor A. If some sequence
	 * database accessions are ambiguous (Ex : uniprot secondary accessions that are shared between different uniprot
	 * entries and so cannot be used as identifiers of interactor A), it is possible to report them in this column.
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
	A_XREF("Xref(s) interactor A", 22),

	/**
	 * Xref for interactor B. For example the gene ontology cross references associated. This column aims at adding more
	 * information to describe the interactor A but cannot be used to identify the interactor B. If some sequence
	 * database accessions are ambiguous (Ex : uniprot secondary accessions that are shared between different uniprot
	 * entries and so cannot be used as identifiers of interactor B), it is possible to report them in this column.
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
	B_XREF("Xref(s) interactor B", 23),

	/**
	 * Xref for the interaction. For example the gene ontology cross references associated (components, etc.) or OMIM
	 * cross references. Note, that this field is not meant to store interaction accessions or interaction checksums.
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
	XREF("Interaction Xref(s)", 24),

	/**
	 * Annotations for interactor A.
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
	A_ANNOTATION("Annotation(s) interactor A", 25),

	/**
	 * Annotations for interactor B.
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
	B_ANNOTATION("Annotation(s) interactor B", 26),

	/**
	 * Annotations for the interaction. This column would also be used for tagging interactions and in such a case,
	 * topics for tags are also defined in the <a href=http://www.ebi.ac.uk/ontology-lookup/browse.do?ontName=MI&termId=MI%3A1045&termName=curation%20content>PSI-MI
	 * controlled vocabulary</a> except for the complex expansion tags that have their own column ({@link
	 * ColumnName#EXPANSION_METHOD}).
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
	ANNOTATION("Interaction annotation(s)", 27),

	/**
	 * NCBI Taxonomy identifier for the host organism. Cells and tissues cannot be described in this column. If both
	 * scientific name and common name are given, they should be represented with : taxid:id1(common
	 * name1)|taxid:id1(scientific name1). Note: Currently no taxonomy identifiers other than NCBI taxid are
	 * anticipated, apart from the use of -1 to indicate "in vitro", -2 to indicate "chemical synthesis", -3 indicates
	 * "unknown", -4 indicates "in vivo" and -5 indicates "in silico".
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
	ORGANISM("Host organism(s)", 28),

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
	PARAMETERS("Interaction parameter(s)", 29),

	/**
	 * Creation date: when the curation of the publication started. This field is equivalent to the release date in the
	 * PSI-XML schema.
	 *
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>date</strong>: as yyyy/mm/dd</dd>
	 * </dl>
	 * Ex:2010/10/17
	 */
	CREATION("Creation date", 30),

	/**
	 * when the interaction was updated for the last time. This field does not have any equivalence in PSI-XML (could be
	 * the release date as well) and helps to keep track of changes in curated data.
	 *
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>date</strong>: as yyyy/mm/dd</dd>
	 * </dl>
	 * Ex:2011/12/13
	 */
	UPDATE("Update date", 31),

	/**
	 * Checksum for interactor A, for instance the ROGID of the interactor which takes into consideration both the
	 * sequence and the organism of the interactor. It is recommended to give the ROGID and CROGID for proteins and the
	 * standard Inchi key for small molecules.
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
	A_CHECKSUM("Checksum(s) interactor A", 32),

	/**
	 * Checksum for interactor B, for instance the ROGID of the interactor which takes into consideration both the
	 * sequence and the organism of the interactor. It is recommended to give the ROGID and CROGID for proteins and the
	 * standard Inchi key for small molecules.
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
	B_CHECKSUM("Checksum(s) interactor B", 33),

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
	CHECKSUM("Interaction Checksum(s)", 34),

	/**
	 * negative: Boolean value to distinguish positive interactions (false) from negative interactions (true). A
	 * molecular interaction between A and B entities is considered as negative (true) when an isoform of A
	 * interacts/binds to B and not A itself. For that particular isoform of A that interacts with entity B, in the
	 * corresponding binary interaction row, the negative value should be set to false. By default, if the column is
	 * empty ('-'), the negative value is considered to be false (positive interaction).
	 *
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>negative</strong>: true if negative, false or empty if positive</dd>
	 * </dl>
	 * <p>
	 * Ex: true
	 */
	NEGATIVE("Negative", 35),

	/**
	 * Feature(s) for interactor A: describe features for participant A such as binding sites, PTMs, tags, etc.
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
	A_FEATURES("Feature(s) interactor A", 36),

	/**
	 * Feature(s) for interactor B: describe features for participant A such as binding sites, PTMs, tags, etc.
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
	B_FEATURES("Feature(s) interactor B", 37),

	/**
	 * Stoichiometry for interactor A: A numerical value describing the count of instance of the molecule participating
	 * in the interaction. If no stoichiometry is available for interactor A, the column should be empty ('-'),
	 * otherwise a positive Integer value should be given. Several specific cases should be taken into consideration: in
	 * case of auto-catalysis, only one interactor is given and the stoichiometry should be 1. In case of homodimers,
	 * homotrimers, etc., the stoichiometry of one interactor should be 0 and the stoichiometry of the other should be a
	 * valid positive Integer. In case of homo-oligomer, the stoichiometry of both interactors should be 0. Example: for
	 * self interactors e.g. a kinase occluding its kinase domain by an internal phospho-tyrosine/SH2 domain
	 * interaction, only Interactor A column will show the molecule accession number with the stoichiometry 1. The
	 * columns for Interactor B will be empty.
	 *
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>stoichiometry</strong>: as described before.</dd>
	 * </dl>
	 * <p>
	 * Ex: 4
	 */
	A_STOICHIOMETRY("Stoichiometry(s) interactor A", 38),

	/**
	 * Stoichiometry for interactor B: A numerical value describing the count of instance of the molecule participating
	 * in the interaction. If no stoichiometry is available for interactor B, the column should be empty ('-'),
	 * otherwise a positive Integer value should be given. Several specific cases should be taken into consideration: in
	 * case of auto-catalysis, only one interactor is given and the stoichiometry should be 1. In case of homodimers,
	 * homotrimers, etc., the stoichiometry of one interactor should be 0 and the stoichiometry of the other should be a
	 * valid positive Integer. In case of homo-oligomer, the stoichiometry of both interactors should be 0. Example: for
	 * self interactors e.g. a kinase occluding its kinase domain by an internal phospho-tyrosine/SH2 domain
	 * interaction, only Interactor A column will show the molecule accession number with the stoichiometry 1. The
	 * columns for Interactor B will be empty.
	 *
	 * <dl>
	 * <dt>xref</dt>
	 * <dd><strong>stoichiometry</strong>: as described before.</dd>
	 * </dl>
	 * <p>
	 * Ex: 4
	 */
	B_STOICHIOMETRY("Stoichiometry(s) interactor B", 39),

	/**
	 * Participant identification method for interactor A. Participant detection method is recommended by MIMIx so it is
	 * recommended to always give this information.
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
	A_IDENTIFICATION_METHOD("Identification method participant A", 40),

	/**
	 * Participant identification method for interactor B. Participant detection method is recommended by MIMIx so it is
	 * recommended to always give this information.
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
	B_IDENTIFICATION_METHOD("Identification method participant B", 41),

	/**
	 * Biological effect of interactor A: The GO term associated with the molecular function of interactor A that is
	 * responsible for its regulatory activity. Also, it is recommended that at most one GO xref is given as a
	 * biological effect.
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
	A_BIOLOGICAL_EFFECT("Biological effect of interactor A", 42),

	/**
	 * Biological effect of interactor B: The GO term associated with the molecular function of interactor A that is
	 * responsible for its regulatory activity. Also, it is recommended that at most one GO xref is given as a
	 * biological effect.
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
	B_BIOLOGICAL_EFFECT("Biological effect of interactor B", 43),

	/**
	 * Causal regulatory mechanism: This column describes the regulatory mechanism of indirect causal interactions,
	 * where the entity A is not immediately upstream of the entity B. When the column {@link ColumnName#TYPE} is equal
	 * to the psi-mi term: psi-mi:"MI:2286"(functional association), then the causal regulatory mechanism must be
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
	CASUAL_REGULATORY_MECHANISM("Causal regulatory mechanism", 44),

	/**
	 * Causal statement: This column describes the effect of modulator entity A on a modulated entity B.
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
	CAUSAL_STATEMENT("Causal statement", 45);

	private final String name;
	private final Function<Interaction, List<Field>> getter;
	private final BiConsumer<Interaction, List<Field>> setter;

	/**
	 * Shorthand for creating a column with getter and setter based only on index.
	 */
	ColumnName(String name, int index) {
		this(name, interaction -> interaction.get(index), ((interaction, fields) -> interaction.set(index, fields)));
	}

	/**
	 * Genral ColumnName constructor. By using this constructor you will be able to retrieve fields not only based on
	 * its index, but you can merge, filter or create fields. Imagination is the limit.
	 */
	ColumnName(String name, Function<Interaction, List<Field>> getter, BiConsumer<Interaction, List<Field>> setter) {
		this.name = name;
		this.getter = getter;
		this.setter = setter;
	}

	public String getName() {
		return name;
	}

	public List<Field> get(Interaction interaction) {
		return getter.apply(interaction);
	}

	public void set(Interaction interaction, List<Field> fields) {
		setter.accept(interaction, fields);
	}

}
