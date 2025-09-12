package org.example.revesion.graph;

import java.util.LinkedList;

public class Floyd_Warshal {
    private static final int INF = 10000000;
    private boolean negativeCycle = false;
    private int[][] dist;
    private int[][] path;
    public Floyd_Warshal(int size) {
        dist = new int[size][size];
        path = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                dist[i][j] = INF;
                path[i][j] = -1;
            }
        }
    }
    public int[][] findAllPairShortest(int[][] distantMatrix) {
        int len = distantMatrix.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                dist[i][j] = distantMatrix[i][j];
                if (distantMatrix[i][j] != INF && i != j) {
                    path[i][j] = i;
                }
            }
        }
        for(int k=0;k<len;k++){
            for(int i=0;i<len;i++){
                for(int j=0;j<len;j++){
                    if(dist[i][k] == INF || dist[k][j] == INF)
                        continue;
                    if(dist[i][j] > dist[i][k] + dist[k][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                        path[i][j] = path[k][j];
                    }
                }
            }
        }
        for(int i=0;i<len;i++){
            if(dist[i][i] < 0) {
                negativeCycle = true;
                break;
            }
        }
        //please add code for printing the distance matrix here
        System.out.println("Distance Matrix:");
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
        //please add code for printing the path matrix here
        System.out.println("Path Matrix:");
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (path[i][j] == -1) {
                    System.out.print("INF ");
                } else {
                    System.out.print(path[i][j] + " ");
                }
            }
            System.out.println();
        }
        return dist;
    }
    public void printPath(int source, int dest){
        if(source == dest){
            System.out.println("source: " + source + " and the destination " + dest +" is the same");
            return;
        }
        if(path[source][dest] == -1){
            System.out.println("No path exists");
            return;
        }
        System.out.print("Path from " + source + " to " + dest + ": ");
        LinkedList list = new LinkedList();
        list.add(dest);
        int currentDest =dest;
        boolean[] visited = new boolean[dist.length];
        visited[currentDest] = true;
        while(path[source][currentDest] != source){
            int nextNode = path[source][currentDest];
            if (visited[nextNode]) {
                System.out.println("\nCycle detected in path");
                return;
            }
            list.addFirst(nextNode);
            visited[nextNode] = true;
            currentDest = nextNode;
        }
        list.addFirst(source);
        System.out.println(String.join("->",list.stream().map(String::valueOf).toList()));
    }

    public static void main(String[] args) {
        Floyd_Warshal fw = new Floyd_Warshal(4);
        int[][] distanceMatrix = {
                {0, 3, 6, 15},
                {INF, 0, -2, INF},
                {INF, INF, 0, 2},
                {1, INF, INF, 0}
        };
        fw.findAllPairShortest(distanceMatrix);
        fw.printPath(3,1);
    }

}
