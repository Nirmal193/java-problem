package org.example.leetcode.graph;

import java.util.*;

public class kSimilarity {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2  = "bca";
        System.out.println(bfs_util(s1,s2));
    }
    public static int kSimilarity(String s1, String s2) {
        if(s1.length() != s2.length())
            return -1;
        if(checkAnagram(s1,s2))
            return -1;
        Map<Character, Queue<Integer>> pos = new HashMap<>();
        for(int i=0;i<s1.length();i++){
            pos.putIfAbsent(s2.charAt(i),new LinkedList<>());
            pos.get(s2.charAt(i)).add(i);
        }
        int permutation[]  = new int[s1.length()];
        for(int i=0;i<s1.length();i++){
            permutation[i] = pos.get(s1.charAt(i)).poll();
        }
        int ans = 0;
        boolean visited[] = new boolean[s1.length()];
        for(int i=0;i<permutation.length;i++){
            if(visited[i])
                continue;
            int count = i;
            int numberOfNodes = 1;
            while(permutation[count] != i){
                count = permutation[count];
                numberOfNodes++;
                visited[count] = true;
            }
            visited[i] = true;
            ans += numberOfNodes-1;
        }
        return ans;
    }
    public static int bfs_util(String s1, String s2){
        if(s1.equals(s2))
            return 0;
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(s1);
        visited.add(s1);
        int level = 0;
        while(!queue.isEmpty()){
            int sz = queue.size();
            for(int k=0;k<sz;k++) {
                String current = queue.poll();
                if (current.equals(s2))
                    return level;
                int j = 0;
                while (j < current.length() && current.charAt(j) == s2.charAt(j))
                    j++;
                char sarr[] = current.toCharArray();
                for (int i = j + 1; i < sarr.length; i++) {
                    if(sarr[i] == s2.charAt(j)) {
                        swap(sarr, j, i);
                        String newStr = new String(sarr);
                        if (!visited.contains(newStr)) {
                            visited.add(newStr);
                            queue.add(newStr);
                        }
                        swap(sarr, i, j);
                    }
                }
            }
            level++;
        }
        return level;
    }
    public static void swap(char[] s, int i,int j){
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

    public static boolean checkAnagram(String s1, String s2){
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        for(int i=0;i<c1.length;i++){
            if(c1[i] != c2[i])
                return false;
        }
        return true;
    }
}
