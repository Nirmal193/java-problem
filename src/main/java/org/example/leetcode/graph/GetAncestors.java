package org.example.leetcode.graph;
import java.util.*;

public class GetAncestors {
    Map<Integer,List<Integer>> graph = new HashMap<>();
    public void initializeGraph(int n) {
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
    }
    public void addEdge(int u, int v) {
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
    }
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        initializeGraph(n);
        for (int i = 0; i < edges.length; i++) {
            addEdge(edges[i][1], edges[i][0]);
        }
        List<List<Integer>> ans = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        for(int i=0;i<n;i++){
            List<Integer> temp = new ArrayList<>();
            visited.clear();
            dfs_util(i, visited, temp);
            Collections.sort(temp);
            ans.add(temp);
        }
        return ans;
    }
    public void dfs_util(int node,Set<Integer> visited,List<Integer> curr){
        visited.add(node);
        for(Integer ngh : graph.get(node)){
            if(visited.contains(ngh) == false){
                curr.add(ngh);
                dfs_util(ngh, visited, curr);
            }
        }
    }
}
