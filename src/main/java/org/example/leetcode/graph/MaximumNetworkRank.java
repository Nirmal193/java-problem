package org.example.leetcode.graph;

import java.util.PriorityQueue;

public class MaximumNetworkRank {
    private boolean graph[][];
    public int maximalNetworkRank(int n, int[][] roads) {
        graph = new boolean[n][n];
        int indegree[] = new int[n];
        for(int i=0;i<n;i++){
            indegree[roads[i][0]]++;
            indegree[roads[i][1]]++;
            graph[roads[i][0]][roads[i][1]] = true;
            graph[roads[i][1]][roads[i][0]] = true;
        }
        int ans  = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int rank = indegree[i] + indegree[j];
                if (graph[i][j]) rank--;
                ans = Math.max(ans, rank);
            }
        }

        return ans;
    }
    private static class Pair {
        int node;
        int indegree;
        public Pair(int node, int indegree) {
            this.node = node;
            this.indegree = indegree;
        }
    }
}
