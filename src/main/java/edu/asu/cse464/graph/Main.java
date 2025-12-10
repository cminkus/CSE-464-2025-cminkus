package edu.asu.cse464.graph;

public class Main {
    //this is what i ran for the demo
    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("Usage: java Main <dot-file>");
            return;
        }

        String filepath = args[0];

        GraphService gs = new GraphService();
        System.out.println("Loading graph: " + filepath);
        gs.parseGraph(filepath);

        // ===== BFS a -> h =====
        System.out.println("\n=== BFS a -> h ===");
        Path bfs = gs.GraphSearch("a", "h", Algorithm.BFS);
        System.out.println(bfs);

        // ===== DFS a -> h =====
        System.out.println("\n=== DFS a -> h ===");
        Path dfs = gs.GraphSearch("a", "h", Algorithm.DFS);
        System.out.println(dfs);

        // ===== RANDOM WALKS (5 runs) =====
        System.out.println("\n=== RANDOM WALKS (5 runs) a -> h ===");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Run #" + i);
            Path p = gs.GraphSearch("a", "h", Algorithm.RANDOM);
            System.out.println(p);
        }
    }
}
