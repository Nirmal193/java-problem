package org.example.leetcode.binarySearch;

import java.util.TreeMap;

public class LC911{
    int currentWinner;
    int maxVote;
    int voters[] = new int[5001];
    TreeMap<Integer,Integer> timeline;
    public LC911(int[] persons, int[] times) {
        timeline = new TreeMap<>();
        int len = persons.length;
        maxVote = Integer.MIN_VALUE;
        for(int i=0;i<len;i++){
            int cvote = ++voters[persons[i]];
            if(maxVote <= cvote){
                maxVote = cvote;
                currentWinner = persons[i];
            }
            timeline.put(times[i],currentWinner);
        }
    }

    public int q(int t) {
        return timeline.floorEntry(t).getValue();
    }
}
