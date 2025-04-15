package org.example.ds_store;

import java.util.*;

public class KruskalAlgo {
    Map<Integer, List<Edge<Integer,Integer>>> graph;
    List<Edge<Integer,Integer>> edgeList;
    public KruskalAlgo(int size){
        graph = new HashMap<>();
        edgeList = new ArrayList<>();
        for(int i=0;i<size;i++){
            graph.put(i,new ArrayList<>());
        }
    }
    public void addEdge(int src, int dest,int weight){
        graph.get(src).add(new Edge<>(src,dest,weight));
        graph.get(dest).add(new Edge<>(dest,src,weight));
        edgeList.add(new Edge<>(src,dest,weight));
    }
    public List<Edge<Integer,Integer>> generateMSP(){
        List<Edge<Integer,Integer>> MSP = new ArrayList<>();
        UnionFind uf = new UnionFind(graph.size());
        Collections.sort(edgeList, new Comparator<Edge<Integer, Integer>>() {
            @Override
            public int compare(Edge<Integer, Integer> e1, Edge<Integer, Integer> e2) {
                return Integer.compare(e1.weight,e2.weight);
            }
        });
        for(Edge<Integer,Integer> edge : edgeList){
            if(uf.find(edge.source) != uf.find(edge.dest)){
                MSP.add(edge);
                uf.union(edge.source,edge.dest);
            }
        }
        return MSP;
    }

    public static void main(String[] args) {
        KruskalAlgo kalgo = new KruskalAlgo(6);
        kalgo.addEdge(0,1,3);
        kalgo.addEdge(0,3,1);
        kalgo.addEdge(1,2,1);
        kalgo.addEdge(1,3,3);
        kalgo.addEdge(1,0,3);
        kalgo.addEdge(2,3,1);
        kalgo.addEdge(2,1,1);
        kalgo.addEdge(2,4,5);
        kalgo.addEdge(2,5,4);
        kalgo.addEdge(3,4,6);
        kalgo.addEdge(3,2,1);
        kalgo.addEdge(3,1,3);
        kalgo.addEdge(3,0,1);
        kalgo.addEdge(4,3,6);
        kalgo.addEdge(4,2,5);
        kalgo.addEdge(4,5,2);
        kalgo.addEdge(5,4,2);
        kalgo.addEdge(5,2,4);
        List<Edge<Integer, Integer>> edges = kalgo.generateMSP();
        edges.stream().forEach(x-> System.out.println(x.source +"->"+x.dest + ":" + x.weight));
    }
}
