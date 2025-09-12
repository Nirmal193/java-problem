package org.example.revesion.graph;

import java.util.*;

public class PrimsAlgo {
    Map<Integer, List<Node>> graph;
    int size;
    private static class Node{
        Integer vertex;
        int weight;
        public Node(Integer vertex,int weight){
            this.vertex = vertex;
            this.weight = weight;
        }
    }
    private static class Edge{
        Integer source;
        Integer dest;
        int weight;
        public Edge(Integer source,Integer dest,int weight){
            this.source = source;
            this.dest = dest;
            this.weight = weight;
        }
    }
    public PrimsAlgo(int size){
        graph = new HashMap<>();
        this.size = size;
        for(int i=0;i<size;i++){
            graph.put(i,new ArrayList<>());
        }
    }
    public void addEdge(Integer src, Integer dest,Integer weight){
        graph.get(src).add(new Node(dest,weight));
        graph.get(dest).add(new Node(src,weight));
    }

    // Prim's algorithm to find the minimum spanning tree
    public List<Edge> generateMsp(){
        Map<Integer,Edge> nodeEdgeMap = new HashMap<>();
        //Temporary data structure to store the edges might not be needed cause i already have a binary heap for this
        Set<Integer> visited = new HashSet<>();
        Binary_heap<Integer> heap = new Binary_heap<>();
        for(int i=0;i<size;i++){
            heap.insert(i,Integer.MAX_VALUE);
        }
        heap.decreaseKey(0,0);
        while(!heap.isEmpty()){
            Integer minVertex = heap.extractMin();
            //i think i do not need this operation
            visited.add(minVertex);
            for(Node neighbour : graph.get(minVertex)){
                if(heap.contains(neighbour.vertex) && heap.getKey(neighbour.vertex) > neighbour.weight){
                    heap.decreaseKey(neighbour.vertex, neighbour.weight);
                    nodeEdgeMap.put(neighbour.vertex,new Edge(minVertex, neighbour.vertex, neighbour.weight));
                }
            }
        }
        return nodeEdgeMap.values().stream().toList();
    }


    public static void main(String[] args) {
        PrimsAlgo primsAlgo = new PrimsAlgo(6);
        primsAlgo.addEdge(0, 1, 3);
        primsAlgo.addEdge(0, 3, 1);
        primsAlgo.addEdge(1, 2, 1);
        primsAlgo.addEdge(1, 3, 3);
        primsAlgo.addEdge(2, 4, 5);
        primsAlgo.addEdge(2, 5, 4);
        primsAlgo.addEdge(3, 4, 6);
        primsAlgo.addEdge(4, 5, 2);
        primsAlgo.addEdge(2,3,1);

        List<Edge> mst = primsAlgo.generateMsp();
        mst.forEach(edge -> System.out.println(edge.source + "->" + edge.dest + ":" + edge.weight));
        System.out.println("end");
    }
}
