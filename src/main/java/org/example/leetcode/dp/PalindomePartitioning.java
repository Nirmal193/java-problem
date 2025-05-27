package org.example.leetcode.dp;
import java.util.*;
public class PalindomePartitioning {
    public static int partition(String s) {
        int len = s.length();
        boolean isPalindrome[][] = new boolean[len][len];
        for(int i=0;i<len;i++)
            isPalindrome[i][i] = true;
        for(int l=2;l<=len;l++){
            for(int i=0;i<=len-l;i++){
                int j = i+l-1;
                if(s.charAt(i) == s.charAt(j)){
                    if(j-i == 1)
                        isPalindrome[i][j] = true;
                    else
                        isPalindrome[i][j] = isPalindrome[i+1][j-1];
                }
            }
        }
        int dp[] = new int[len];
        for(int i=0;i<len;i++){
            dp[i] = i;
            for(int j=0;j<=i;j++){
                if(isPalindrome[j][i]){
                    dp[i] = (j==0) ? 0 : Math.min(dp[j-1]+1 , dp[i]);
                }
            }
        }
        return dp[len-1];
    }

    public static void main(String[] args) {
        System.out.println(partition("aab"));
    }
}
