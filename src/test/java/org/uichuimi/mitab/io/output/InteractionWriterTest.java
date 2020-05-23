package org.uichuimi.mitab.io.output;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.uichuimi.mitab.io.input.InteractionReader;
import org.uichuimi.mitab.io.model.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class InteractionWriterTest {


	public static final String INPUT_NAME = "/input/test.mitab27";

	@Test
	public void readAndWrite() {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try (outputStream) {
			final List<Interaction> interactions = new InteractionReader(getClass().getResourceAsStream(INPUT_NAME)).readAll();
			final InteractionWriter writer = new InteractionWriter(outputStream, PsiMitabVersion.TAB_27);
			interactions.forEach(writer::write);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		try {
			final String expected = IOUtils.toString(getClass().getResourceAsStream(INPUT_NAME), Charset.defaultCharset());
			final String actual = outputStream.toString();
			Assert.assertEquals(expected, actual);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void createAndWrite() throws IOException {
		final Interactor a = new Interactor();
		final Interactor b = new Interactor();
		final Interaction interaction = new Interaction(a, b);
		a.setPrimaryIdentifier(new Identifier("uniprotkb", "P49418"));
		b.setPrimaryIdentifier(new Identifier("uniprotkb", "O43426"));
		a.setAlternativeIdentifiers(List.of(new Identifier("intact", "EBI-7121510"), new Identifier("intact", "MINT-109264")));
		b.setAlternativeIdentifiers(List.of(new Identifier("intact", "EBI-2821539")));
		a.setAliases(List.of(new Alias("psi-mi", "amph_human", "display_long"), new Alias("uniprotkb", "AMPH", "gene name")));
		b.setAliases(List.of(new Alias("psi-mi", "SYNJ1", "display_short"), new Alias("uniprotkb", "KIAA0910", "gene name synonym")));
		interaction.setDetectionMethods(List.of(new DetectionMethod("psi-mi", "MI:0084", "phage display")));
		interaction.setAuthors(List.of(new Author("Cestra et al. (1999)")));
		interaction.setPublications(List.of(new Publication("pubmed", "10542231"), new Publication("mint", "MINT-5211933")));
		a.setOrganisms(List.of(new Organism("taxid", "9606", "human"), new Organism("taxid", "9606", "Homo sapiens")));
		b.setOrganisms(List.of(new Organism("taxid", "9606", "human"), new Organism("taxid", "9606", "Homo sapiens")));
		interaction.setTypes(List.of(new Type("psi-mi", "MI:0407", "direct interaction")));
		interaction.setDatabases(List.of(new Database("psi-mi", "MI:0471", "MINT")));
		interaction.setIdentifiers(List.of(new Identifier("intact", "EBI-7121552")));
		interaction.setConfidenceScores(List.of(new ConfidenceScore("intact-miscore", "0.56")));
		a.setBiologicalRoles(List.of(new BiologicalRole("psi-mi", "MI:0499", "unspecified role")));
		b.setBiologicalRoles(List.of(new BiologicalRole("psi-mi", "MI:0499", "unspecified role")));
		a.setExperimentalRoles(List.of(new ExperimentalRole("psi-mi", "MI:0498", "prey")));
		a.setExperimentalRoles(List.of(new ExperimentalRole("psi-mi", "MI:0496", "bait")));
		a.setTypes(List.of(new Type("psi-mi", "MI:0326", "protein")));
		b.setTypes(List.of(new Type("psi-mi", "MI:0326", "protein")));
		interaction.setCreation(new Date("2002/05/24"));
		interaction.setUpdate(new Date("2002/05/24"));
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try (InteractionWriter writer = new InteractionWriter(outputStream)) {
			writer.write(interaction);
		}
		final String expected = IOUtils.toString(getClass().getResourceAsStream("/output/interaction_writer.tab27"), Charset.defaultCharset());
		Assert.assertEquals(expected, outputStream.toString());
		// reactome:R-HSA-8856828|ensembl:ENST00000325590|ensembl:ENST00000356264|ensembl:ENSG00000078053|ensembl:ENSP00000317441|ensembl:ENSP00000348602|mint:P49418|go:"GO:0030672"(synaptic vesicle membrane)|rcsb pdb:5M61|rcsb pdb:5M5S|dip:DIP-40729N|go:"GO:0005543"(phospholipid binding)|go:"GO:0031256"(leading edge membrane)|go:"GO:0005886"(plasma membrane)|go:"GO:0048488"(synaptic vesicle endocytosis)|interpro:IPR035470|interpro:IPR036028|go:"GO:0005829"(cytosol)|go:"GO:0061024"(membrane organization)|go:"GO:0015629"(actin cytoskeleton)|refseq:NP_001626.1|interpro:IPR003005(Amphiphysin)|rcsb pdb:4ATM|go:"GO:0007268"(chemical synaptic transmission)|interpro:IPR003017(Amphiphysin, isoform 1)|refseq:NP_647477.1|rcsb pdb:3SOG|rcsb pdb:1UTC|interpro:IPR001452(Src homology-3)|rcsb pdb:1KY7|interpro:IPR004148(BAR)|go:"GO:0008021"(synaptic vesicle)|go:"GO:0006897"(endocytosis)|go:"GO:0030054"(cell junction)|interpro:IPR027267|mint:MINT-376294(identity)	go:"GO:0007420"(brain development)|go:"GO:0017124"(SH3 domain binding)|go:"GO:0004438"(phosphatidylinositol-3-phosphatase activity)|go:"GO:0012506"(vesicle membrane)|go:"GO:0030132"(clathrin coat of coated pit)|go:"GO:0043195"(terminal bouton)|go:"GO:0043812"(phosphatidylinositol-4-phosphate phosphatase activity)|go:"GO:0097060"(synaptic membrane)|reactome:R-HSA-1660499|reactome:R-HSA-1855183|reactome:R-HSA-1855204|ensembl:ENSP00000349903|go:"GO:0061024"(membrane organization)|go:"GO:0006836"(neurotransmitter transport)|go:"GO:0016082"(synaptic vesicle priming)|go:"GO:0016191"(synaptic vesicle uncoating)|go:"GO:0030117"(membrane coat)|go:"GO:0034595"(phosphatidylinositol phosphate 5-phosphatase activity)|go:"GO:0046488"(phosphatidylinositol metabolic process)|go:"GO:0046855"(inositol phosphate dephosphorylation)|go:"GO:0048489"(synaptic vesicle transport)|reactome:R-HSA-8856828|refseq:XP_016883989.1|interpro:IPR012677(Nucleotide-binding, alpha-beta plait)|mint:O43426|interpro:IPR034971|interpro:IPR034972|ensembl:ENST00000357345|go:"GO:0004439"(phosphatidylinositol-4,5-bisphosphate 5-phosphatase activity)|go:"GO:0003723"(RNA binding)|refseq:NP_001153774.1|refseq:NP_001153778.1|refseq:NP_003886.3|refseq:NP_982271.2|ensembl:ENSG00000159082|interpro:IPR015047(Region of unknown function DUF1866)|interpro:IPR005135(Endonuclease/exonuclease/phosphatase)|interpro:IPR000300(Inositol polyphosphate related phosphatase)|interpro:IPR000504(RNA recognition motif, RNP-1)|interpro:IPR002013(Synaptojanin, N-terminal)|rcsb pdb:1W80|rcsb pdb:2DNR|rcsb pdb:2VJ0|go:"GO:0048471"(perinuclear region of cytoplasm)|go:"GO:0005623"(cell)|interpro:IPR035979|interpro:IPR036691|go:"GO:0007612"(learning)|go:"GO:1904980"(positive regulation of endosome organization)|go:"GO:0048488"(synaptic vesicle endocytosis)|go:"GO:0005829"(cytosol)|go:"GO:0006661"(phosphatidylinositol biosynthetic process)|go:"GO:0043647"(inositol phosphate metabolic process)|go:"GO:0046856"(phosphatidylinositol dephosphorylation)|go:"GO:0098793"(presynapse)|mint:MINT-376287(identity)	-	function:May participate in mechanisms of regulated exocytosis in synapses and certain endocrine cell types. May control the properties of the membrane associated cytoskeleton|comment:mint|comment:homomint|function:Antibodies against AMPH are detected in patients with stiff-man syndrome, a rare disease of the central nervous system characterized by progressive rigidity of the body musculature with superimposed painful spasms|comment:"Stoichiometry: 1.0"	comment:"Stoichiometry: 1.0"	comment:homomint|comment:domino|comment:mint	taxid:-1(in vitro)|taxid:-1(In vitro)	-	2001/01/10	2014/10/16	rogid:vrgVrVoYr45cUe4X6L/zBAE1RtU9606	rogid:RA73eMbCn6F7MD0ItxF/V7QbjqM9606	intact-crc:F4234557A3B54840|rigid:n+UcEH4PPLkFnIyvBiXrLefK/xU	false	binding-associated region:626-695(MINT-376295)	binding-associated region:1063-1070(MINT-376288)	-	-	psi-mi:"MI:0078"(nucleotide sequence identification)	psi-mi:"MI:0078"(nucleotide sequence identification)
	}
}
