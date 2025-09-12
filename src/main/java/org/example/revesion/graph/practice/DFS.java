package org.example.revesion.graph.practice;
import java.util.*;
public class DFS {
    private Map<Integer,List<Integer>> graph;
    public DFS(){
        this.graph = new HashMap<>();
    }
    public void addEdge(int u, int v){
        graph.computeIfAbsent(u,k->new ArrayList<>()).add(v);
        graph.computeIfAbsent(v,k->new ArrayList<>()).add(u);
    }
    public void dfs_util(int node,Set<Integer> visited){
        visited.add(node);
        System.out.println(node);
        List<Integer> neigh = graph.get(node);
        for(Integer ngh: neigh){
            if(!visited.contains(ngh))
                dfs_util(ngh,visited);
        }
    }

    public static void main(String[] args) {
        DFS dfs = new DFS();
        dfs.addEdge(0, 1);
        dfs.addEdge(0, 4);
        dfs.addEdge(1, 2);
        dfs.addEdge(1, 3);
        dfs.addEdge(1, 4);
        dfs.addEdge(2, 3);
        dfs.addEdge(3, 4);
        Set<Integer> visited = new HashSet<>();
        dfs.dfs_util(0,visited);
    }
}
