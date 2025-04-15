package org.example.leetcode.dp;

public class Kanpsack {
    public int knapsack(int[] wt, int[] val, int W, int n) {
        if (n == 0 || W == 0) return 0;
        if (wt[n - 1] > W) return knapsack(wt, val, W, n - 1);
        return Math.max(val[n - 1] + knapsack(wt, val, W - wt[n - 1], n - 1),
                knapsack(wt, val, W, n - 1));
    }
    public int knapsackButtomUp(int wt[],int val[],int w){
        int len = wt.length;
        int dp[][] = new int[len+1][w+1];
        for(int i=1;i<=len;i++){
            for(int j=1;j<=w;j++){
                if (wt[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], val[i - 1] + dp[i - 1][j - wt[i - 1]]);
                }
            }
        }
        return dp[len][w];
    }
}
