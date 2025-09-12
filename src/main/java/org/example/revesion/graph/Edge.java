package org.example.revesion.graph;

public class Edge <T,U>{
    T source;
    T dest;
    U weight;

    public Edge(T source, T dest, U weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "source=" + source +
                ", dest=" + dest +
                ", weight=" + weight +
                '}';
    }
}
