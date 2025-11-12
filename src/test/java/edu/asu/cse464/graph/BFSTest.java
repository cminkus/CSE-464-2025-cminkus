package edu.asu.cse464.graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BFSTest {

    @Test
    void bfs_finds_path_when_reachable() {
        GraphService g = new GraphService();

        g.addNodes(new String[]{"A","B","C","D"});
        g.addEdge("A","B");
        g.addEdge("B","C");
        g.addEdge("A","D");
        g.addEdge("D","C");

        Path p = g.GraphSearch("A", "C");  // BFS version on this branch

        assertNotNull(p);
        assertEquals("A", p.nodes().get(0));
        assertEquals("C", p.nodes().get(p.nodes().size() - 1));
        assertEquals("A -> B -> C", p.toString());
    }

    @Test
    void bfs_returns_null_when_unreachable() {
        GraphService g = new GraphService();
        g.addNodes(new String[]{"A","B","C"});
        g.addEdge("A","B");
        Path p = g.GraphSearch("B", "A");
        assertNull(p);
    }
}
