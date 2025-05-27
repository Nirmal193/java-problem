package org.example.leetcode.graph;
import java.util.*;
public class MaximumDetonation {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    public void initializeGraph(int n) {
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
    }
    public void addEdge(int u, int v) {
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
    }
    public boolean checkReachability(int b1[], int b2[]){
        long dx = (long)(b2[0] - b1[0]);
        long dy = (long)(b2[1] - b1[1]);
        long distanceSquared = dx * dx + dy * dy;
        long radiusSquared = (long)b1[2] * b1[2];
        return distanceSquared <= radiusSquared;
    }

    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        initializeGraph(n);
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i!=j){
                    if(checkReachability(bombs[i], bombs[j])){
                        addEdge(i, j);
                    }
                }
            }
        }
        Set<Integer> visited = new HashSet<>();
        int ans = 0;
        for(int i=0;i<n;i++){
            visited.clear();
            dfs_util(i, visited);
             ans = Math.max(ans, visited.size());
        }
        return ans;
    }
    public void dfs_util(int node, Set<Integer> visited){
        visited.add(node);
        for(Integer ngh : graph.get(node)){
            if(visited.contains(ngh) == false){
                dfs_util(ngh, visited);
            }
        }
    }

    public static void main(String[] args) {
        MaximumDetonation obj = new MaximumDetonation();
        int[][] bombs = {{1,1,5},{10,10,5}};
        System.out.println(obj.maximumDetonation(bombs)); // Output: 2
    }

}
