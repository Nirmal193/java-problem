package org.example.leetcode.dp;

import org.example.leetcode.trie.Trie;

public class Driver {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.addString("nirmal");
        trie.addString("nihar");
        trie.addString("ritu");
        System.out.println(trie.search("nirmad"));
    }
}
