package org.example.revesion.graph;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class maximumImportance {
    public long maximumImportance(int n, int[][] roads) {
        Pair[] indegree= new Pair[n];
        for (int i=0;i<n;i++){
            indegree[i] = new Pair(i, 0);
        }
        for (int i=0;i<roads.length;i++){
            indegree[roads[i][0]].indegree++;
            indegree[roads[i][1]].indegree++;
        }
        Arrays.sort(indegree,(a,b)-> Integer.compare(a.indegree,b.indegree));
        Map<Integer, Integer> collect = IntStream.range(0, indegree.length).boxed()
                .collect(Collectors.toMap(i -> indegree[i].node, i -> i+1));
        long sum = 0;
        for(int i=0;i<roads.length;i++){
            sum += collect.get(roads[i][0]) + collect.get(roads[i][1]);
        }
        return sum;
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
