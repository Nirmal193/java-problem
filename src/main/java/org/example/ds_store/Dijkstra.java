package org.example.ds_store;

import java.util.*;

public class Dijkstra {
    private final Map<Integer, List<Node>> graph;
    private final int size;
    private static class Node{
        int vertex;
        int key;
        public Node(int vertex, int key){
            this.vertex = vertex;
            this.key = key;
        }
    }
    public Dijkstra(int size){
        this.size = size;
        graph = new HashMap<>();
        for(int i=0;i<size;i++){
            graph.put(i,new ArrayList<>());
        }
    }
    public int[] getShortestPath(Integer source){
        boolean visited[] = new boolean[size];
        int dist[] = new int[size];
        Arrays.fill(dist,Integer.MAX_VALUE);
        PriorityQueue<Node> ps = new PriorityQueue<>((a,b)-> Integer.compare(a.key,b.key));
        ps.add(new Node(source,0));
        dist[source] = 0;
        while(!ps.isEmpty()){
            Node node = ps.poll();
            int vertex = node.vertex;
            if(visited[vertex])
                continue;
            visited[vertex] = true;
            for(Node neighbour : graph.get(vertex)){
                if(visited[neighbour.vertex]== false && dist[neighbour.vertex] + neighbour.key > neighbour.key){
                    dist[neighbour.vertex] = dist[vertex] + neighbour.key;
                    ps.add(new Node(neighbour.vertex,dist[neighbour.vertex]));
                }
            }
        }
        return dist;
    }
}
