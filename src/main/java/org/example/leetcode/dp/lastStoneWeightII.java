package org.example.leetcode.dp;

import java.util.Arrays;

public class lastStoneWeightII {
    public int lastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        sum = sum/2;
        int n = stones.length;
        boolean dp[][] = new boolean[n + 1][sum + 1];
        for(int i=0;i<=n;i++)
            dp[i][0] = true;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=sum;j++){
                if(stones[i-1] > j){
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-stones[i-1]];
                }
            }
        }
        for(int i=sum;i>=0;i--)
            if(dp[n][i])
                return sum - i;
        return 0;
    }
}
