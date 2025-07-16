package org.example.Practice;

public class pr03 {
    public static void main(String[] args) {
       int coins[] = {1,2,5};
       int amount=5;
        System.out.println(change(amount,coins));
    }
    public static int change(int amount, int[] coins) {
        int len = coins.length;
        int dp[][] = new int[amount+1][len+1];
        dp[0][0] = 1;

        for (int j = 0; j <= len; j++) {
            dp[0][j] = 1;
        }
        for(int i=1;i<=amount;i++){
            for(int j=1;j<=len;j++){
                dp[i][j] = dp[i][j-1];
                if(i>=coins[j-1]){
                    dp[i][j] = dp[i][j] + dp[i-coins[j-1]][j];
                }
            }
        }
        return dp[amount][len];
    }
}
