package org.example.leetcode.binarySearch;

import java.util.TreeMap;

public class FindRightINterval {
    public static int[] findRightInterval(int[][] intervals) {
        TreeMap<Integer,Integer> tmap = new TreeMap<>();
        int len = intervals.length;
        for(int i=0;i<len;i++){
            tmap.put(intervals[i][0],i);
        }
        int ans[] = new int[len];
        for(int i=0;i<len;i++){
            Integer index = tmap.ceilingKey(intervals[i][1]);
            ans[i] = index == null ? -1 : tmap.get(index);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] intervals = {
                {3, 4},
                {2, 3},
                {1, 2}
        };
        findRightInterval(intervals);
    }
}
