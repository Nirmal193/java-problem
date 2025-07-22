package org.example.leetcode.others;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class SummaryRanges {
    TreeMap<Integer,Integer> timeline;
    public SummaryRanges() {
      timeline = new TreeMap<>();
    }

    public void addNum(int value) {
        Integer prev = timeline.floorKey(value);
        Integer next = timeline.ceilingKey(value);
        boolean flag = false;
        if(prev != null && (value - timeline.get(prev)) == 1){
            timeline.put(prev,value);
            flag = true;
        }if(next != null && (next - value) == 1){
            if(prev != null && next - timeline.get(prev) == 1){
                timeline.put(prev,timeline.get(next));
                timeline.remove(next);
                return;
            }
            timeline.put(value,timeline.get(next));
            timeline.remove(next);
            return;
        }
        if(!flag)
        timeline.put(value,value);
    }

    public int[][] getIntervals() {
        int ans[][] = new int[timeline.size()][2];
        int count = 0;
        for(Map.Entry<Integer,Integer> entry : timeline.entrySet()){
            ans[count++] = new int[]{entry.getKey(),entry.getValue()};
        }
        return ans;
    }

    public static void main(String[] args) {
        SummaryRanges sm = new SummaryRanges();
        sm.addNum(1);
        System.out.println(Arrays.toString(sm.getIntervals()));
        sm.addNum(2);
        System.out.println(Arrays.toString(sm.getIntervals()));
    }
}
