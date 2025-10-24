package edu.asu.cse464.graph;

public class Main {
    public static void main(String[] args) {
        GraphService gs = new GraphService();
        gs.parseGraph("examples/sample.dot");
        System.out.println(gs);
    }
}
