package org.example.revesion.stringMatching;
import java.util.*;
public class Zalgo {
    public static int[] zFunctionold(String text){
        int n = text.length();
        int[] z = new int[n];
        int l = 0, r = 0;
        for (int i = 1; i < n; i++) {
            if (i <= r) z[i] = Math.min(r - i + 1, z[i - l]);
            while (i + z[i] < n && text.charAt(z[i]) == text.charAt(i + z[i])) z[i]++;
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        return z;
    }
    public static int[] zFunction(String text){
        int n = text.length();
        int z[] = new int[n];
        int l = 0,r = 0;
        for(int i=1;i<n;i++){
            if(i<=r) z[i] = Math.min(r-i+1,z[i-l]);
            while(i + z[i] < n && text.charAt(z[i]) == text.charAt(i + z[i])) z[i]++;
            if(i+z[i] - 1 > r){
                l = i;
                r = i + z[i] - 1;
            }
        }
        return z;
    }
    public static List<Integer> zSearch(String text, String pattern) {
        String combined = pattern + "$" + text;
        int[] z = zFunction(combined);
        List<Integer> result = new ArrayList<>();
        int m = pattern.length();
        for (int i = 0; i < z.length; i++) {
            if (z[i] == m) {
                result.add(i - m - 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String text = "abcdedabdghisabc";
        List<Integer> ans = zSearch(text, "abc");
        System.out.println(ans.toString());
    }
}
