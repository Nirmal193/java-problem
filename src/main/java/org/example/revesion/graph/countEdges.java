package org.example.revesion.graph;
import java.util.*;
public class countEdges {
    Map<Integer,List<Integer>> graph = new HashMap<>();
    public void initializeGraph(int n) {
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
    }
    public void addEdge(int u, int v) {
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
    }
    public long countPairs(int n, int[][] edges) {
        initializeGraph(n);
        for(int i=0;i<edges.length;i++){
            addEdge(edges[i][0], edges[i][1]);
        }
        boolean visited[] = new boolean[n];
        long ans = 0;
        for(int i=0;i<n;i++){
            if(visited[i] == false){
                long count = dfs_util(i, visited);
                ans += (count * (n - count));
            }
        }
        return ans;
    }
    public long dfs_util(int node, boolean visited[]){
        visited[node] = true;
        long count = 1;
        for(Integer ngh : graph.get(node)){
            if(visited[ngh] == false){
                count += dfs_util(ngh, visited);
            }
        }
        return count;
    }
}
