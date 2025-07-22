package org.example.leetcode.others;

import java.util.*;
class RangeModule {
    TreeMap<Integer,Integer> timeline;
    public RangeModule() {
        this.timeline = new TreeMap<>();
    }

    public void addRange(int left, int right) {
        Integer start = timeline.floorKey(left);
        if(start == null || timeline.get(start) < left)
            start = timeline.ceilingKey(left);
        Integer newLeft = left;
        Integer newRight = right;
        while(start != null && start <= newRight && timeline.get(start) >= newLeft){
            newLeft = Math.min(start,newLeft);
            newRight = Math.max(timeline.get(start),newRight);
            timeline.remove(start);
            start = timeline.higherKey(start);
        }
        timeline.put(newLeft,newRight);
    }

    public boolean queryRange(int left, int right) {
        Integer start = timeline.floorKey(left);
        if(start == null || timeline.get(start) < right)
            return false;
        return true;
    }

    public void removeRange(int left, int right) {
        if (left >= right) return;

        Integer start = timeline.floorKey(left);
        if (start == null) {
            start = timeline.ceilingKey(left);
        }

        List<Integer> keysToRemove = new ArrayList<>();
        List<int[]> newIntervals = new ArrayList<>();

        while (start != null && start < right) {
            int end = timeline.get(start);

            if (end <= left) {
                start = timeline.higherKey(start);
                continue;
            }

            if (start < left) {
                newIntervals.add(new int[]{start, left});
            }

            if (end > right) {
                newIntervals.add(new int[]{right, end});
            }

            keysToRemove.add(start);
            start = timeline.higherKey(start);
        }

        for (int key : keysToRemove) {
            timeline.remove(key);
        }

        for (int[] interval : newIntervals) {
            timeline.put(interval[0], interval[1]);
        }
    }
//[[],[10,20],[14,16],[10,14],[13,15],[16,17]]
    public static void main(String[] args) {
        RangeModule rm = new RangeModule();
        rm.addRange(10,20);
        rm.removeRange(14,16);
        rm.queryRange(10,14);
        rm.queryRange(13,15);
        rm.queryRange(16,17);
    }
}
