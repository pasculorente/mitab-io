# MITAB I/O

Small library to work with, i.e. _parse_, PSI-MI TAB files (https://psicquic.github.io/),
such us intact.txt.gz, or BIOGRID-ALL-3.5.185.mitab.zip).


> This is NOT the official PSI-MI TAB library, neither pretends to replace it. To get 
> the official Java client, try on these links:
>
> - https://mvnrepository.com/artifact/psidev.psi.mi/psimitab
> - http://www.psidev.info/groups/molecular-interactions.

> PSI-MI stands for Proteomics Standards Initiative - Molecular Interaction. 

## PSI-MI TAB
Every row contains Tab Separated Values, where each column contains a list of _fields_, which are separated
by vertical bars |, and contain 1 to 3 values:
```
FIELD=XREF[:VALUE[(DESCRIPTION)]][|FIELD]
```

```
xref:value(description)|xref:value(description)
or
xref:value|xref:value
or
xref|xref
```

Example:
```
#ID(s) interactor A	ID(s) interactor B	Alt. ID(s) interactor A	Alt. ID(s) interactor B	Alias(es) interactor A	Alias(es) interactor B	Interaction detection method(s)	Publication 1st author(s)	Publication Identifier(s)	Taxid interactor A	Taxid interactor B	Interaction type(s)	Source database(s)	Interaction identifier(s)	Confidence value(s)	Expansion method(s)	Biological role(s) interactor A	Biological role(s) interactor B	Experimental role(s) interactor A	Experimental role(s) interactor B	Type(s) interactor A	Type(s) interactor B	Xref(s) interactor A	Xref(s) interactor B	Interaction Xref(s)	Annotation(s) interactor A	Annotation(s) interactor B	Interaction annotation(s)	Host organism(s)	Interaction parameter(s)	Creation date	Update date	Checksum(s) interactor A	Checksum(s) interactor B	Interaction Checksum(s)	Negative	Feature(s) interactor A	Feature(s) interactor B	Stoichiometry(s) interactor A	Stoichiometry(s) interactor B	Identification method participant A	Identification method participant B
uniprotkb:P49418	uniprotkb:O43426	intact:EBI-7121510|uniprotkb:Q75MK5|uniprotkb:Q75MM3|uniprotkb:A4D1X9|intact:MINT-109264|uniprotkb:O43538|uniprotkb:A4D1X8|uniprotkb:Q75MJ8|uniprotkb:Q8N4G0	intact:EBI-2821539|uniprotkb:O43425|uniprotkb:O94984|uniprotkb:Q4KMR1	psi-mi:amph_human(display_long)|uniprotkb:AMPH(gene name)|psi-mi:AMPH(display_short)|uniprotkb:AMPH1(gene name synonym)	psi-mi:synj1_human(display_long)|uniprotkb:SYNJ1(gene name)|psi-mi:SYNJ1(display_short)|uniprotkb:KIAA0910(gene name synonym)|uniprotkb:Synaptic inositol 1,4,5-trisphosphate 5-phosphatase 1(gene name synonym)	psi-mi:"MI:0084"(phage display)	Cestra et al. (1999)	pubmed:10542231|mint:MINT-5211933	taxid:9606(human)|taxid:9606(Homo sapiens)	taxid:9606(human)|taxid:9606(Homo sapiens)	psi-mi:"MI:0407"(direct interaction)	psi-mi:"MI:0471"(MINT)	intact:EBI-7121552|mint:MINT-16056	intact-miscore:0.56	-	psi-mi:"MI:0499"(unspecified role)	psi-mi:"MI:0499"(unspecified role)	psi-mi:"MI:0498"(prey)	psi-mi:"MI:0496"(bait)	psi-mi:"MI:0326"(protein)	psi-mi:"MI:0326"(protein)	reactome:R-HSA-8856828|ensembl:ENST00000325590|ensembl:ENST00000356264|ensembl:ENSG00000078053|ensembl:ENSP00000317441|ensembl:ENSP00000348602|mint:P49418|go:"GO:0030672"(synaptic vesicle membrane)|rcsb pdb:5M61|rcsb pdb:5M5S|dip:DIP-40729N|go:"GO:0005543"(phospholipid binding)|go:"GO:0031256"(leading edge membrane)|go:"GO:0005886"(plasma membrane)|go:"GO:0048488"(synaptic vesicle endocytosis)|interpro:IPR035470|interpro:IPR036028|go:"GO:0005829"(cytosol)|go:"GO:0061024"(membrane organization)|go:"GO:0015629"(actin cytoskeleton)|refseq:NP_001626.1|interpro:IPR003005(Amphiphysin)|rcsb pdb:4ATM|go:"GO:0007268"(chemical synaptic transmission)|interpro:IPR003017(Amphiphysin, isoform 1)|refseq:NP_647477.1|rcsb pdb:3SOG|rcsb pdb:1UTC|interpro:IPR001452(Src homology-3)|rcsb pdb:1KY7|interpro:IPR004148(BAR)|go:"GO:0008021"(synaptic vesicle)|go:"GO:0006897"(endocytosis)|go:"GO:0030054"(cell junction)|interpro:IPR027267|mint:MINT-376294(identity)	go:"GO:0007420"(brain development)|go:"GO:0017124"(SH3 domain binding)|go:"GO:0004438"(phosphatidylinositol-3-phosphatase activity)|go:"GO:0012506"(vesicle membrane)|go:"GO:0030132"(clathrin coat of coated pit)|go:"GO:0043195"(terminal bouton)|go:"GO:0043812"(phosphatidylinositol-4-phosphate phosphatase activity)|go:"GO:0097060"(synaptic membrane)|reactome:R-HSA-1660499|reactome:R-HSA-1855183|reactome:R-HSA-1855204|ensembl:ENSP00000349903|go:"GO:0061024"(membrane organization)|go:"GO:0006836"(neurotransmitter transport)|go:"GO:0016082"(synaptic vesicle priming)|go:"GO:0016191"(synaptic vesicle uncoating)|go:"GO:0030117"(membrane coat)|go:"GO:0034595"(phosphatidylinositol phosphate 5-phosphatase activity)|go:"GO:0046488"(phosphatidylinositol metabolic process)|go:"GO:0046855"(inositol phosphate dephosphorylation)|go:"GO:0048489"(synaptic vesicle transport)|reactome:R-HSA-8856828|refseq:XP_016883989.1|interpro:IPR012677(Nucleotide-binding, alpha-beta plait)|mint:O43426|interpro:IPR034971|interpro:IPR034972|ensembl:ENST00000357345|go:"GO:0004439"(phosphatidylinositol-4,5-bisphosphate 5-phosphatase activity)|go:"GO:0003723"(RNA binding)|refseq:NP_001153774.1|refseq:NP_001153778.1|refseq:NP_003886.3|refseq:NP_982271.2|ensembl:ENSG00000159082|interpro:IPR015047(Region of unknown function DUF1866)|interpro:IPR005135(Endonuclease/exonuclease/phosphatase)|interpro:IPR000300(Inositol polyphosphate related phosphatase)|interpro:IPR000504(RNA recognition motif, RNP-1)|interpro:IPR002013(Synaptojanin, N-terminal)|rcsb pdb:1W80|rcsb pdb:2DNR|rcsb pdb:2VJ0|go:"GO:0048471"(perinuclear region of cytoplasm)|go:"GO:0005623"(cell)|interpro:IPR035979|interpro:IPR036691|go:"GO:0007612"(learning)|go:"GO:1904980"(positive regulation of endosome organization)|go:"GO:0048488"(synaptic vesicle endocytosis)|go:"GO:0005829"(cytosol)|go:"GO:0006661"(phosphatidylinositol biosynthetic process)|go:"GO:0043647"(inositol phosphate metabolic process)|go:"GO:0046856"(phosphatidylinositol dephosphorylation)|go:"GO:0098793"(presynapse)|mint:MINT-376287(identity)	-	function:May participate in mechanisms of regulated exocytosis in synapses and certain endocrine cell types. May control the properties of the membrane associated cytoskeleton|comment:mint|comment:homomint|function:Antibodies against AMPH are detected in patients with stiff-man syndrome, a rare disease of the central nervous system characterized by progressive rigidity of the body musculature with superimposed painful spasms|comment:"Stoichiometry: 1.0"	comment:"Stoichiometry: 1.0"	comment:homomint|comment:domino|comment:mint	taxid:-1(in vitro)|taxid:-1(In vitro)	-	2001/01/10	2014/10/16	rogid:vrgVrVoYr45cUe4X6L/zBAE1RtU9606	rogid:RA73eMbCn6F7MD0ItxF/V7QbjqM9606	intact-crc:F4234557A3B54840|rigid:n+UcEH4PPLkFnIyvBiXrLefK/xU	false	binding-associated region:626-695(MINT-376295)	binding-associated region:1063-1070(MINT-376288)	-	-	psi-mi:"MI:0078"(nucleotide sequence identification)	psi-mi:"MI:0078"(nucleotide sequence identification)
uniprotkb:P49418	intact:EBI-7121639	intact:EBI-7121510|uniprotkb:Q75MK5|uniprotkb:Q75MM3|uniprotkb:A4D1X9|intact:MINT-109264|uniprotkb:O43538|uniprotkb:A4D1X8|uniprotkb:Q75MJ8|uniprotkb:Q8N4G0	intact:MINT-8094608	psi-mi:amph_human(display_long)|uniprotkb:AMPH(gene name)|psi-mi:AMPH(display_short)|uniprotkb:AMPH1(gene name synonym)	psi-mi:vrparrvlw(display_short)|psi-mi:EBI-7121639(display_long)	psi-mi:"MI:0084"(phage display)	Cestra et al. (1999)	pubmed:10542231|mint:MINT-5211933	taxid:9606(human)|taxid:9606(Homo sapiens)	taxid:-2(chemical synthesis)|taxid:-2("Chemical synthesis (Chemical synthesis)")	psi-mi:"MI:0407"(direct interaction)	psi-mi:"MI:0471"(MINT)	intact:EBI-7121634|mint:MINT-8094596	intact-miscore:0.44	-	psi-mi:"MI:0499"(unspecified role)	psi-mi:"MI:0499"(unspecified role)	psi-mi:"MI:0498"(prey)	psi-mi:"MI:0496"(bait)	psi-mi:"MI:0326"(protein)	psi-mi:"MI:0327"(peptide)	reactome:R-HSA-8856828|ensembl:ENST00000325590|ensembl:ENST00000356264|ensembl:ENSG00000078053|ensembl:ENSP00000317441|ensembl:ENSP00000348602|mint:P49418|go:"GO:0030672"(synaptic vesicle membrane)|rcsb pdb:5M61|rcsb pdb:5M5S|dip:DIP-40729N|go:"GO:0005543"(phospholipid binding)|go:"GO:0031256"(leading edge membrane)|go:"GO:0005886"(plasma membrane)|go:"GO:0048488"(synaptic vesicle endocytosis)|interpro:IPR035470|interpro:IPR036028|go:"GO:0005829"(cytosol)|go:"GO:0061024"(membrane organization)|go:"GO:0015629"(actin cytoskeleton)|refseq:NP_001626.1|interpro:IPR003005(Amphiphysin)|rcsb pdb:4ATM|go:"GO:0007268"(chemical synaptic transmission)|interpro:IPR003017(Amphiphysin, isoform 1)|refseq:NP_647477.1|rcsb pdb:3SOG|rcsb pdb:1UTC|interpro:IPR001452(Src homology-3)|rcsb pdb:1KY7|interpro:IPR004148(BAR)|go:"GO:0008021"(synaptic vesicle)|go:"GO:0006897"(endocytosis)|go:"GO:0030054"(cell junction)|interpro:IPR027267|mint:MINT-8094601(identity)	mint:MINT-8094610(identity)	-	function:May participate in mechanisms of regulated exocytosis in synapses and certain endocrine cell types. May control the properties of the membrane associated cytoskeleton|comment:mint|comment:homomint|function:Antibodies against AMPH are detected in patients with stiff-man syndrome, a rare disease of the central nervous system characterized by progressive rigidity of the body musculature with superimposed painful spasms	comment:mint|no-uniprot-update:	figure legend:F1 F2|comment:domino|comment:mint	taxid:-1(in vitro)|taxid:-1(In vitro)	-	2001/01/10	2014/10/16	rogid:vrgVrVoYr45cUe4X6L/zBAE1RtU9606	rogid:lPy6gBhpgvyGSYgOqeHbjcqBtMQ-2	intact-crc:880913D9000BF26E|rigid:iRObbQsaUeX0PxuKeQwZ73s8iKU	false	binding-associated region:626-695(MINT-8094602)	-	-	-	psi-mi:"MI:0078"(nucleotide sequence identification)	psi-mi:"MI:0083"(peptide synthesis)
uniprotkb:P49418	intact:EBI-7121654	intact:EBI-7121510|uniprotkb:Q75MK5|uniprotkb:Q75MM3|uniprotkb:A4D1X9|intact:MINT-109264|uniprotkb:O43538|uniprotkb:A4D1X8|uniprotkb:Q75MJ8|uniprotkb:Q8N4G0	intact:MINT-8094645	psi-mi:amph_human(display_long)|uniprotkb:AMPH(gene name)|psi-mi:AMPH(display_short)|uniprotkb:AMPH1(gene name synonym)	psi-mi:hrpvrraap(display_short)|psi-mi:EBI-7121654(display_long)	psi-mi:"MI:0084"(phage display)	Cestra et al. (1999)	pubmed:10542231|mint:MINT-5211933	taxid:9606(human)|taxid:9606(Homo sapiens)	taxid:-2(chemical synthesis)|taxid:-2("Chemical synthesis (Chemical synthesis)")	psi-mi:"MI:0407"(direct interaction)	psi-mi:"MI:0471"(MINT)	intact:EBI-7121659|mint:MINT-8094627	intact-miscore:0.44	-	psi-mi:"MI:0499"(unspecified role)	psi-mi:"MI:0499"(unspecified role)	psi-mi:"MI:0498"(prey)	psi-mi:"MI:0496"(bait)	psi-mi:"MI:0326"(protein)	psi-mi:"MI:0327"(peptide)	reactome:R-HSA-8856828|ensembl:ENST00000325590|ensembl:ENST00000356264|ensembl:ENSG00000078053|ensembl:ENSP00000317441|ensembl:ENSP00000348602|mint:P49418|go:"GO:0030672"(synaptic vesicle membrane)|rcsb pdb:5M61|rcsb pdb:5M5S|dip:DIP-40729N|go:"GO:0005543"(phospholipid binding)|go:"GO:0031256"(leading edge membrane)|go:"GO:0005886"(plasma membrane)|go:"GO:0048488"(synaptic vesicle endocytosis)|interpro:IPR035470|interpro:IPR036028|go:"GO:0005829"(cytosol)|go:"GO:0061024"(membrane organization)|go:"GO:0015629"(actin cytoskeleton)|refseq:NP_001626.1|interpro:IPR003005(Amphiphysin)|rcsb pdb:4ATM|go:"GO:0007268"(chemical synaptic transmission)|interpro:IPR003017(Amphiphysin, isoform 1)|refseq:NP_647477.1|rcsb pdb:3SOG|rcsb pdb:1UTC|interpro:IPR001452(Src homology-3)|rcsb pdb:1KY7|interpro:IPR004148(BAR)|go:"GO:0008021"(synaptic vesicle)|go:"GO:0006897"(endocytosis)|go:"GO:0030054"(cell junction)|interpro:IPR027267|mint:MINT-8094629(identity)	mint:MINT-8094647(identity)	-	function:May participate in mechanisms of regulated exocytosis in synapses and certain endocrine cell types. May control the properties of the membrane associated cytoskeleton|comment:mint|comment:homomint|function:Antibodies against AMPH are detected in patients with stiff-man syndrome, a rare disease of the central nervous system characterized by progressive rigidity of the body musculature with superimposed painful spasms	comment:mint|no-uniprot-update:	figure legend:F1 F2|comment:domino|comment:mint	taxid:-1(in vitro)|taxid:-1(In vitro)	-	2001/01/10	2014/10/16	rogid:vrgVrVoYr45cUe4X6L/zBAE1RtU9606	rogid:GsDnsVmAo8ZZoKTZ4/uDb2x+h3c-2	intact-crc:B68245E1F0508124|rigid:SlinPP5vIczFOGZjelrO+IXJq1s	false	binding-associated region:626-695(MINT-8094630)	-	-	-	psi-mi:"MI:0078"(nucleotide sequence identification)	psi-mi:"MI:0083"(peptide synthesis)
uniprotkb:P49418	intact:EBI-7121715	intact:EBI-7121510|uniprotkb:Q75MK5|uniprotkb:Q75MM3|uniprotkb:A4D1X9|intact:MINT-109264|uniprotkb:O43538|uniprotkb:A4D1X8|uniprotkb:Q75MJ8|uniprotkb:Q8N4G0	intact:MINT-8094663	psi-mi:amph_human(display_long)|uniprotkb:AMPH(gene name)|psi-mi:AMPH(display_short)|uniprotkb:AMPH1(gene name synonym)	psi-mi:vrptraada(display_short)|psi-mi:EBI-7121715(display_long)	psi-mi:"MI:0084"(phage display)	Cestra et al. (1999)	pubmed:10542231|mint:MINT-5211933	taxid:9606(human)|taxid:9606(Homo sapiens)	taxid:-2(chemical synthesis)|taxid:-2("Chemical synthesis (Chemical synthesis)")	psi-mi:"MI:0407"(direct interaction)	psi-mi:"MI:0471"(MINT)	intact:EBI-7121710|mint:MINT-8094651	intact-miscore:0.44	-	psi-mi:"MI:0499"(unspecified role)	psi-mi:"MI:0499"(unspecified role)	psi-mi:"MI:0498"(prey)	psi-mi:"MI:0496"(bait)	psi-mi:"MI:0326"(protein)	psi-mi:"MI:0327"(peptide)	reactome:R-HSA-8856828|ensembl:ENST00000325590|ensembl:ENST00000356264|ensembl:ENSG00000078053|ensembl:ENSP00000317441|ensembl:ENSP00000348602|mint:P49418|go:"GO:0030672"(synaptic vesicle membrane)|rcsb pdb:5M61|rcsb pdb:5M5S|dip:DIP-40729N|go:"GO:0005543"(phospholipid binding)|go:"GO:0031256"(leading edge membrane)|go:"GO:0005886"(plasma membrane)|go:"GO:0048488"(synaptic vesicle endocytosis)|interpro:IPR035470|interpro:IPR036028|go:"GO:0005829"(cytosol)|go:"GO:0061024"(membrane organization)|go:"GO:0015629"(actin cytoskeleton)|refseq:NP_001626.1|interpro:IPR003005(Amphiphysin)|rcsb pdb:4ATM|go:"GO:0007268"(chemical synaptic transmission)|interpro:IPR003017(Amphiphysin, isoform 1)|refseq:NP_647477.1|rcsb pdb:3SOG|rcsb pdb:1UTC|interpro:IPR001452(Src homology-3)|rcsb pdb:1KY7|interpro:IPR004148(BAR)|go:"GO:0008021"(synaptic vesicle)|go:"GO:0006897"(endocytosis)|go:"GO:0030054"(cell junction)|interpro:IPR027267|mint:MINT-8094653(identity)	mint:MINT-8094665(identity)	-	function:May participate in mechanisms of regulated exocytosis in synapses and certain endocrine cell types. May control the properties of the membrane associated cytoskeleton|comment:mint|comment:homomint|function:Antibodies against AMPH are detected in patients with stiff-man syndrome, a rare disease of the central nervous system characterized by progressive rigidity of the body musculature with superimposed painful spasms	comment:mint|no-uniprot-update:	figure legend:F1 F2|comment:domino|comment:mint	taxid:-1(in vitro)|taxid:-1(In vitro)	-	2001/01/10	2014/10/16	rogid:vrgVrVoYr45cUe4X6L/zBAE1RtU9606	rogid:M+f6GE3SQXrMBiIVttW1MKhz3M0-2	intact-crc:8F7F46DF027DF34E|rigid:uiWbDe6gaj1XcnJXNxuLLq2l/m4	false	binding-associated region:626-695(MINT-8094654)	-	-	-	psi-mi:"MI:0078"(nucleotide sequence identification)	psi-mi:"MI:0083"(peptide synthesis)
intact:EBI-7121765	uniprotkb:P49418	intact:MINT-8094691	intact:EBI-7121510|uniprotkb:Q75MK5|uniprotkb:Q75MM3|uniprotkb:A4D1X9|intact:MINT-109264|uniprotkb:O43538|uniprotkb:A4D1X8|uniprotkb:Q75MJ8|uniprotkb:Q8N4G0	psi-mi:xrprrghal(display_short)|psi-mi:EBI-7121765(display_long)	psi-mi:amph_human(display_long)|uniprotkb:AMPH(gene name)|psi-mi:AMPH(display_short)|uniprotkb:AMPH1(gene name synonym)	psi-mi:"MI:0084"(phage display)	Cestra et al. (1999)	pubmed:10542231|mint:MINT-5211933	taxid:-2(chemical synthesis)|taxid:-2("Chemical synthesis (Chemical synthesis)")	taxid:9606(human)|taxid:9606(Homo sapiens)	psi-mi:"MI:0407"(direct interaction)	psi-mi:"MI:0471"(MINT)	intact:EBI-7121760|mint:MINT-8094677	intact-miscore:0.44	-	psi-mi:"MI:0499"(unspecified role)	psi-mi:"MI:0499"(unspecified role)	psi-mi:"MI:0496"(bait)	psi-mi:"MI:0498"(prey)	psi-mi:"MI:0327"(peptide)	psi-mi:"MI:0326"(protein)	mint:MINT-8094693(identity)	reactome:R-HSA-8856828|ensembl:ENST00000325590|ensembl:ENST00000356264|ensembl:ENSG00000078053|ensembl:ENSP00000317441|ensembl:ENSP00000348602|mint:P49418|go:"GO:0030672"(synaptic vesicle membrane)|rcsb pdb:5M61|rcsb pdb:5M5S|dip:DIP-40729N|go:"GO:0005543"(phospholipid binding)|go:"GO:0031256"(leading edge membrane)|go:"GO:0005886"(plasma membrane)|go:"GO:0048488"(synaptic vesicle endocytosis)|interpro:IPR035470|interpro:IPR036028|go:"GO:0005829"(cytosol)|go:"GO:0061024"(membrane organization)|go:"GO:0015629"(actin cytoskeleton)|refseq:NP_001626.1|interpro:IPR003005(Amphiphysin)|rcsb pdb:4ATM|go:"GO:0007268"(chemical synaptic transmission)|interpro:IPR003017(Amphiphysin, isoform 1)|refseq:NP_647477.1|rcsb pdb:3SOG|rcsb pdb:1UTC|interpro:IPR001452(Src homology-3)|rcsb pdb:1KY7|interpro:IPR004148(BAR)|go:"GO:0008021"(synaptic vesicle)|go:"GO:0006897"(endocytosis)|go:"GO:0030054"(cell junction)|interpro:IPR027267|mint:MINT-8094679(identity)	-	comment:mint|no-uniprot-update:	function:May participate in mechanisms of regulated exocytosis in synapses and certain endocrine cell types. May control the properties of the membrane associated cytoskeleton|comment:mint|comment:homomint|function:Antibodies against AMPH are detected in patients with stiff-man syndrome, a rare disease of the central nervous system characterized by progressive rigidity of the body musculature with superimposed painful spasms	figure legend:F1 F2|comment:domino|comment:mint	taxid:-1(in vitro)|taxid:-1(In vitro)	-	2001/01/10	2014/10/16	rogid:cilZXZ3YLDI1s7n8WNRyUE6EnJQ-2	rogid:vrgVrVoYr45cUe4X6L/zBAE1RtU9606	intact-crc:2EF43A66D9B2D428|rigid:/ki1zA46M4Q+rHcjCcxNhhk+5Xw	false	-	binding-associated region:626-695(MINT-8094680)	-	-	psi-mi:"MI:0083"(peptide synthesis)	psi-mi:"MI:0078"(nucleotide sequence identification)
uniprotkb:P49418	intact:EBI-7121785	intact:EBI-7121510|uniprotkb:Q75MK5|uniprotkb:Q75MM3|uniprotkb:A4D1X9|intact:MINT-109264|uniprotkb:O43538|uniprotkb:A4D1X8|uniprotkb:Q75MJ8|uniprotkb:Q8N4G0	intact:MINT-8094718	psi-mi:amph_human(display_long)|uniprotkb:AMPH(gene name)|psi-mi:AMPH(display_short)|uniprotkb:AMPH1(gene name synonym)	psi-mi:hrptrskla(display_short)|psi-mi:EBI-7121785(display_long)	psi-mi:"MI:0084"(phage display)	Cestra et al. (1999)	pubmed:10542231|mint:MINT-5211933	taxid:9606(human)|taxid:9606(Homo sapiens)	taxid:-2(chemical synthesis)|taxid:-2("Chemical synthesis (Chemical synthesis)")	psi-mi:"MI:0407"(direct interaction)	psi-mi:"MI:0471"(MINT)	intact:EBI-7121780|mint:MINT-8094706	intact-miscore:0.44	-	psi-mi:"MI:0499"(unspecified role)	psi-mi:"MI:0499"(unspecified role)	psi-mi:"MI:0498"(prey)	psi-mi:"MI:0496"(bait)	psi-mi:"MI:0326"(protein)	psi-mi:"MI:0327"(peptide)	reactome:R-HSA-8856828|ensembl:ENST00000325590|ensembl:ENST00000356264|ensembl:ENSG00000078053|ensembl:ENSP00000317441|ensembl:ENSP00000348602|mint:P49418|go:"GO:0030672"(synaptic vesicle membrane)|rcsb pdb:5M61|rcsb pdb:5M5S|dip:DIP-40729N|go:"GO:0005543"(phospholipid binding)|go:"GO:0031256"(leading edge membrane)|go:"GO:0005886"(plasma membrane)|go:"GO:0048488"(synaptic vesicle endocytosis)|interpro:IPR035470|interpro:IPR036028|go:"GO:0005829"(cytosol)|go:"GO:0061024"(membrane organization)|go:"GO:0015629"(actin cytoskeleton)|refseq:NP_001626.1|interpro:IPR003005(Amphiphysin)|rcsb pdb:4ATM|go:"GO:0007268"(chemical synaptic transmission)|interpro:IPR003017(Amphiphysin, isoform 1)|refseq:NP_647477.1|rcsb pdb:3SOG|rcsb pdb:1UTC|interpro:IPR001452(Src homology-3)|rcsb pdb:1KY7|interpro:IPR004148(BAR)|go:"GO:0008021"(synaptic vesicle)|go:"GO:0006897"(endocytosis)|go:"GO:0030054"(cell junction)|interpro:IPR027267|mint:MINT-8094708(identity)	mint:MINT-8094720(identity)	-	function:May participate in mechanisms of regulated exocytosis in synapses and certain endocrine cell types. May control the properties of the membrane associated cytoskeleton|comment:mint|comment:homomint|function:Antibodies against AMPH are detected in patients with stiff-man syndrome, a rare disease of the central nervous system characterized by progressive rigidity of the body musculature with superimposed painful spasms	comment:mint|no-uniprot-update:	figure legend:F1 F2|comment:domino|comment:mint	taxid:-1(in vitro)|taxid:-1(In vitro)	-	2001/01/10	2014/10/16	rogid:vrgVrVoYr45cUe4X6L/zBAE1RtU9606	rogid:Q9S7HY0OYKNz5U6ndWhu91PBN5Q-2	intact-crc:84BF0FF8155AE7F8|rigid:XLOVWM25uJw3qiUhKe6tGNH97NE	false	binding-associated region:626-695(MINT-8094709)	-	-	-	psi-mi:"MI:0078"(nucleotide sequence identification)	psi-mi:"MI:0083"(peptide synthesis)
```

Each interaction contains 

Column | Version |    Field    | Arity |         Structure         | Example
------ | ------- | ----------- | ----- | ------------------------- | -------
7      | MITAB25 | Method      |  0..* | database:identifier(name) | psi-mi:"MI:0084"(phage display)
8      | MITAB25 | Author      |  0..* | author                    | Cestra et al. (1999)
9      | MITAB25 | Publication |  0..* | database:identifier       | pubmed:10542231
12     | MITAB25 | Type        |  0..* | database:identifier(name) | psi-mi:"MI:0407"(direct interaction)
13     | MITAB25 | Database    |  0..* | database:identifier(name) | psi-mi:"MI:0471"(MINT)
14     | MITAB25 | Identifier  |  0..* | database:identifier       | intact:EBI-7121552
15     | MITAB26 | Confidence  |  0..* | type:score                | intact-miscore:0.56
16     | MITAB26 | Expansion   |  0..* | database:identifier(name) | psi-mi:"MI:1060"(spoke expansion)
25     | MITAB26 | Cross ref   |  0..* | database:identifier(text) | go:"GO:0005643"(nuclear pore)
28     | MITAB26 | Annotations |  0..* | topic:text                | comment:homomint
29     | MITAB26 | Organism    |  0..* | database:identifier(text) | taxid:-1(in vitro)
30     | MITAB26 | Parameters  |  0..* | type:value(text)          | kd:"4.0x2^5 ~0.3"|
31     | MITAB26 | Creation    |  0..1 | yyyy/mm/dd                | 2001/01/10
32     | MITAB26 | Update      |  0..1 | yyyy/mm/dd                | 2014/10/16
35     | MITAB26 | Checksum    |  0..* | method:checksum           | rigid:n+UcEH4PPLkFnIyvBiXrLefK/xU
36     | MITAB26 | Negative    |  0..1 | negative                  | false
45     | MITAB28 | Regulatory  |  0..* | database:identifier(name) | psi-mi:"MI:2247"(transcriptional regulation)
46     | MITAB28 | Statement   |  0..* | database:identifier(name) | psi-mi:"MI:2240"(down regulates)

and the interactors

Columns | Version |    Field      | Arity |         Structure         | Example
------- | ------- | ------------- | ----- | ------------------------- | -------
1, 2    | MITAB25 | Identifier    |  0..* | database:identifier       | uniprotkb:P49418
3, 4    | MITAB25 | Alt. Ids      |  0..* | database:identifier       | intact:EBI-7121510
5, 6    | MITAB25 | Aliases       |  0..* | database:name(type)       | uniprotkb:AMPH(gene name)
10, 11  | MITAB25 | Taxid         |  0..* | taxid:identifier(name)    | taxid:9606(human)
17, 18  | MITAB26 | Bio roles     |  0..* | database:identifier(name) | psi-mi:"MI:0499"(unspecified role)
19, 20  | MITAB26 | Exp roles     |  0..* | database:identifier(name) | psi-mi:"MI:0498"(prey)
21, 22  | MITAB26 | Type          |  0..* | database:identifier(name) | psi-mi:"MI:0326"(protein)
23, 24  | MITAB26 | Cross refs    |  0..* | database:identifier(name) | reactome:R-HSA-8856828
26, 27  | MITAB26 | Annotations   |  0..* | topic:text                | comment:homomint
33, 34  | MITAB26 | Checksums     |  0..* | method:checksum           | rogid:vrgVrVoYr45cUe4X6L/zBAE1RtU9606
37, 38  | MITAB27 | Features      |  0..* | type:range(text)          | binding-associated region:626-695(MINT-376295)
39, 40  | MITAB27 | Stoichiometry |  0..1 | stoichiometry             | 2
41, 42  | MITAB27 | Method        |  0..* | database:identifier(name) | psi-mi:"MI:0078"(nucleotide sequence identification)
43, 44  | MITAB28 | Effect        |  0..* | database:identifier(name) | go:"GO:0016301"(kinase activity)

For extended documentation, visit https://psicquic.github.io/PSIMITAB.html

## Features
- supports txt, gzip and zip files
- reads from standard input
- writes into standard output
- MITAB 2.5, 2.6, 2.7, 2.8

There are 2 ways to use this library:
- [As a Java library](#java-library)
- [As a command line tool](#command-line)

## <a name="java-library"></a> As a Java library
Download source code
```
git clone https://github.com/pasculorente/mitab-io
```
Install library
```
mvn install
```

Import to your project
```xml
<dependency>
    <groupId>org.uichuimi</groupId>
    <artifactId>mitab-io</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```
### Reading
*mitab-io* provides one class to read PSI-MI TAB files (**org.uichuimi.mitab.io.input.InteractionReader**). This
class allows you to iterate over the interactions of a file in 4 ways, choose your favourite:
* read all
* for loop
* stream
* iterator

```java
import org.uichuimi.mitab.io.input.InteractionReader;
import org.uichuimi.mitab.io.model.Interaction;

import java.io.File;import java.util.List;

class Main { 
    public static void main(String[] args) throws Exception {
        File intact = new File("intact.txt.gz");
        try (InteractionReader reader = new InteractionReader(intact)) {
            // read all at once
            final List<Interaction> interactions = reader.readAll();

            // for loop
            for (Interaction interaction : reader) { 
            }

            // stream
            reader.interactions().filter(interaction -> true).forEach(interaction -> {});

            // as an iterator?
            while (reader.hasNext()) {
            	final Interaction interaction = reader.next();
            }
        } 
    }
}
```

### Writing
Currently, we provide 3 writers: psi, tsv and neo4j.

### PSI

You can create or modify Interaction objects for later writing:

```java
import org.uichuimi.mitab.io.output.InteractionWriter;
import org.uichuimi.mitab.io.model.Interactor;
import org.uichuimi.mitab.io.model.Interaction;import java.io.File;import java.io.FileOutputStream;

class Main { 
    public static void main(String[] args) {
        Interactor a = new Interactor();
        Interactor b = new Interactor();
        Interaction interaction = new Interaction(a, b);
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
        final OutputStream outputStream = new FileOutputStream(new File(("output.mitab27")));
        try (InteractionWriter writer = new InteractionWriter(outputStream)) {
            writer.write(interaction);
        }
    }
}
```

#### Tab Separated Value (TSV)
Creates a TSV file with the following fields:

For each column, we extract the first value

_xref:**value**(description)|xref:value(description)..._

Column |    Name    | PSI-MI column
------ | ---------- | -------------
1      | ID         |            14
2      | TYPE       |            12
3      | METHOD     |             7
4      | ORGANISM   |            29
5      | SCORE      |            15
6      | A_ID       |             1
7      | B_ID       |             2
8      | A_BIO_ROLE |            17
9      | B_BIO_ROLE |            18
10     | A_EXP_ROLE |            19
11     | B_EXP_ROLE |            20      

The file looks like

ID  | TYPE | METHOD | ORGANISM | SCORE | A_ID | B_ID | A_BIO_ROLE | B_BIO_ROLE | A_EXP_ROLE | B_EXP_ROLE
--- | --- | --- | --- | --- | --- | --- | --- | --- | --- | ---  
EBI-7121552 | MI:0407 | MI:0084 | -1 | 0.56 | P49418 | O43426 | MI:0499 | MI:0499 | MI:0498 | MI:0496
EBI-7121634 | MI:0407 | MI:0084 | -1 | 0.44 | P49418 | EBI-7121639 | MI:0499 | MI:0499 | MI:0498 | MI:0496


Print to standard output
```java
import org.uichuimi.mitab.io.input.InteractionReader;
import org.uichuimi.mitab.io.output.TsvWriter;
import java.io.File;

class Main { 
    public static void main(String[] args) throws Exception {
        File intact = new File("intact.txt.gz");
        try (InteractionReader reader = new InteractionReader(intact);
            TsvWriter writer = new TsvWriter(System.out)) {
            reader.forEach(writer);
        } 
    }
}
```

### Neo4j
_neo4j-admin import_ and cypher **IMPORT CSV** use a particular TSV/CSV file format. The ways a PSI-TAB
file can be split into edge and node files are many, and the discussion of which one is best is out of this
scope. We present a way of splitting them, which will serve for you to implement your own writer.

```java
import org.uichuimi.mitab.io.input.InteractionReader;
import org.uichuimi.mitab.io.output.Neo4jWriter;
import java.io.File;

class Main { 
    public static void main(String[] args) throws Exception {
        File intact = new File("intact.txt.gz");
        try (InteractionReader reader = new InteractionReader(intact);
            Neo4jWriter writer = new Neo4jWriter(System.out)) {
            reader.forEach(writer);
        } 
    }
}
```

Created files are:

**nodes.tsv.gz**

Column |    Name    | PSI-MI column
------ | ---------- | -------------
1      | identifier:ID(interactor) | 1 / 2

Example:

identifier:ID(interactor) | 
--- |
EBI-7121639 |
EBI-7121654 |
EBI-7121715 |
EBI-7121765 |
EBI-7121785 |
EBI-7121821 |

**relationships.tsv.gz**

Column |          Name         | PSI-MI column
------ | --------------------- | -------------
1      | :START_ID(interactor) |             1
2      | :END_ID(interactor)   |             2
3      | identifier            |            14
4      | type                  |            12
5      | method                |             7
6      | score                 |            15

Example:

:START_ID(interactor) | :END_ID(interactor) | identifier  |  type   | method  | score
 -------------- | ------------- | ----------- | ------- | ------- | -----
P34709          |     P34708    | EBI-640637  | MI:0915 | MI:0047 | 0.51
P34708-1        |     P34709    | EBI-367231  | MI:0915 | MI:0018 | 0.51
P49418          | EBI-7121639   | EBI-7121634 | MI:0407 | MI:0084 | 0.44
P49418          | EBI-7121654   | EBI-7121659 | MI:0407 | MI:0084 | 0.44
P49418          | EBI-7121715   | EBI-7121710 | MI:0407 | MI:0084 | 0.44
EBI-7121765     |     P49418    | EBI-7121760 | MI:0407 | MI:0084 | 0.44


## <a name="command-line"></a> As a command line tool
> **NOTE**: this option is the less recommended, since this script provides little functionality.

Compile and package the library into a jar.
```
mvn clean compile assembly:single
```
Check if it works
```
java -jar target/mitab-io-1.0-SNAPSHOT-jar-with-dependencies.jar --help
```
You should see something like
```
Usage: mitab [-hv] [-i=<input>] [--neo4j=<neo4j>] [-o=<output>]
umpteenth package with tools to work with PSI MITAB files
  -h, --help              display this help message
  -i, --input=<input>
      --neo4j=<neo4j>
  -o, --output=<output>
  -v, --verbose
```
With this option you can export PSI-MI TAB files into tsv or _neo4j-like_ files.
```
java -jar target/mitab-io-1.0-SNAPSHOT-jar-with-dependencies.jar \
    --input my-file.txt.gz \
    --neo4j neo4jfiles \
    --output simplified.tsv
```