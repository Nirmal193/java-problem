package org.example.leetcode.graph;

import java.util.LinkedList;
import java.util.*;

public class FindShortestCycle {
    Map<Integer,List<Integer>> graph = new HashMap<>();
    public int findMinCycle(int node, int n){
        boolean visited[] = new boolean[n];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{node,-1});
        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int currNode = curr[0];
            int parent = curr[1];
            for(Integer ngh : graph.get(currNode)){
                if(parent == ngh) continue;
                if(visited[ngh]) return 2;
                visited[ngh] = true;
                queue.offer(new int[]{ngh,currNode});
            }
        }
        return -1;
    }
    public int findCycle(int n) {
        int minCycle = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int distance[] = new int[n];
            Arrays.fill(distance, Integer.MAX_VALUE);
            Queue<Integer> queue = new LinkedList<>();
            distance[i] = 0;
            queue.offer(i);
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                for (int neighbour : graph.get(curr)) {
                    if (distance[neighbour] == Integer.MAX_VALUE) {
                        distance[neighbour] = distance[curr] + 1;
                        queue.offer(neighbour);
                    } else if (distance[neighbour] >= distance[curr]) {
                        minCycle = Math.min(minCycle, distance[curr] + 1 + distance[neighbour]);
                    }

                }
            }
        }
        return minCycle == Integer.MAX_VALUE ? -1 : minCycle;
    }
}
