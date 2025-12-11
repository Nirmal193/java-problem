package org.example.leetcode.intervalDP;

public class CoinChange {
    private static int change(int coins[], int amount){
        int dp[] = new int[amount+1];
        dp[0] = 1;
        for(int i=0;i<coins.length;i++){
            for(int j = coins[i];j<=amount;j++){
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        int coins[] = {1, 2, 5};
        System.out.println(change(coins,5));
    }
}
