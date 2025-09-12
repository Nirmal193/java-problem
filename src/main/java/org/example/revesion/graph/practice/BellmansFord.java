package org.example.revesion.graph.practice;
import java.util.*;
import java.util.stream.Collectors;

public class BellmansFord {
    public static class Edge{
        int u,v,w;
        public Edge(int u,int v,int w){
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
    private Map<Integer,List<Edge>> graph;
    private int size;
    public BellmansFord(int size){
        this.size = size;
        graph = new HashMap<>();
        for(int i=0;i<size;i++){
            graph.put(i,new ArrayList<>());
        }
    }
    public void addEdge(int u, int v, int w){
        graph.get(u).add(new Edge(u,v,w));
        graph.get(v).add(new Edge(v,u,w));
    }
    public List<Integer> shortestPath(int source, int dest){
        int dist[] = new int[size];
        int parent[] = new int[size];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[source] = 0;
        List<Edge> edges = graph.values().stream().flatMap(x -> x.stream()).collect(Collectors.toList());
        for(int i=0;i<size-1;i++) {
            for (Edge edge : edges) {
                if(dist[edge.u] != Integer.MAX_VALUE  && dist[edge.v] > dist[edge.u] + edge.w){
                    dist[edge.v] = dist[edge.u] + edge.w;
                    parent[edge.v] = edge.u;
                }
            }
        }
        for(Edge edge : edges){
            if(dist[edge.v] != Integer.MAX_VALUE && dist[edge.v] > dist[edge.u] + edge.w){
                //graph contains negative cycle
                return null;
            }
        }
        if (dist[dest] == Integer.MAX_VALUE) {
            return Collections.emptyList();
        }
        List<Integer> ans = new ArrayList<>();
        int curr  = dest;
        while(curr != source){
            ans.add(curr);
            curr = parent[dest];
        }
        ans.add(source);
        return ans.reversed();
    }

}
