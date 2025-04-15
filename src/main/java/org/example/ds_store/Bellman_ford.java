package org.example.ds_store;

import java.util.*;

public class Bellman_ford {
    private final Map<Integer, List<Edge>> map;
    private final int size;
    private final boolean doesContainNegativeCycle = false;
    private final List<Edge> edgeList;
    private static class Edge{
        int source;
        int dest;
        int weight;

        public Edge(int source, int dest, int weight) {
            this.source = source;
            this.dest = dest;
            this.weight = weight;
        }
    }
    private static class NodeUtil{
        int distance;
        int parent;
        public NodeUtil(int distance, int parent) {
            this.distance = distance;
            this.parent = parent;
        }
    }
    public void addEdge(int src, int dest, int weight){
        Edge edge = new Edge(src,dest,weight);
        map.get(src).add(edge);
        edgeList.add(edge);
    }
    public Bellman_ford(int size){
        this.size = size;
        map = new HashMap<>();
        edgeList = new ArrayList<>();
        for(int i=0;i<size;i++){
            map.put(i,new ArrayList<>());
        }
    }
    public int[] getShortest(int source){
        int dist[] = new int[size];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[source] = 0;
        for(int i=0;i<size-1;i++){
            for(Edge edge : edgeList){
                //relaxing all the edges
                if(dist[edge.dest] > dist[edge.source] + edge.weight){
                    dist[edge.dest] = dist[edge.source] + edge.weight;
                }
            }
        }
        //check for negative cycle
        boolean flagForNegativeCycle = false;
        for(Edge edge : edgeList){
            if(dist[edge.dest] > dist[edge.source] + edge.weight){
                flagForNegativeCycle = true;
                break;
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        Bellman_ford bf = new Bellman_ford(5);
        bf.addEdge(0,1 ,4);
        bf.addEdge(0,2 ,5);
        bf.addEdge(0,3,8);
        bf.addEdge(1,2 ,-3);
        bf.addEdge(3,4 ,2);
        bf.addEdge(2,4 ,4);
        bf.addEdge(4,3 ,1);

        int[] dist = bf.getShortest(0);
        for(int i=0;i<dist.length;i++){
            System.out.println("Distance from 0 to " + i + " is " + dist[i]);
        }
        System.out.println("Negative cycle exists: " + bf.doesContainNegativeCycle);
    }
}
