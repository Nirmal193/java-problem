package org.example.revesion.graph;

import java.util.*;

public class MinOrder {
    private Map<Integer,List<Integer>> graph = new HashMap<>();
    private Map<Integer,Set<Integer>> graphSet = new HashMap<>();
    public void intializeGraph(int n){
        for(int i=0;i<n;i++) {
            graph.put(i, new ArrayList<>());
            graphSet.put(i,new HashSet<>());
        }
    }
    public void addEdge(int u, int v){
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        graphSet.computeIfAbsent(u, k -> new HashSet<>()).add(v);
    }
    public int minReorder(int n, int[][] connections) {
        intializeGraph(n);
        for(int i=0;i<connections.length;i++){
            addEdge(connections[i][0],connections[i][1]);
        }
        int ans[] = new int[1];
        boolean visited[] = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;
        while(!queue.isEmpty()){
            int node = queue.poll();
            for(Integer ngh : graph.get(node)){
                if(visited[ngh] == false){
                    if(graphSet.get(node).contains(ngh))
                        ans[0]++;
                    visited[ngh] = true;
                    queue.add(ngh);
                }
            }
        }
        return ans[0];
    }

    public static void main(String[] args) {
        MinOrder obj = new MinOrder();
        int[][] connections ={{1,0},{1,2},{3,2},{3,4}};
        int[][] connections1 = {{1, 0}, {2, 0}};
        int n = 3;
        System.out.println(obj.minReorder(n,connections1));
    }
}
