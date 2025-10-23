package edu.asu.cse464.graph;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class Feature4Test {

    @Test
    void outputDOTGraph_createsDotFile() throws Exception {
        Files.createDirectories(Paths.get("target/test-out"));
        GraphService gs = new GraphService();
        gs.addEdge("A", "B");
        gs.addEdge("B", "C");

        String dotPath = "target/test-out/graph.dot";
        gs.outputDOTGraph(dotPath);

        File f = new File(dotPath);
        assertTrue(f.exists(), "DOT file should be created");

        //check the contents and make sure everything is contained
        String content = Files.readString(Paths.get(dotPath));
        assertTrue(content.contains("digraph"), "DOT should start with digraph");
        assertTrue(content.contains("A"), "DOT should include vertex A");
        assertTrue(content.contains("B"), "DOT should include vertex B");
    }

    @Test
    void outputGraphics_createsPng() throws Exception {
        Files.createDirectories(Paths.get("target/test-out"));
        GraphService gs = new GraphService();
        gs.addEdge("A", "B");
        gs.addEdge("B", "C");

        String pngPath = "target/test-out/graph.png";   //test path for png
        gs.outputGraphics(pngPath, "png");

        File img = new File(pngPath);
        assertTrue(img.exists(), "PNG file should be created");
        assertTrue(img.length() > 0, "PNG file should not be empty");
    }
}
