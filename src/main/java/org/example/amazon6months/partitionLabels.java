package org.example.amazon6months;


import java.util.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class partitionLabels {
    public List<Integer> partitionLabels(String s) {
        List<Integer> ans = new ArrayList<>();
        int index[] = new int[26];
        for(int i=0;i<s.length();i++){
            index[s.charAt(i)-'a'] = i;
        }
        int maxtill = 0;
        int lastIndex = 0;
        for(int i=0;i<s.length();i++){
            maxtill = Math.max(maxtill,index[s.charAt(i)-'a']);
            if(maxtill == i) {
                ans.add((i+1) - lastIndex);
                lastIndex = i+1;
                maxtill = 0;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String words[] = new String[]{"i","love","leetcode","i","love","coding"};
        System.out.println(topKFrequent(words,2));
    }
    public static List<String> topKFrequent(String[] words, int k) {
        List<String> ans = new ArrayList<>();
        Map<String,Integer> freq = new HashMap<>();
        for(String word : words){
            freq.putIfAbsent(word,0);
            freq.put(word,freq.get(word)+1);
        }
        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int x = freq.get(s1).compareTo(freq.get(s2));
                if(x==0){
                    return s2.compareTo(s1);
                }else
                    return x;
            };
        };
        List<String> word = new ArrayList<>();
        for(Map.Entry<String,Integer> m: freq.entrySet()){
            word.add(m.getKey());
        }
        word = word.stream().sorted(comp).collect(Collectors.toList());
        for(int i = word.size()-1;i>= word.size()-k;i--){
            ans.add(word.get(i));
        }
        return ans;
    }
}
