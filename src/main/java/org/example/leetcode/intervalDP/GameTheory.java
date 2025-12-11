package org.example.leetcode.intervalDP;

public class GameTheory {
    private static boolean maxDiff(int nums[]){
        int n = nums.length;
        int dp[][] = new int[n][n];
        for(int i=0;i<n;i++){
            dp[i][i] = nums[i];
        }
        for(int l=2;l<=n;l++){
            for(int i=0;i<= n-l;i++) {
                int j = i + l - 1;
                int scoreTakeI = nums[i] - dp[i+1][j];
                int scoreTakej = nums[j] - dp[i][j-1];
                dp[i][j] = Math.max(scoreTakeI,scoreTakej);
            }
        }
        System.out.println(dp[0][n-1]);
        return dp[0][n-1] >= 0;
    }

    public static void main(String[] args) {
        int nums[] = {1,5,2};
        System.out.println(maxDiff(nums));
    }
}
