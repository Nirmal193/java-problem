package org.example.leetcode.dp;

public class MinimumSUmOfLeafNodes {
    public static int solve(int arr[]){
        int n = arr.length;
        int max[][] = new int[n][n];
        for(int i=0;i<n;i++){
            max[i][i] = arr[i];
        }
        for(int l=2;l<=n;l++){
            for(int i=0;i<n-l+1;i++){
                int j = i+l-1;
                max[i][j] = Math.max(max[i][j-1], max[i+1][j]);
            }
        }
        int dp[][] = new int[n][n];
        for(int i=0;i<n;i++){
            dp[i][i] = 0;
        }
        for(int i=0;i<n-1;i++){
            dp[i][i+1] = arr[i] * arr[i+1];
        }
        for(int l=3;l<=n;l++){
            for(int i=0;i<n-l+1;i++){
                int j = i+l-1;
                dp[i][j] = Integer.MAX_VALUE;
                for(int k=i;k<j;k++){
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + max[i][k]*max[k+1][j]);
                }
            }
        }
        return dp[0][n-1];
    }
    public static void main(String[] args) {
        int arr[] = {6, 2, 5,4};
        System.out.println(solve(arr)); // Output: 60
    }
}
