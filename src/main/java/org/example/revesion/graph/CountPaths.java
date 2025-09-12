package org.example.revesion.graph;
import java.util.*;
public class CountPaths {
    private final Map<Integer,List<Node>> graph = new HashMap<>();
    private final int MOD = 1000000007;
    public void intializeGraph(int n){
        for(int i=0;i<n;i++) {
            graph.put(i, new ArrayList<>());
        }
    }
    public void addEdge(int u, int v, int weight){
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new Node(v, weight));
        graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new Node(u, weight));
    }
    public int countPaths(int n, int[][] roads) {
        intializeGraph(n);
        for(int i=0;i<roads.length;i++){
            addEdge(roads[i][0], roads[i][1], roads[i][2]);
        }
        boolean visited[] = new boolean[n];
        long[] ways = new long[n];
        Pair dist[] = new Pair[n];
        Queue<Node> queue = new PriorityQueue<>(Comparator.comparingLong(a -> a.weight));
        queue.add(new Node(0, 0));
        for(int i=0;i<n;i++){
            dist[i] = new Pair(Long.MAX_VALUE);
        }
        dist[0].dist = 0;
        while(queue.isEmpty() == false){
            Node node = queue.poll();
            if(visited[node.node] == true) continue;
            visited[node.node] = true;
            for(Node ngh : graph.get(node.node)){
                if(visited[ngh.node] == false){
                    if(dist[ngh.node].dist > dist[node.node].dist + ngh.weight){
                        dist[ngh.node].dist = dist[node.node].dist + ngh.weight;
                        dist[ngh.node].from.clear();
                        dist[ngh.node].from.add(node.node);
                        queue.add(new Node(ngh.node, dist[ngh.node].dist));
                    } else if(dist[ngh.node].dist == dist[node.node].dist + ngh.weight){
                        dist[ngh.node].from.add(node.node);
                    }
                }
            }
        }
        int ans =  dfs_util(n-1, dist, ways);
        return ans;
    }
    public int dfs_util(int node, Pair[] dist, long ways[]) {
        if (node == 0) {
            return 1;
        }
        if (ways[node] != 0) {
            return (int)(ways[node] % MOD);
        }
        ways[node] = 0;
        for (Integer from : dist[node].from) {
            long curr = dfs_util(from, dist, ways);
            ways[node] = (ways[node] + curr) % MOD;
        }
        return (int)(ways[node] % MOD);
    }
    private static class Pair{
        long dist;
        List<Integer> from;
        public Pair(long dist) {
            this.dist = dist;
            this.from = new ArrayList<>();
        }
    }
    private static class Node{
        int node;
        long weight;
        public Node(int node, long weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        CountPaths obj = new CountPaths();
        int[][] roads = {{0,6,7},{0,1,2},{1,2,3},{1,3,3},{6,3,3},{3,5,1},{6,5,1},{2,5,1},{0,4,5},{4,6,2}};
        int n = 7;
        System.out.println(obj.countPaths(n, roads));
    }
}
