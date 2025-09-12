package org.example.revesion.graph;
import java.util.*;
public class GraphRepresentation {
    public List<List<Integer>> graph;
    int vertices;
    int parents[];
    int rank[];
    public GraphRepresentation(int n){
        this.vertices = n;
        parents = new int[n];
        rank = new int[n];
        Arrays.fill(rank,1);
        graph = new ArrayList<>(n);
        for(int i=0;i<n;i++){
            parents[i] = i;
            graph.add(new ArrayList<>());
        }
    }
    public void addEdgeForDircted(int v, int e){
        graph.get(v).add(e);
    }
    public void addEdgeForunDirected(int v, int e){
        graph.get(v).add(e);
        graph.get(e).add(v);
    }
    public void DFS_Util_Iterative(int source){
        System.out.println();
        boolean visited[] = new boolean[this.vertices];
        visited[source] = true;
        Stack<Integer> stack = new Stack<>();
        stack.push(source);
        while(!stack.isEmpty()){
            int current = stack.pop();
            System.out.print(current + "->");
            List<Integer> nghList = graph.get(current);
            for(Integer ngh:nghList){
                if(!visited[ngh]){
                    visited[ngh] = true;
                    stack.add(ngh);
                }
            }
        }
    }
    public void DFS_Util(int source,boolean visited[]){
        //visited[source] = true;
        //System.out.print(source + "->");
        List<Integer> nghList = graph.get(source);
        for(Integer ngh:nghList){
            if(!visited[ngh]){
                visited[source] = true;
                DFS_Util(ngh,visited);
            }
        }
    }
    public void BFS_util(int source){
        Queue<Integer> queue = new LinkedList<>();
        boolean visited[] = new boolean[this.vertices];
        queue.add(source);
        System.out.println();
        visited[source] = true;
        while(!queue.isEmpty()){
            int current = queue.poll();
            System.out.print(current+"->");
            List<Integer> nghList = graph.get(current);
            for(Integer ngh: nghList){
                if(!visited[ngh]){
                    queue.offer(ngh);
                    visited[ngh] = true;
                }
            }
        }
    }


    /*
    Cycle detection using DFS for a undirected graph
    using parent and neighbour
     */
    public boolean detect_cycle_dfs_undirected(int source,boolean visited[],int parent){
        visited[source] = true;
        List<Integer> nghList = graph.get(source);
        for(Integer ngh : nghList){
            if(!visited[ngh]){
                visited[ngh] = true;
                detect_cycle_dfs_undirected(ngh,visited,source);
            }else if(parent != ngh)
                return true;
        }
        return false;
    }

    /*
    For finding the connected component and detect cycles in undirected graph only
    cause the union find is just for checking the connectivity between two nodes
    it does not account for the direction of those edges
     */

    public int find(int source) {
        if (parents[source] != source)
            parents[source] = find(parents[source]);  //path compression
        return parents[source];
    }

    public void union(int node1, int node2){
        int parent1 = find(node1);
        int parent2 = find(node2);
        if(parent2 == parent1) return;
        if(rank[node1] > rank[node2]) {
            parents[node2] = node1;
        }else if(rank[node1] < rank[node2]){
            parents[node1] = node2;
        }else{
            parents[node2] = node1;
            rank[node1]++;
        }
    }

    public boolean find_cycle_using_union_find(){
        for(int i=0;i<vertices;i++){
            List<Integer> nghList = graph.get(i);
            for(Integer ngh : nghList){
                if(i<ngh) {
                    if (find(i) == find(ngh))
                        return true;
                    else
                        union(i, ngh);
                }
            }
        }
        return false;
    }

    public boolean find_cycle_for_directed(int node,boolean visited[],Set<Integer> recStack){
        visited[node] = true;
        recStack.add(node);
        List<Integer> nghList = graph.get(node);
        for(Integer ngh: nghList){
            if(visited[ngh] = false) {
                find_cycle_for_directed(ngh, visited, recStack);
            }
            else if(recStack.contains(ngh))
                return true;
        }
        recStack.remove(node);
        return false;
    }

    public static void main(String[] args) {
        int numberOfEdges = 4;
        GraphRepresentation gph = new GraphRepresentation(numberOfEdges);
        boolean visited[] = new boolean[numberOfEdges];
        gph.addEdgeForunDirected(0,1);
        gph.addEdgeForunDirected(1,2);
        gph.addEdgeForunDirected(2,3);
        gph.addEdgeForunDirected(3,0);
        System.out.println(gph.detect_cycle_dfs_undirected(0,visited,-1));
//        gph.addEdgeForunDirected(0,1);
//        gph.addEdgeForunDirected(1,2);
//        gph.addEdgeForunDirected(2,3);
//        gph.BFS_util(0);
    }
}
