package edu.asu.cse464.graph;

import org.jgrapht.graph.DefaultEdge;

import java.util.*;

public class RandomWalkSearch extends SearchTemplate {

    private final Random rand = new Random();

    public RandomWalkSearch(org.jgrapht.Graph<String, DefaultEdge> graph) {
        super(graph);
    }

    @Override
    protected boolean performSearch(String src, String dst, Set<String> visited, Map<String, String> parent) {
        String current = src;

        while (true) {
            //if target found
            if (current.equals(dst)) {
                return true;
            }

            //mark it as visited
            visited.add(current);

            //get neighbors of current node
            List<String> neighbors = getNeighbors(current);

            if (neighbors.isEmpty()) {
                return false;
            }

            //choose a random neighbor
            String next = neighbors.get(rand.nextInt(neighbors.size()));
            if (!parent.containsKey(next)) {
                parent.put(next, current);
            }

            current = next; //set curr to next
        }
    }

    private List<String> getNeighbors(String node) {
        List<String> list = new ArrayList<>();
        for (DefaultEdge e : graph.outgoingEdgesOf(node)) {
            list.add(graph.getEdgeTarget(e)); //add the neighbor
        }
        return list;
    }
}
