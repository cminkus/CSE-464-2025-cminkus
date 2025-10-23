package edu.asu.cse464.graph;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.nio.dot.DOTImporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class GraphService {
    private final Graph<String, DefaultEdge> graph =
            new DefaultDirectedGraph<>(DefaultEdge.class); //instantiate graph variables

    //TASK 1
    public void parseGraph(String filepath) {
        try {
            File file = new File(filepath);
            if (!file.exists()) {
                System.out.println("File not found: " + filepath); //check for path crodie
                return;
            }

            DOTImporter<String, DefaultEdge> importer = new DOTImporter<>(); //make the dot importer from the graph dependency thank u internet
            importer.setVertexFactory(label -> label); //label is vertex id

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr); //readers!

            importer.importGraph(graph, br); //import the graph and then close the files after!

            br.close();
            fr.close();

            System.out.println("Graph parsed successfully!");
        } catch (Exception e) {
            System.out.println("Error reading graph: " + e.getMessage());
        }
    }

    public boolean addNode(String label) {
        if (label == null || label.isEmpty()){
            return false;
        }
        if (graph.containsVertex(label)){   //add the individual node
            return false;
        }
        graph.addVertex(label);
        return true;
    }


    public int addNodes(String[] labels) {
        if (labels == null){
            return 0;
        }
        int added = 0;
        for (String s : labels){  //add each node from the list
            if (addNode(s)){
                added++;
            }
        }
        return added;
    }

    public boolean addEdge(String src, String dst) {
        if (src == null || dst == null || src.isEmpty() || dst.isEmpty()) {
            return false;   //check for null/empty
        }

        addNode(src);
        addNode(dst);   //make sure the endpoints exist or else that would be bad :(

        //check for dupes
        if (graph.containsEdge(src, dst)) {
            return false;
        }
        graph.addEdge(src, dst); //if everything is all good add the edge
        return true;
    }


    public void outputDOTGraph(String path) {
        //TO DO
    }

    public void outputGraphics(String path, String format) {
        //TO DO
    }


    @Override
    public String toString() {
        return "Graph: |Vertex|=" + graph.vertexSet().size() + ", |Edges|=" + graph.edgeSet().size();
    }



}
