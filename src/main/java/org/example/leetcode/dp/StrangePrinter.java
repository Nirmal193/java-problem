package org.example.leetcode.dp;

public class StrangePrinter {
    public int strangePrinter(String s) {
        if(s.length() == 0 || s == null)
            return 0;
        int len = s.length();
        int dp[][] = new int[len][len];
        for(int i=0;i<len;i++){
            dp[i][i] = 1;
        }
        for(int l=1;l<len;l++){
            for(int i=0;i+l<len;i++){
                int j = i+ l;
                if(l==1){
                    dp[i][j] = (s.charAt(i) == s.charAt(j)) ? 1 : 2;
                    continue;
                }
                dp[i][j] = Integer.MAX_VALUE;
                for(int k =i;k+1<=j;k++){
                    dp[i][j] = Math.min(dp[i][j] , dp[i][k] + dp[k+1][j]);
                }
                if(s.charAt(i) == s.charAt(j))
                    dp[i][j]--;
            }
        }
        return dp[0][len-1];
    }

    public static void main(String[] args) {
        StrangePrinter sp = new StrangePrinter();
        System.out.println(sp.strangePrinter("aba"));
    }
}
