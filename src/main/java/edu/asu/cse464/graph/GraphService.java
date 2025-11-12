package edu.asu.cse464.graph;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.nio.dot.DOTExporter;
import org.jgrapht.nio.dot.DOTImporter;

import java.io.*;

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
        try (java.io.Writer writer = new java.io.FileWriter(path)) {
            DOTExporter<String, DefaultEdge> exporter =
                    new DOTExporter<>();
            exporter.setVertexIdProvider(v -> v);

            exporter.exportGraph(graph, writer);
            System.out.println("DOT file created: " + path);
        } catch (java.io.IOException e) {
            System.out.println("Error creating DOT file: " + e.getMessage());
        }
    }


    public void outputGraphics(String path) {
        try {
            String tempDot = "temp.dot";    //create a temporary .dot file for outputting the graphics
            outputDOTGraph(tempDot);

            //set format to png bc that's what the project wants lol
            Format fmt = Format.PNG;

            //render the temp .dot file to an image
            Graphviz.fromFile(new java.io.File(tempDot)).render(fmt).toFile(new java.io.File(path));

            System.out.println("Image created: " + path);   //show that the image was created along with the path of the img
        } catch (Exception e) {
            System.out.println("Error creating image: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Graph:\n");
        result.append("Vertices (").append(graph.vertexSet().size()).append("): ").append(graph.vertexSet()).append("\n");
        result.append("Edges (").append(graph.edgeSet().size()).append("):\n");

        for (String v : graph.vertexSet()) {
            for (DefaultEdge e : graph.outgoingEdgesOf(v)) {
                String target = graph.getEdgeTarget(e); //append the vertices and edges
                result.append("  ").append(v).append(" -> ").append(target).append("\n");
            }
        }
        return result.toString();
    }

//STUFF FOR PROJECT PART 2

    public void removeNode(String label) {
        if (label == null){
            throw new IllegalArgumentException("label is null");
        }
        boolean flag = graph.removeVertex(label);
        if (!flag) {
            throw new java.util.NoSuchElementException("Node not found: " + label);
        }
    }

    public void removeNodes(String[] labels) {
        if (labels == null){
            throw new IllegalArgumentException("labels is null");
        }
        for (String label : labels) {
            if (label == null || !graph.containsVertex(label)) {
                throw new java.util.NoSuchElementException("Node not found: " + label);
            }
        }
        for (String label : labels) {
            graph.removeVertex(label);
        }
    }

    public void removeEdge(String srcLabel, String dstLabel) {
        if (srcLabel == null || dstLabel == null) {
            throw new IllegalArgumentException("src/dst is null");
        }
        if (!graph.containsVertex(srcLabel)) {
            throw new java.util.NoSuchElementException("Source node not found: " + srcLabel);
        }
        if (!graph.containsVertex(dstLabel)) {
            throw new java.util.NoSuchElementException("Destination node not found: " + dstLabel);
        }
        var edge = graph.getEdge(srcLabel, dstLabel);
        if (edge == null) {
            throw new java.util.NoSuchElementException("Edge not found: " + srcLabel + " -> " + dstLabel);
        }
        graph.removeEdge(edge);
    }

    //PART 5: Complete GraphSearch with Algorithm parameter added!  yay!!!!!!!!!!!!!!!!!!!!!!!
    public Path GraphSearch(String src, String dst, Algorithm algo) {
        if (algo == null) {
            throw new IllegalArgumentException("Algorithm cannot be null");
        }

        if (algo == Algorithm.BFS) {
            return bfsSearch(src, dst);
        }

        if (algo == Algorithm.DFS) {
            return dfsSearch(src, dst);
        }

        throw new IllegalArgumentException("Unknown algorithm: " + algo);
    }

    private Path bfsSearch(String src, String dst) {
        if (src == null || dst == null) {
            return null;
        }
        if (!graph.containsVertex(src) || !graph.containsVertex(dst)) {
            return null;
        }

        java.util.Queue<String> q = new java.util.ArrayDeque<>();
        java.util.Map<String, String> parent = new java.util.HashMap<>();

        q.add(src);
        parent.put(src, null);

        while (!q.isEmpty()) {
            String u = q.remove();
            if (u.equals(dst)) {
                java.util.List<String> rev = new java.util.ArrayList<>();
                String cur = dst;
                while (cur != null) {
                    rev.add(cur);
                    cur = parent.get(cur);
                }
                java.util.Collections.reverse(rev);
                return new Path(rev);
            }
            for (org.jgrapht.graph.DefaultEdge e : graph.outgoingEdgesOf(u)) {
                String v = graph.getEdgeTarget(e);
                if (!parent.containsKey(v)) {
                    parent.put(v, u);
                    q.add(v);
                }
            }
        }
        return null;
    }

    private Path dfsSearch(String src, String dst) {
        if (src == null || dst == null) {
            return null;
        }
        if (!graph.containsVertex(src) || !graph.containsVertex(dst)) {
            return null;
        }

        java.util.Set<String> visited = new java.util.HashSet<>();
        java.util.List<String> path = new java.util.ArrayList<>();

        boolean found = dfsVisit(src, dst, visited, path);
        if (found) {
            return new Path(path);
        }
        return null;
    }

    private boolean dfsVisit(String current,
                             String target,
                             java.util.Set<String> visited,
                             java.util.List<String> path) {
        visited.add(current);
        path.add(current);

        if (current.equals(target)) {
            return true;
        }

        for (org.jgrapht.graph.DefaultEdge e : graph.outgoingEdgesOf(current)) {
            String next = graph.getEdgeTarget(e);
            if (!visited.contains(next)) {
                boolean ok = dfsVisit(next, target, visited, path);
                if (ok) {
                    return true;
                }
            }
        }

        // backtrack
        path.remove(path.size() - 1);
        return false;
    }

}
