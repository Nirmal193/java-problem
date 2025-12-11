package org.example.leetcode.intervalDP;

public class Knapsack {
    private static int solveKnapsack(int weights[], int values[],int W){
        int n = weights.length;
        int dp[][] = new int[n+1][W+1];
        for(int i=1;i<=n;i++){
            int currentWeight = weights[i-1];
            int currentValue = values[i-1];
            for(int w= 1;w<=W;w++){
                if(currentWeight <= w){
                    int includeValue = currentValue + dp[i-1][w - currentWeight];
                    int excludeValue = dp[i-1][w];
                    dp[i][w] = Math.max(includeValue,excludeValue);
                }else{
                    dp[i][w] = dp[i-1][w];
                }
            }
        }
        return dp[n][W];
    }

    public static void main(String[] args) {
        int values[] = {10, 12, 8};
        int weights[] = {2, 3, 1};
        System.out.println(solveKnapsack(weights,values,6));
    }
}
