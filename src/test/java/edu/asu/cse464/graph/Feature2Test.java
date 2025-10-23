package edu.asu.cse464.graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Feature2Test {

    @Test
    void testAddSingleNode() {
        GraphService gs = new GraphService();
        assertTrue(gs.addNode("A"));
        assertFalse(gs.addNode("A")); //test duplicate
    }

    @Test
    void testAddMultipleNodes() {
        GraphService gs = new GraphService();
        String[] nodes = {"A", "B", "C", "A"}; //test one duplicate
        int added = gs.addNodes(nodes);
        assertEquals(3, added); //this should add only unique nodes
    }
}
