package org.example.leetcode.trie;

public class Trie {
    public TrieNode trieNode;
    public Trie(){
        trieNode = new TrieNode();
    }
    public void addString(String s){
        TrieNode current = trieNode;
        for(int i =0 ;i<s.length();i++){
            int index = s.charAt(i)-'a';
            if(current.next[index] == null)
            current.next[index] = new TrieNode();

            current = current.next[index];
        }
        current.isStringEnd = true;
    }
    public boolean search(String s){
        TrieNode current = trieNode;
        for(int i=0;i<s.length();i++){
            if(current.next[s.charAt(i)-'a'] != null)
                current = current.next[s.charAt(i)-'a'];
            else
                return false;
        }
        return current.isStringEnd;
    }
}
