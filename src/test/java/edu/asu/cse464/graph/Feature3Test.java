package edu.asu.cse464.graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Feature3Test {

    @Test
    void addEdge_addsEdge_andPreventsDuplicates() { //test adding edges and preventing dupes
        GraphService gs = new GraphService();
        assertTrue(gs.addEdge("A","B")); //first add is a new edge
        assertFalse(gs.addEdge("A","B")); //the dupe is rejected in this test
    }

    @Test
    void addEdge_autoCreatesMissingVertices() {  //test automatically creating a missing vertex/vertices
        GraphService gs = new GraphService();
        assertTrue(gs.addEdge("A","B"));
        assertTrue(gs.addEdge("B","C"));
    }
}
