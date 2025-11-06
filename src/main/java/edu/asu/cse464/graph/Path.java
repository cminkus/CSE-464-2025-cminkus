package edu.asu.cse464.graph;

import java.util.List;

public class Path {
    private final List<String> nodes;

    public Path(List<String> nodes) {
        if (nodes == null || nodes.isEmpty()) {
            throw new IllegalArgumentException("Path cannot be empty");
        }
        this.nodes = List.copyOf(nodes);
    }

    public List<String> nodes() {
        return nodes;
    }

    @Override
    public String toString() {
        return String.join(" -> ", nodes);
    }
}
