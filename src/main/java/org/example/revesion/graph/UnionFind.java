package org.example.revesion.graph;

public class UnionFind {
    int parent[];
    int rank[];
    public UnionFind(int size){
        parent = new int[size];
        rank = new int[size];
        for(int i=0;i<size;i++){
            parent[i] = i;
            rank[i] = 1;
        }
    }
    public int find(int node){
        if(parent[node] == node)
            return node;
        else {
            parent[node] = find(parent[node]);
            return parent[node];
        }
    }
    public void union(int node1, int node2){
        int parent1 = find(node1);
        int parent2 = find(node2);
        if(parent2 == parent1)
            return;
        if(rank[parent1] < rank[parent2]){
            parent[parent1] = parent2;
        }else if(rank[parent1] > rank[parent2]){
            parent[parent2] = parent1;
        }else{
            parent[parent2] = parent1;
            rank[parent2]++;
        }
    }
}
