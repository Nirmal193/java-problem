package org.example.leetcode;
import java.util.*;
public class Practice {
    private static final int mod = (int)1e9+7;
    public static void find_pattern(String str,String pattern){
        int base = 256;
        int pow = 1;
        int strLen = str.length();
        int patLen = pattern.length();
        if(patLen > strLen)
            return;
        int texthash = 0, patHash = 0;
        // for removing the oldest chracter
        for(int i=0;i<patLen;i++){
            pow = (pow * base) % mod;
        }
        for(int i=0;i<patLen;i++){
            patHash = (base * pattern.charAt(i)) % mod;
            texthash = (base * str.charAt(i)) % mod;
        }
        List<Integer> ansIndex =  new ArrayList<>();
        for(int i=patLen-1;i<strLen;i++){
            if(patHash == texthash){
                ansIndex.add(i-patLen);
            }
            texthash = texthash + str.charAt(i) - (str.charAt(i-patLen) * pow);
        }
    }
}
