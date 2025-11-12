package edu.asu.cse464.graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DFSTest {
    @Test
    void reachable() {
        GraphService g = new GraphService();

        g.addNodes(new String[]{"A","B","C","D"});
        g.addEdge("A","B");
        g.addEdge("B","C");
        g.addEdge("A","D");

        Path p = g.GraphSearch("A", "C", Algorithm.DFS);
        assertNotNull(p);
        assertEquals("A", p.nodes().get(0));
        assertEquals("C", p.nodes().get(p.nodes().size() - 1));
    }

    @Test
    void unreachable() {
        GraphService g = new GraphService();
        g.addNodes(new String[]{"A","B","C"});
        g.addEdge("A","B");

        Path p = g.GraphSearch("B", "A", Algorithm.DFS);
        assertNull(p);
    }
}
