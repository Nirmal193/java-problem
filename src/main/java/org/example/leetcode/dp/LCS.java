package org.example.leetcode.dp;

public class LCS {
    public static void main(String[] args) {
        System.out.println(lcs("naman","nirmal",5,6));
        System.out.println(lcsButtomUp("naman","naman"));
    }
    public static int lcsButtomUp(String s1, String s2){
        int len1 = s1.length();
        int len2 = s2.length();
        int dp[][] = new int[len1+1][len2+1];
        for(int i=1;i<=len1;i++){
            for(int j=1;j<=len2;j++){
                if(s1.charAt(i-1) ==  s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[len1][len2];
    }
    public static int lcs(String s1, String s2, int i, int j) {
        if (i == 0 || j == 0) return 0;
        if (s1.charAt(i - 1) == s2.charAt(j - 1))
            return 1 + lcs(s1, s2, i - 1, j - 1);
        return Math.max(lcs(s1, s2, i - 1, j), lcs(s1, s2, i, j - 1));
    }
}
