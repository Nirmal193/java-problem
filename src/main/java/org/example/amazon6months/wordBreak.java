package org.example.amazon6months;

import java.util.*;

public class wordBreak {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("leet","code");
        System.out.println(wordBreak("leetcode",list));
    }
    public static boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean dp[]  = new boolean[len+1];
        dp[0] = true;
        Set<String> dict = new HashSet<>(wordDict);
        for(int i=1;i<=len;i++){
            for(int j=0;j<=i;j++){
                String str = s.substring(j,i);
                if(dict.contains(str)){
                    dp[i] = dp[j] || dp[i];
                }
            }
        }
        return dp[len];
    }
}
