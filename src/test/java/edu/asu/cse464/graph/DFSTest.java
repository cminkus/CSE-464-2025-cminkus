package edu.asu.cse464.graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DFSTest {
    //helpers to help with graph creation for DFS (refactor 5):
    private GraphService simpleABC() {
        GraphService g = new GraphService();
        g.addNodes(new String[]{"A", "B", "C"});
        g.addEdge("A", "B");
        return g;
    }

    private GraphService diamondGraph() {
        GraphService g = new GraphService();
        g.addNodes(new String[]{"A","B","C","D"});
        g.addEdge("A","B");
        g.addEdge("B","C");
        g.addEdge("A","D");
        return g;
    }

    @Test
    void reachable() {
        GraphService g = diamondGraph();

        Path p = g.GraphSearch("A", "C", Algorithm.DFS);
        assertNotNull(p);
        assertEquals("A", p.nodes().get(0));
        assertEquals("C", p.nodes().get(p.nodes().size() - 1));
    }

    @Test
    void unreachable() {
        GraphService g = simpleABC();

        Path p = g.GraphSearch("B", "A", Algorithm.DFS);
        assertNull(p);
    }
}
