package org.example.leetcode.graph;

import java.util.HashMap;
import java.util.*;
public class ShortestAlternatingPaths {
    private Map<Integer,List<Node>> graph = new HashMap<>();
    public void intiateGraph(int n){
        for(int i=0;i<n;i++) {
            graph.put(i, new ArrayList<>());
        }
    }
    public void addEdge(int u, int v, int color) {
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new Node(v, color));
    }
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        int ans[] = new int[n];
        Arrays.fill(ans, -1);
        for(int i=0;i<redEdges.length;i++){
            addEdge(redEdges[i][0], redEdges[i][1], 0);
        }
        for(int i=0;i<blueEdges.length;i++){
            addEdge(blueEdges[i][0], blueEdges[i][1], 1);
        }
        boolean visited[] = new boolean[n];
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, -1));
        visited[0] = true;
        int level = 0;
        while(!queue.isEmpty()){
            int sz = queue.size();
            for(int i=0;i<sz;i++){
                Node node = queue.poll();
                if(graph.get(node.node) == null) continue;
                for(Node ngh : graph.get(node.node)){
                    if(ngh.node == node.node) continue;
                    if(ngh.color != node.color){
                        visited[ngh.node] = true;
                        queue.add(ngh);
                        if(ans[ngh.node] == -1) {
                            ans[ngh.node] = level + 1;
                        }
                    }
                }
            }
            level++;
        }
        return Arrays.stream(ans).toArray();
    }
    private static class Node {
        int node;
        int color;
        public Node(int node, int color) {
            this.node = node;
            this.color = color;
        }
    }

    public static void main(String[] args) {
        ShortestAlternatingPaths obj = new ShortestAlternatingPaths();
        int[][] redEdges = {{0,1}, {1,2}, {2,3}, {3,4}};
        int[][] blueEdges = {{1,2},{2,3},{3,1}};
        int n = 5;
        System.out.println(Arrays.toString(obj.shortestAlternatingPaths(n, redEdges, blueEdges)));
    }
}
