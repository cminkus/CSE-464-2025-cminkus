package edu.asu.cse464.graph;

import org.jgrapht.graph.DefaultEdge;

import java.util.*;

public class RandomWalkSearch extends SearchTemplate {

    private final Random rand = new Random();

    public RandomWalkSearch(org.jgrapht.Graph<String, DefaultEdge> graph) {
        super(graph);
    }

    @Override
    protected boolean performSearch(String src, String dst,
                                    Set<String> visited,
                                    Map<String, String> parent) {

        String current = src;
        visited.add(current);
        parent.put(src, null); //make sure that the root exists in parent map

        java.util.List<String> pathSoFar = new java.util.ArrayList<>();
        pathSoFar.add(current);

        while (true) {
            System.out.println("visiting " + new Path(pathSoFar));

            if (current.equals(dst)) {
                return true;
            }

            java.util.List<String> neighbors = getNeighbors(current);
            if (neighbors.isEmpty()) {
                //dead end so ret false
                return false;
            }

            String next = neighbors.get(rand.nextInt(neighbors.size()));

            //if this is a new node in the tree, record its parent and extend path
            if (!visited.contains(next)) {
                visited.add(next);
                parent.put(next, current);
                pathSoFar.add(next);
            } else {
                pathSoFar.add(next);
            }
            //move on lol
            current = next;
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
