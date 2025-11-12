CSE 464 – Project Part 1

Student: Cyler Minkus
ASU Email: cminkus@asu.edu
Repository: https://github.com/cminkus/CSE-464-2025-cminkus

Project Overview:
This project demonstrates how to parse, manage, and export graphs using Graphviz and JGraphT.  This project consists of four main features, each implemented and tested separately and can be built and tested using Maven commands.

Setup Instructions:
Necessities: Java 17+, Apache Maven, Graphviz installed and available in PATH.
To verify Graphviz installation: use "dot -V" in the terminal

Clone and build my project:
git clone https://github.com/cminkus/CSE-464-2025-cminkus.git
cd CSE-464-2025-cminkus
mvn clean package

Run tests:
mvn test (you should see "[INFO] BUILD SUCCESS" after running)

Run the Main Program:
java -cp target/CSE-464-2025-cminkus-1.0.0.jar edu.asu.cse464.graph.Main

-----------------------------------------------------------------------------------------------------------------------------
Features and Example Output

Feature 1: Parse DOT Graph
Method: parseGraph(String filepath)
Description: Reads DOT file and creates a directed graph.

Example Input (sample.dot):
digraph G {
  a -> b;
  b -> c;
  c -> a;
}

Example Output:
Graph parsed successfully!
Graph:
Vertices (3): [a, b, c]
Edges (3):
  a -> b
  b -> c
  c -> a

Screenshot of Expected Output:
<img width="705" height="301" alt="image" src="https://github.com/user-attachments/assets/b458eb00-fd51-48da-98fa-968725700ac6" />

Link to Commit:
https://github.com/cminkus/CSE-464-2025-cminkus/commit/599304c98e3b7c10211c4217e9a14a40ee71ae87

Feature 2: Add Nodes
Methods: addNode(String label), addNodes(String[] labels)
Description: Adds one or multiple nodes and rejects duplicate nodes.

Example Output:
true
false
3
Graph:
Vertices (4): [A, B, C, D]
Edges (0):

Screenshot of Expected Output:
<img width="259" height="160" alt="image" src="https://github.com/user-attachments/assets/69b4a73a-3be6-4ef6-9dd4-1b4aeebe5b09" />

Link to Commit:
https://github.com/cminkus/CSE-464-2025-cminkus/commit/40cd06a997f0d34889dea9f6ec48543725e48cd7

Feature 3: Add Edges
Method: addEdge(String src, String dst)
Description: Adds an edge between two nodes, ensuring no duplicates.

Example Output:
true
false
true
Graph:
Vertices (3): [A, B, C]
Edges (2):
  A -> B
  B -> C


Screenshot of Expected Output:
<img width="295" height="213" alt="image" src="https://github.com/user-attachments/assets/0d5655e8-3d6a-40a8-9d64-eff12c24099d" />

Link to Commit:
https://github.com/cminkus/CSE-464-2025-cminkus/commit/3d43a39611a9863a4bd29a22e2cede672120c7ec

Feature 4: Output Graph
Methods: outputDOTGraph(String path), outputGraphics(String path, String format)
Description: Creates DOT file and PNG image of the graph

Example Output:
DOT file created: output.dot
DOT file created: temp.dot


Screenshot of Expected Output:
<img width="304" height="72" alt="image" src="https://github.com/user-attachments/assets/ec3b025d-9f87-43af-a4c7-00d39a32bb16" />

Link to Commit:
https://github.com/cminkus/CSE-464-2025-cminkus/commit/fe09c46a61dd1a5742779e8546e1110ad003cbb2

-----------------------------------------------------------------------------------------------------------------------------
Unit Tests:
Each feature has a dedicated test class in "src/test/java/edu/asu/cse464/graph/"
Use mvn test to run all tests.

Expected Result:
Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS

Screenshot showing BUILD SUCCESS after running mvn package:
<img width="859" height="227" alt="image" src="https://github.com/user-attachments/assets/faa2a32c-282d-4b82-9559-2b4b35e15f24" />
------------------------------------------------------------------------------------------------------------------------------
Continuous Integration (CI):

[![Maven CI](https://github.com/cminkus/CSE-464-2025-cminkus/actions/workflows/maven.yml/badge.svg)](https://github.com/cminkus/CSE-464-2025-cminkus/actions/workflows/maven.yml)
------------------------------------------------------------------------------------------------------------------------------

