package edu.asu.cse464.graph;

import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

public class RemoveTest {

    //some nodes and some edges correctly removed
    @Test
    void scenario1_someNodesAndEdgesAreRemoved() {
        GraphService g = new GraphService();
        g.addNodes(new String[]{"A", "B", "C", "D"});
        g.addEdge("A","B");
        g.addEdge("A","C");
        g.addEdge("C","D");

        //remove one edge
        g.removeEdge("A","B");
        g.removeNode("C");

        //removing C again throws an err
        assertThrows(NoSuchElementException.class, () -> g.removeNode("C"));

        //A->C should no longer exist
        assertThrows(NoSuchElementException.class, () -> g.removeEdge("A","C"));
    }

    //removing nodes that do not exist
    @Test
    void scenario2_removingNonexistentNodesThrows() {
        GraphService g = new GraphService();
        g.addNodes(new String[]{"A", "B"});

        assertThrows(NoSuchElementException.class, () -> g.removeNode("Z"));
        assertThrows(NoSuchElementException.class, () -> g.removeNodes(new String[]{"A", "Z"}));
    }

    //removing edges that do not exist
    @Test
    void scenario3_removingNonexistentEdgesThrows() {
        GraphService g = new GraphService();
        g.addNodes(new String[]{"A", "B", "C"});
        g.addEdge("A","B");

        //never existed
        assertThrows(NoSuchElementException.class, () -> g.removeEdge("B","C"));
        g.removeNode("B");
        assertThrows(NoSuchElementException.class, () -> g.removeEdge("A","B"));
    }
}
