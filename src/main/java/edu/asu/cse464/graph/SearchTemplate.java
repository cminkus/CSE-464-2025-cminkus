package edu.asu.cse464.graph;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;

public abstract class SearchTemplate{

    protected final Graph<String, DefaultEdge> graph;

    protected SearchTemplate(Graph<String, DefaultEdge> graph) {
        this.graph = graph;
    }


    public final Path search(String src, String dst) {

        if (src == null || dst == null) return null;
        if (!graph.containsVertex(src) || !graph.containsVertex(dst)) return null;

        //common data structures used by both BFS and DFS
        Set<String> visited = new HashSet<>();
        Map<String, String> parent = new HashMap<>();

        parent.put(src, null);

        //BFS vs DFS logic
        boolean found = performSearch(src, dst, visited, parent);

        if (!found) return null;

        return buildPath(parent, dst);
    }

    protected abstract boolean performSearch(String src, String dst, Set<String> visited, Map<String, String> parent);

    protected Path buildPath(Map<String, String> parent, String dst) {
        List<String> p = new ArrayList<>();
        String cur = dst;
        while (cur != null) {
            p.add(cur);
            cur = parent.get(cur);
        }
        Collections.reverse(p);
        return new Path(p);
    }
}
