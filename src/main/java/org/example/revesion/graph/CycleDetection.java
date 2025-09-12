package org.example.revesion.graph;

import java.util.*;
import java.util.stream.Collectors;

public class CycleDetection {
    private final Map<Integer,List<Edge>> graph;
    private final int size;
    private final Map<Integer,Integer> parent;
    private static class Edge{
        int dest;
        int weight;
        public Edge(int dest,int weight){
            this.dest = dest;
            this.weight = weight;
        }
    }
    public CycleDetection(int size){
        this.size = size;
        graph = new java.util.HashMap<>();
        parent = new HashMap<>();
        for(int i=0;i<size;i++){
            graph.put(i,new java.util.ArrayList<>());
        }
    }
    public void addEdge(int src, int dest, int weight){
        Edge edge = new Edge(dest,weight);
        graph.get(src).add(edge);
    }
    public boolean hasCycle(){
        Set<Integer> whiteSet = new java.util.HashSet<>();
        Set<Integer> greySet = new java.util.HashSet<>();
        Set<Integer> blackSet = new java.util.HashSet<>();
        for(int i=0;i<size;i++){
            whiteSet.add(i);
        }
        while(!whiteSet.isEmpty()){
            int current = whiteSet.iterator().next();
            if(dfs(current,-1,whiteSet,greySet,blackSet)){
                return true;
            }
        }
        return false;
    }
    private void printParent(int source, int destination){
        if(source == destination){
            System.out.println(source);
            return;
        }
        LinkedList<Integer> list = new LinkedList<>();
        list.add(destination);
        while(parent.get(source) != destination){
            list.addFirst(source);
            source = parent.get(source);
        }
        list.addFirst(source);
        list.addFirst(destination);
        System.out.println(list.stream().map(String::valueOf).collect(Collectors.joining("->")));
    }
    public boolean dfs(Integer node,Integer p,Set<Integer> white,Set<Integer> gray,Set<Integer> black){
        moveToSet(node,white,gray);
        parent.put(node,p);
        for(Edge edge:graph.get(node)){
            if(black.contains(edge.dest))
                continue;
            if(gray.contains(edge.dest)) {
                printParent(node,edge.dest);
                return true;
            }
            if(dfs(edge.dest,node,white,gray,black)){
                return true;
            }
        }
        moveToSet(node,gray,black);
        return false;
    }
    private void moveToSet(Integer node,Set<Integer> source,Set<Integer> dest){
        source.remove(node);
        dest.add(node);
    }

    public static void main(String[] args) {
        CycleDetection cycleDetection = new CycleDetection(6);
        cycleDetection.addEdge(0,1,0);
        cycleDetection.addEdge(1,2,0);
        cycleDetection.addEdge(0,2,0);
        cycleDetection.addEdge(3,0,0);
        cycleDetection.addEdge(3,4,0);
        cycleDetection.addEdge(4,5,0);
        cycleDetection.addEdge(5,3,0);
        System.out.println("Cycle detected: " + cycleDetection.hasCycle());
    }

}
