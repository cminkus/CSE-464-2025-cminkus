package edu.asu.cse464.graph;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Files;
import java.nio.file.Paths;

class Feature1Test {
    @Test
    void parseGraph_toString_matchesExpected() throws Exception {
        GraphService gs = new GraphService();
        gs.parseGraph("examples/sample.dot"); //input file in repo

        String output = gs.toString();
        String expected = Files.readString(Paths.get("src/test/resources/feature1test.txt"));

        assertEquals(expected.replace("\r\n", "\n").trim(),
                output.replace("\r\n", "\n").trim());
    }
}
