package org.example.amazon6months;

import org.example.leetcode.trie.TrieNode;

import java.util.*;

public class suggestedProducts {
     TrieNode trieNode = new TrieNode();

    public static void main(String[] args) {
        String str = "mouse";
        String products[] = {"mobile","mouse","moneypot","monitor","mousepad"};
        //System.out.println(suggestedProducts(products,str));
    }
    public  List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> ans = new ArrayList<>();
        TrieNode current = trieNode;
        for(int i=0;i<products.length;i++)
            addString(products[i]);
        for(int i=0;i<searchWord.length();i++){
            int index =  searchWord.charAt(i)-'a';
            List<String> li = new ArrayList<>();
            if(current.next[index] != null){
                current = current.next[index];
            }
            dfs(current,li);
            ans.add(li);
        }
        return ans;
    }
    public void dfs(TrieNode node,List<String> suggestions){
        if(node == null || suggestions.size() == 3)
            return;
        if(node.isStringEnd)
            suggestions.add(node.word);
        for(int i=0;i<26;i++){
            if(node.next[i] != null){
                dfs(node.next[i],suggestions);
            }
        }
    }
    public void addString(String s){
        TrieNode current = trieNode;
        for(int i=0;i<s.length();i++){
            int index = s.charAt(i)-'a';
            if(current.next[index] ==  null){
                current.next[index] = new TrieNode();
            }
            current = current.next[index];
        }
        current.isStringEnd = true;
        current.word=s;
    }
}

