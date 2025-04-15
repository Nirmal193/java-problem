package org.example.ds_store;

import java.util.*;
import java.util.stream.Collectors;

public class KosarajuAlgo {
    private final Map<Integer,List<Edge>> graph;
    private final int size;
    private static class Edge{
        int dest;
        int weight;
        public Edge(int dest) {
            this.dest = dest;
        }
    }
    public KosarajuAlgo(int size){
        this.size = size;
        graph = new HashMap<>();
        for(int i=0;i<size;i++){
            graph.put(i,new ArrayList<>());
        }
    }
    public void addEdge(int src, int dest){
        Edge edge = new Edge(dest);
        graph.get(src).add(edge);
    }
    public Map<Integer,List<Edge>> reverseGraph(){
        Map<Integer,List<Edge>> reversedGraph = new HashMap<>();
        for(int i=0;i<size;i++){
            reversedGraph.put(i,new ArrayList<>());
        }
        for(int i=0;i<size;i++){
            for(Edge edge : graph.get(i)){
                reversedGraph.get(edge.dest).add(new Edge(i));
            }
        }
        return reversedGraph;
    }
    public List<List<Integer>> stringlyConnectedComponent(){
       Stack<Integer> stack = new Stack<>();
       Set<Integer> visited = new HashSet<>();
       List<List<Integer>> ans = new ArrayList<>();
       for(int i=0;i<size;i++){
           if(visited.contains(i) == false)
                dfs_util(i,visited,stack);
       }
        Map<Integer, List<Edge>> reversedGraph = reverseGraph();
        visited.clear();
        while(stack.isEmpty() == false){
            Integer node  = stack.pop();
            if(visited.contains(node))
                continue;
            List<Integer> components = new ArrayList<>();
            dfs_utilForConnectedComponent(node,reversedGraph,visited,components);
            ans.add(components);
        }
         return ans;
    }
    public void dfs_utilForConnectedComponent(Integer node, Map<Integer,List<Edge>> reversedGraph,Set<Integer> visited,List<Integer> component){
        visited.add(node);
        component.add(node);
        for(Edge edge : reversedGraph.get(node)){
            if(visited.contains(edge.dest) == false){
                dfs_utilForConnectedComponent(edge.dest,reversedGraph,visited,component);
            }
        }
    }
    public void dfs_util(Integer node,Set<Integer> visited,Stack<Integer> stack){
        visited.add(node);
        for(Edge edge: graph.get(node)){
            if(visited.contains(edge.dest) == false)
                dfs_util(edge.dest,visited,stack);
        }
        stack.push(node);
    }

    public static void main(String[] args) {
        KosarajuAlgo kslgo = new KosarajuAlgo(11);
        kslgo.addEdge(0,1);
        kslgo.addEdge(1,2);
        kslgo.addEdge(2,0);
        kslgo.addEdge(1,3);
        kslgo.addEdge(3,4);
        kslgo.addEdge(4,5);
        kslgo.addEdge(5,3);
        kslgo.addEdge(6,7);
        kslgo.addEdge(7,8);
        kslgo.addEdge(8,9);
        kslgo.addEdge(9,6);
        kslgo.addEdge(6,5);
        kslgo.addEdge(9,10);
        List<List<Integer>> ans = kslgo.stringlyConnectedComponent();
        for(List<Integer> list : ans){
            System.out.println(list.stream().map(String::valueOf).collect(Collectors.joining("->")));
        }
    }

}
