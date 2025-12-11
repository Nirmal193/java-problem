package org.example.leetcode.intervalDP;

public class MatrixChain {
    public static int solve(int nums[]){
        int len = nums.length-1;
        int dp[][] = new int[len+1][len+1];
        for(int l = 2;l<=len;l++){
            for(int i=1;i<=len-l+1;i++){
                int j = i+l-1;
                dp[i][j] = Integer.MAX_VALUE;
                for(int k=i;k<j;k++){
                    dp[i][j] = Math.min(dp[i][k] + dp[k+1][j] + nums[i-1] * nums[k] * nums[j],dp[i][j]);
                }
            }
        }
        return dp[1][len];
    }

    public static void main(String[] args) {
        int nums[] = {2,1,3,4};
        System.out.println(solve(nums));
    }
}
