package edu.asu.cse464.graph;

import org.jgrapht.graph.DefaultEdge;

import java.util.*;

/**
 * DFS implementation using the SearchTemplate.
 */
public class DFSSearch extends SearchTemplate {

    public DFSSearch(org.jgrapht.Graph<String, DefaultEdge> graph) {
        super(graph);
    }

    @Override
    protected boolean performSearch(String src, String dst, Set<String> visited, Map<String, String> parent) {
        return dfs(src, dst, visited, parent);
    }

    private boolean dfs(String current, String target, Set<String> visited, Map<String, String> parent) {
        visited.add(current);

        if (current.equals(target)) {
            return true;
        }

        for (DefaultEdge e : graph.outgoingEdgesOf(current)) {
            String next = graph.getEdgeTarget(e);

            if (!visited.contains(next)) {
                parent.put(next, current);

                if (dfs(next, target, visited, parent)) {
                    return true;
                }
            }
        }

        return false;
    }

}
