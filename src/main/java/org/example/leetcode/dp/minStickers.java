package org.example.leetcode.dp;

import java.util.*;
import java.util.stream.Collectors;

public class minStickers {
    public static int minStickers(String[] stickers, String target) {
        Map<String,Integer> memo = new HashMap<>();
        memo.put("",0);
        int len = stickers.length;
        Set<Character> targetSet = target.chars().mapToObj(ch->(char)ch).collect(Collectors.toSet());
        int stics[][] = new int[len][26];
        for(int i=0;i<len;i++){
            for(int j=0;j<stickers[i].length();j++){
                char ch = stickers[i].charAt(j);
                if(targetSet.contains(ch))
                    stics[i][ch-'a']++;
            }
        }
        int ans = solve(stics,target,memo);
        return ans == 999999 ? -1 : ans;
    }
    public static int solve(int[][] stickers,String target, Map<String,Integer> memo){
        if(target.length() == 0)
            return 0;
        if(memo.containsKey(target))
            return memo.get(target);
        int ans = 999999;
        for(int i=0;i<stickers.length;i++){
            String remains = remaining(stickers[i],target);
                if(target.length() != remains.length()){
                    int result = solve(stickers, remains, memo);
                    if (result != -1) {
                        ans = Math.min(ans, 1 + result);
                    }
                }
        }
        memo.put(target, ans == 999999 ? -1 : ans);
        return ans;
    }
    public static String remaining(int[] sticker,String target){
        StringBuilder sb = new StringBuilder();
        int[] stk = Arrays.copyOf(sticker,sticker.length);
        for(int i=0;i<target.length();i++){
            if(stk[target.charAt(i)-'a'] >0){
                stk[target.charAt(i)-'a']--;
            }else{
                sb.append(target.charAt(i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String stickers[] = {"notice","possible"};
        System.out.println(minStickers(stickers,"basicbasic"));
    }
}
