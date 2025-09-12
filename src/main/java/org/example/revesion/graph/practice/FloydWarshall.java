package org.example.revesion.graph.practice;

public class FloydWarshall {
    public static final int INF = (int)1e9+7;
    public static void floydWarshall(int graph[][],int n){
        int dp[][] = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                dp[i][j] = graph[i][j];
            }
        }
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(dp[i][k] != INF && dp[k][j] != INF){
                        dp[i][j] = Math.min(dp[i][j],dp[i][k] + dp[k][j]);
                    }
                }
            }
        }
        //for checking the negative cycle
        for(int i=0;i<n;i++){
            if(dp[i][i] < 0){
                System.out.println("The graph contains negative cycle ");
                return;
            }
        }
        printSolution(dp,n);
    }
    public static void printSolution(int dp[][], int n){
        System.out.println("This is the following solution for the provided graph");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(dp[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int INF = 1000000000; // Infinity

        int[][] graph = {
                {0,   3,   INF, 5},
                {2,   0,   INF, 4},
                {INF, 1,   0,   INF},
                {INF, INF, 2,   0}
        };

        int n = graph.length;
        floydWarshall(graph, n);
    }
}
