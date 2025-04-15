package org.example.Practice;

import java.util.Arrays;

public class Pr02 {
    public static void main(String[] args) {
        int nums[] = {1,2,5};
        System.out.println(solve3(nums,15));
        System.out.println(change(nums,15));
    }
    public static int solve3(int nums[],int amount){
        int dp[] = new int[amount+1];
        dp[0] = 1;
        for(int i=1;i<=amount;i++){
            for(int j=0;j<nums.length;j++){
                if(i >= nums[j]){
                    dp[i] += dp[i-nums[j]];
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[amount];
    }
    public static int change(int[] coins,int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j] += dp[j - coin];
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[amount];
    }
    public static int solve1(int nums[],int amount){
        int dp[] = new int[amount+1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for(int i=1;i<=amount;i++){
            for(int j=0;j<nums.length;j++){
                if(nums[j] <= i){
                    dp[i] = Math.min(dp[i],dp[i-nums[j]]+1);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[amount];
    }
    public static int solve2(int nums[], int amount){
        int dp[] = new int[amount+1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int coin : nums) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[amount];
    }

}
