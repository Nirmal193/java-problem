package org.example.leetcode.trie;

import java.util.HashSet;

public class TrieNode {
    public Boolean isStringEnd;
    public TrieNode[] next;
    public String word;
    public TrieNode(){
        next = new TrieNode[26];
        isStringEnd = false;
    }
}
