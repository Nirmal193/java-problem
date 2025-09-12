package org.example.revesion.stringMatching;
import java.util.*;
public class KMP {
    public static List<Integer> kmpSearch(String text,String pat){
        int n = text.length(), m = pat.length();
        int lps[] = new int[m];
        List<Integer> res = new ArrayList<>();
        //built the LPS array
        for(int i=1,len = 0;i<m;){
            if(pat.charAt(i) == pat.charAt(len)) lps[i++] = ++len;
            else if(len > 0) len = lps[len-1];
            else lps[i++] = 0;
        }
        //building the result Array
        for(int i=1,j = 0;i<n;){
            if(text.charAt(i) == pat.charAt(j)) {
                i++; j++;
                if(j == m) {res.add(i-m); j = lps[j-1];}
            }else if(j > 0) j = lps[j-1];
            else i++;
        }
        return res;
    }
    public static List<Integer> kmpMatcher(String text,String pat){
        int m = pat.length(), n = text.length();
        List<Integer> res = new ArrayList<>();
        int lps[] = new int[m];
        for(int i=1,len=0;i<m;){
            if(pat.charAt(i) == pat.charAt(len)) lps[i++] = ++len;
            else if(len > 0) len = lps[len-1];
            else lps[i++] = 0;
        }
        for(int i=0,j=0;j<n;){
            if(text.charAt(i) == pat.charAt(j)){
                i++;j++;
                if(j==m){res.add(i-m); j = lps[j-1];}
            }else if(j > 0) j = lps[j-1];
            else i++;
        }
        return res;
    }

    public static void main(String[] args) {
        String text = "abcbcaabc";
        String pattern = "abc";
        System.out.println(kmpMatcher(text,pattern).toString());
    }
}
