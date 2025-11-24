package edu.asu.cse464.graph;

import org.jgrapht.graph.DefaultEdge;

import java.util.*;

public class BFSSearch extends SearchTemplate {

    public BFSSearch(org.jgrapht.Graph<String, DefaultEdge> graph) {
        super(graph);
    }

    @Override
    protected boolean performSearch(String src, String dst, Set<String> visited, Map<String, String> parent) {
        Queue<String> queue = new ArrayDeque<>();
        queue.add(src);

        while (!queue.isEmpty()) {
            String current = queue.remove();
            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);

            if (current.equals(dst)) {
                return true;    //parent map already built, template will call buildPath()
            }

            for (DefaultEdge e : graph.outgoingEdgesOf(current)) {
                String neighbor = graph.getEdgeTarget(e);
                if (!visited.contains(neighbor) && !parent.containsKey(neighbor)) {
                    parent.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        return false; //no path
    }
}
