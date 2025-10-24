package edu.asu.cse464.graph;

public class Main {
    public static void main(String[] args) {
        // ===== Feature 1: parse DOT =====
        System.out.println("=== Feature 1 ===");
        GraphService f1 = new GraphService();
        f1.parseGraph("examples/sample.dot");
        System.out.println(f1);

        // ===== Feature 2: add nodes =====
        System.out.println("=== Feature 2 ===");
        GraphService f2 = new GraphService();
        System.out.println(f2.addNode("A"));
        System.out.println(f2.addNode("A"));
        System.out.println(f2.addNodes(new String[]{"B", "C", "D"}));
        System.out.println(f2);

        // ===== Feature 3: add edges =====
        System.out.println("=== Feature 3 ===");
        GraphService f3 = new GraphService(); // fresh
        f3.addNodes(new String[]{"A", "B", "C"});
        System.out.println(f3.addEdge("A", "B"));
        System.out.println(f3.addEdge("A", "B"));
        System.out.println(f3.addEdge("B", "C"));
        System.out.println(f3);

        // ===== Feature 4: output DOT + PNG =====
        System.out.println("=== Feature 4 ===");
        GraphService f4 = new GraphService();
        f4.addNodes(new String[]{"A", "B", "C"});
        f4.addEdge("A", "B");
        f4.addEdge("B", "C");
        f4.outputDOTGraph("output.dot");
        f4.outputGraphics("output.png", "png");
        System.out.println("Wrote output.dot and output.png");
    }
}
