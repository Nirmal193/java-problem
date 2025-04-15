package org.example.leetcode.dp;

public class PalindromePartitioning {
    public static void main(String[] args) {
        System.out.println(minCut("nirmal"));
    }
    public static int minCut(String s){
        int len = s.length();
        boolean pal[][] = new boolean[len][len];
        for(int i=0;i<len;i++)
            pal[i][i] = true;
        for(int l=1;l<len;l++){
            for(int i=0;i<len-l;i++){
                int j = i+l;
                if(s.charAt(i) == s.charAt(j))
                    pal[i][j] = pal[i+1][j-1];
            }
        }
        int dp[] = new int[len];
        for(int i=0;i<len;i++){
            dp[i] = i;
            for(int j=0;j<=i;j++){
                if(pal[i][j]){
                    dp[i] = (j==0) ? 0 : Math.min(dp[i],dp[j-1]+1);
                }
            }
        }
        return dp[len-1];
    }
}
