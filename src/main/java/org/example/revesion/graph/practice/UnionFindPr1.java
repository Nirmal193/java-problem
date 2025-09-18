package org.example.revesion.graph.practice;

import java.util.Arrays;

public class UnionFindPr1 {
    private final int parents[];
    private final int rank[];
    public UnionFindPr1(int size){
        parents = new int[size];
        rank = new int[size];
        for(int i=0;i<size;i++)
            parents[i] = i;
        Arrays.fill(rank,1);
    }
    public int find(int node){
        if(parents[node] == node)
            return node;
        parents[node] = find(parents[node]);       //path compression
         return parents[node];
    }
    public int getSize(int node){
        return rank[find(node)];
    }
    public void union(int node1, int node2){
        int pr1 = find(node1);
        int pr2 = find(node2);
        if(pr1 == pr2) return;
        if(rank[pr1] > rank[pr2]){
            parents[pr2] = pr1;
            rank[pr1] += rank[pr2];
        }else{
            parents[pr1] = pr2;
            rank[pr2] += rank[pr1];
        }
    }

    public int findLatestStep(int[] arr, int m) {
        int n = arr.length;
        boolean visited[] = new boolean[n+2];
        UnionFindPr1 uf = new UnionFindPr1(n+2);
        int sizeCount[] = new int[n+2];
        int step  = -1;
        for(int i=0;i<n;i++){
            int index = arr[i];
            sizeCount[1]++;
            visited[index] = true;
            if(visited[index-1]){
                int leftParent = uf.find(index-1);
                int leftSize = uf.getSize(index-1);

                sizeCount[1]--;
                sizeCount[leftSize]--;

                uf.union(index,index-1);
                sizeCount[uf.getSize(uf.find(index))]++;
            }
            if(visited[index+1]){
                int rightParent = uf.find(index+1);
                int rightSize = uf.getSize(index+1);

                int currentRoot = uf.find(index);
                int currentSize = uf.getSize(currentRoot);

                sizeCount[rightSize]--;
                sizeCount[currentSize]--;

                 uf.union(index,index+1);
                 sizeCount[uf.getSize(uf.find(index))]++;
            }
            if(sizeCount[m] > 0)
                step = i+1;
        }
        return step;
    }
}
