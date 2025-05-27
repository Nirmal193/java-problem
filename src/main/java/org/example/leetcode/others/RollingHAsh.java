package org.example.leetcode.others;

import java.util.*;

public class RollingHAsh {
    public static void main(String[] args) {
        String str = "AAAAAAAAAAAAA";
        System.out.println(matchers(str, 10));
    }
    public static List<String> matchers(String str,int k) {
        int mod = (int) 1e9 + 7;
        int base = 31;
        int len = str.length();
        long hash = 0;
        long pow = 1;
        Set<Long> seehHashes = new HashSet<>();
        Set<String> ansSet = new HashSet<>();
        List<String> ans = new ArrayList<>();
        for(int i=0;i<k;i++){
            pow = (pow * base) % mod;
        }
        for(int i=0;i<len;i++){
           hash = (hash * base + str.charAt(i) - 'A'+1)%mod;
           if(i >= k){
               long remove = ((str.charAt(i-k)-'A' + 1) * pow) % mod;
               hash = (hash - remove + mod) % mod;
           }
            if(i>=k-1){
                if(seehHashes.contains(hash)){
                    ansSet.add(str.substring(i - k + 1, i + 1));
                }else
                    seehHashes.add(hash);
            }
        }
        return new ArrayList<>(ansSet);
    }
}
