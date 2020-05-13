# MITAB I/O

Small library to work with, i.e. _parse_, PSI-MI TAB files (https://psicquic.github.io/).

> This is NOT the official PSI-MI TAB library, neither pretends to replace it. To get 
> the official Java client, try:
>
> - https://mvnrepository.com/artifact/psidev.psi.mi/psimitab
> - http://www.psidev.info/groups/molecular-interactions.

PSI-MI stands for Proteomics Standards Initiative - Molecular Interaction. 

Long story short, this library helps parsing PSI-MI TAB files (like intact.txt.gz, or BIOGRID-ALL-3.5.185.mitab.zip).

## Features
- supports txt, gzip and zip files
- reads from standard input
- writes into standard output

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
*mitab-io* provides one class to read PSI-MI TAB files (**org.uichuimi.mitab.io.InteractionReader**). This
class allows you to iterate over the interactions of a file in 4 ways, choose your favourite:
* read all
* for loop
* stream
* iterator

```java
import org.uichuimi.mitab.io.InteractionReader;
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
Currently, we provide only 2 writers: tsv and neo4j.

#### Tab Separated Value (TSV)
Creates a TSV file with the following fields:

Remember that every PSI-MI field follows the syntax:

_xref:value(description)|xref:value(description)..._

For each column, we extract the first value

_xref:**value**(description)|xref:value(description)..._

 Column |    Name    | PSI-MI column
 ------ | ---------- | -------------
      1 | ID         |            14
      2 | TYPE       |            12
      3 | METHOD     |             7
      4 | ORGANISM   |            29
      5 | SCORE      |            15
      6 | A_ID       |             1
      7 | B_ID       |             2
      8 | A_BIO_ROLE |            17
      9 | B_BIO_ROLE |            18
     10 | A_EXP_ROLE |            19
     11 | B_EXP_ROLE |            20      

The file looks like

ID | TYPE | METHOD | ORGANISM | SCORE | A_ID | B_ID | A_BIO_ROLE | B_BIO_ROLE | A_EXP_ROLE | B_EXP_ROLE
--- | --- | --- | --- | --- | --- | --- | --- | --- | --- | ---  
EBI-7121552 | MI:0407 | MI:0084 | -1 | 0.56 | P49418 | O43426 | MI:0499 | MI:0499 | MI:0498 | MI:0496
EBI-7121634 | MI:0407 | MI:0084 | -1 | 0.44 | P49418 | EBI-7121639 | MI:0499 | MI:0499 | MI:0498 | MI:0496


Print to standard output
```java
import org.uichuimi.mitab.io.InteractionReader;
import org.uichuimi.mitab.io.model.Interaction;

import java.io.File;import java.util.List;

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
import org.uichuimi.mitab.io.InteractionReader;
import org.uichuimi.mitab.io.model.Interaction;

import java.io.File;import java.util.List;

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

Created files are:

**nodes.tsv.gz**

 Column |    Name    | PSI-MI column
 ------ | ---------- | -------------
      1 | identifier:ID(interactor) | 1 / 2

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
      1 | :START_ID(interactor) |             1
      2 | :END_ID(interactor)   |             2
      3 | identifier            |            14
      4 | type                  |            12
      5 | method                |             7
      6 | score                 |            15

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