package org.example.leetcode.dp;

public class MinScoreTriangulation {
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int dp[][] = new int[n][n];
        for(int len = 2;len<n;len++){
            for(int i=0;i<n-len;i++){
                int j = i+len;
                dp[i][j] = Integer.MAX_VALUE;
                for(int k=i+1;k<j;k++){
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + values[i] * values[j] * values[k]);
                }
            }
        }
        return dp[0][n-1];
    }
}
