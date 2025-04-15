package org.example.leetcode.dp;

import com.sun.source.tree.LineMap;
import org.example.leetcode.trie.Trie;
import org.example.leetcode.trie.TrieNode;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class numMatchingSubseq {
    public static void main(String[] args) {
        String words[] = {"a","bb","acd","ace"};
        String s = "abcde";
        System.out.println(numMatchingSubseq(s,words));
    }
    public static int numMatchingSubseq(String s, String[] words) {
        int len = words.length;
        LinkedList<Pair<Integer,String>> mappedList = new LinkedList<>();
        for(int i=0;i<len;i++){
            mappedList.add(new Pair<>(0,words[i]));
        }
        int ans  = 0;
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            ListIterator<Pair<Integer,String>> iterator = mappedList.listIterator();
            while(iterator.hasNext()){
                Pair<Integer,String> pair = iterator.next();
                int index = pair.key;
                String str = pair.value;
                if(index == str.length()) {
                    ans++;
                    iterator.remove();
                    continue;
                }
                if(str.charAt(index) == ch){
                    pair.key = index+1;
                }
                 iterator.set(pair);
            }
        }
        ListIterator<Pair<Integer,String>> iterator  = mappedList.listIterator();
        while(iterator.hasNext()){
            Pair<Integer,String> p = iterator.next();
            if(p.key == p.value.length())
                ans++;
        }
        return ans;
    }
}
 class Pair<K, V> {
    public  K key;
    public  V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }
}

