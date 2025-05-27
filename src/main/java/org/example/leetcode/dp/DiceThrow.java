package org.example.leetcode.dp;

import java.util.Arrays;

public class DiceThrow {
    private final static int mod = (int)1e9+7;
    public static void main(String[] args) {
        System.out.println(numRollsToTarget(2,6,7));
        System.out.println(solve(2,6,3));
    }
    public static int numRollsToTarget(int n, int k, int target) {
        int dp[][] = new int[n+1][target+1];
        dp[0][0] = 1;

        for(int j=1;j<=n;j++){
             for(int i=1;i<=k;i++){
                for(int t=1;t <= target;t++){
                    if(t-i >=0) {
                        dp[j][t] = (dp[j][t] + dp[j - 1][t - i]) % mod;
                    }
                }
            }
        }
        return dp[n][target];
        //return solve(n,k,target);
    }
    public static int solve(int n,int k, int target){
        if(n==0 && target == 0){
            return 1;
        }
        if(target < 0 || n ==0)
            return 0;
        int ans = 0;
        for(int i=1;i<=k;i++){
            ans += solve(n-1,k,target-i);
        }
        return ans;

    }
}
