package org.example.revesion.stringMatching;
import java.util.*;
public class RollingHash {
    private static final int mod = (int)1e9+7;
    public static List<Integer> matcher(String text,String pattern){
        List<Integer> res = new ArrayList<>();
        int n = text.length(), m = pattern.length();
        if(m>n) return null;
        int base = 256,pow = 1;
        int ph = 0,th = 0;
        for(int i=0;i<m-1;i++) pow = (pow * base) % mod;
        for(int i=0;i<m;i++){
            ph = (ph * base + pattern.charAt(i))% mod;
            th = (th * base + text.charAt(i))%mod;
        }
        for(int i=m;i<=n;i++){
            if(ph == th && text.regionMatches(i-m,pattern,0,m)) res.add(i-m);
            if(i<n){
                th = (base*((th - text.charAt(i-m) * pow)) + text.charAt(i))%mod;
                th = th < 0 ? th+mod : th;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String text = "aaaaaaaaaa";
        String pattern = "aaaaaaaaa";
        String ans = matcher(text, pattern).toString();
        System.out.println(ans);
    }
}
