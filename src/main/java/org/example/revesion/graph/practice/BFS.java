package org.example.revesion.graph.practice;
import java.util.*;
public class BFS {
    private Map<Integer,List<Integer>> graph;
    public BFS(){
        graph = new HashMap<>();
    }
    public void addEdge(int u, int v){
        graph.computeIfAbsent(u , k-> new ArrayList<>()).add(v);
        graph.computeIfAbsent(v , k-> new ArrayList<>()).add(u);
    }
    public void bfs_util(int node){
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue =  new LinkedList<>();
        queue.add(node);
        visited.add(node);
        while(!queue.isEmpty()){
            int current = queue.poll();
            for(Integer ngh: graph.get(current)){
                if(visited.contains(ngh) == false){
                    visited.add(ngh);
                    System.out.println(ngh);
                    queue.add(ngh);
                }
            }
        }
    }

    public static void main(String[] args) {
        BFS graph = new BFS();
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.bfs_util(0);
    }
}
