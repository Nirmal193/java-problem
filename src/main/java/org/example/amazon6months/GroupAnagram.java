package org.example.amazon6months;

import java.util.*;

public class GroupAnagram {
    public List<List<String>> groupAnagramsOptmized(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        List<List<String>> ans = new ArrayList<>();
        for(String str : strs){
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key = new String(charArray);
            map.computeIfAbsent(key,k-> new ArrayList<>()).add(key);
        }
        ans.addAll(map.values());
        return ans;
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        Map<String,List<String>> map = new HashMap();
        for(int i=0;i<strs.length;i++){
            boolean added = false;
            for (String key : map.keySet()) {
                if (checkAnagram(key, strs[i])) {
                    map.get(key).add(strs[i]);
                    added = true;
                    break;
                }
            }
            if (!added) {
                List<String> newList = new ArrayList<>();
                newList.add(strs[i]);
                map.put(strs[i], newList);
            }
        }
        ans.addAll(map.values());
        return ans;
    }
    public boolean checkAnagram(String s1,String s2){
        if(s1.length() != s2.length())
            return false;
        int freq1[] = new int[26];
        int freq2[] = new int[26];
        for(int i=0;i<s1.length();i++){
            freq1[s1.charAt(i) - 'a']++;
            freq2[s2.charAt(i) - 'a']++;
        }
        for(int i=0;i<26;i++){
            if(freq1[i] != freq2[i])
                return false;
        }
        return true;
    }
}
