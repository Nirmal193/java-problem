package org.example.revesion.graph;

import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class networkBecomesIdle {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    public void initializeGraph(int n) {
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
    }
    public void addEdge(int u, int v) {
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
    }
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        int n= patience.length;
        initializeGraph(n);
        for (int i = 0; i < edges.length; i++) {
            addEdge(edges[i][0], edges[i][1]);
        }
        boolean visited[] = new boolean[n];
        Node dist[] = new Node[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;
        dist[0] = new Node(0, 0);
        int level = 0;
        while(queue.isEmpty() == false){
            int sz = queue.size();
            for(int i=0;i<sz;i++){
                int curr = queue.poll();
                for(Integer ngh : graph.get(curr)){
                    if(visited[ngh] == false){
                        queue.add(ngh);
                        visited[ngh] = true;
                        dist[ngh] = new Node(ngh, level+1);
                    }
                }
            }
            level++;
        }
        int ans = 0;
        for(int i=1;i<n;i++){
            int time = dist[i].level * 2;
            if(time <= patience[i]){
                ans = Math.max(ans, time);
                continue;
            }
            int replay = 0;
            if(time % patience[i] == 0){
                replay = time/patience[i] - 1;
            }else
                replay = time/patience[i];
            time = time + replay * patience[i];
            ans = Math.max(ans, time);
        }
        return ans+1;
    }
    private static class Node{
        int node;
        int level;
        public Node(int node, int level){
            this.node = node;
            this.level = level;
        }
    }

    public static void main(String[] args) {
        networkBecomesIdle obj = new networkBecomesIdle();
        int[][] edges = {{0,1},{0,2},{1,2}};
        int[] patience = {0,10,10};
        System.out.println(obj.networkBecomesIdle(edges, patience)); // Output: 3
    }
}
