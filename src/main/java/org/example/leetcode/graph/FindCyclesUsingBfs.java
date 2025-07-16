package org.example.leetcode.graph;

import java.util.*;
public class FindCyclesUsingBfs {
    private final Map<Integer,List<Integer>> graph = new HashMap<>();
    public void addEdge(int u, int v){
        graph.computeIfAbsent(u,k-> new ArrayList<>()).add(v);
        graph.computeIfAbsent(v,k-> new ArrayList<>()).add(u);
    }
    public boolean findCycles(int node, boolean visited[]){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{node,-1});
        visited[node] = true;
        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int parent = curr[1];
            int currNode = curr[0];
            for(Integer ngh : graph.get(currNode)){
                if(ngh == parent) continue;
                if(visited[ngh]) return true;
                visited[ngh] = true;
                queue.offer(new int[]{ngh,currNode});
            }
        }
        return  false;
    }
}
