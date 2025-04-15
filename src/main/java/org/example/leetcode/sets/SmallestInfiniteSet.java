package org.example.leetcode.sets;

import java.util.TreeSet;

public class SmallestInfiniteSet {
    TreeSet<Integer> set = new TreeSet<>();
    public SmallestInfiniteSet() {
        for(int i=1;i<=1000;i++){
            set.add(i);
        }
    }

    public int popSmallest() {
        return set.pollFirst();
    }

    public void addBack(int num) {
        set.add(num);
    }
}
