package org.example.revesion.graph.practice;
import java.util.*;
public class Dijkstra {
    private Map<Integer,List<Edge>> graph;
    private int size;
    public Dijkstra(int size){
        this.size = size;
        graph = new HashMap<>();
    }
    public void addEdge(Integer u, Integer v,Integer weight){
        graph.computeIfAbsent(u,k->new ArrayList<>()).add(new Edge(v,weight));
        graph.computeIfAbsent(v,k-> new ArrayList<>()).add(new Edge(u, weight));
    }
    public int[] dijkstra_util(int source){
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Edge> queue = new PriorityQueue<>((a,b)->Integer.compare(a.weight,b.weight));
        int dist[] = new int[size];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[source] = 0;
        queue.add(new Edge(source,0));
        visited.add(source);
        while(!queue.isEmpty()){
            Edge current = queue.poll();
            if(visited.contains(current.node))
                continue;
            visited.add(current.node);
            for(Edge edge : graph.get(current.node)){
                int newDist = dist[current.node] + edge.weight;
                if(visited.contains(edge.node) == false && dist[edge.node] > newDist){
                    queue.add(edge);
                }
            }
        }
        return dist;
    }
    public static class Edge{
        Integer node;
        Integer weight;
        public Edge(Integer val,Integer weight){
            this.node = val;
            this.weight = weight;
        }
    }
}
