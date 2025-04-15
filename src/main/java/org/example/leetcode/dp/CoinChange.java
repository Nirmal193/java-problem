package org.example.leetcode.dp;

import java.util.Arrays;

public class CoinChange {
    public static void main(String[] args) {
        int coins[] = new int[] {1,2,5};
        int memo[] = new int[15+1];
//        System.out.println(coinChangeMemo(coins,15));
//        System.out.println(coinChange(coins,15,0));
        System.out.println(minCoinChange(coins,15));
        System.out.println(coinChangeButtomUp(coins,15));
    }
    public static int minCoinChange(int[] coins,int amount){
        int len = coins.length;
        int dp[][] = new int[len][amount+1];
        for(int i=0;i<len;i++)
            Arrays.fill(dp[i],amount);
        dp[0][0] = 0;
        for(int i=0;i<len;i++)
            dp[i][0] = 0;
        for(int i=1;i<len;i++){
            for(int j=coins[i];j<=amount;j++){
                dp[i][j] = Math.min(dp[i-1][j] ,1 + dp[i][j-coins[i]]);
            }
        }
        return dp[len-1][amount];
    }
    public static int coinChangeMemo(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subProblem = coinChangeMemo(coins, amount - coin);
            if (subProblem != -1) {
                minCoins = Math.min(minCoins, subProblem + 1);
            }
        }
        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }
    public static int coinChange(int coins[],int amount,int index){
        if(amount == 0)
            return 1;
        if(amount < 0 || index >= coins.length)
            return 0;
        int ans = coinChange(coins,amount-coins[index],index) + coinChange(coins,amount,index+1);
        return ans;
    }
    public static int coinChangeButtomUp(int coins[],int amount){
        int len = coins.length;
        int dp[][] = new int[len+1][amount+1];
        for(int i=0;i<len;i++)
            dp[i][0] = 1;
        for(int i=1;i<=len;i++){
            for(int j=1;j<=amount;j++){
                dp[i][j] = dp[i-1][j];
                if(j >= coins[i-1]){
                    dp[i][j] += dp[i][j-coins[i-1]];
                }
            }
        }
        return dp[len][amount];
    }
}
