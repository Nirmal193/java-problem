package org.example.leetcode.others;

import java.util.Map;
import java.util.TreeMap;

public class CountIntervals {
    TreeMap<Integer,Integer> timeline;
    public CountIntervals(){
        timeline = new TreeMap<>();
    }
    public void add(int left, int right) {
        Integer start = timeline.floorKey(left);
            if(start == null || timeline.get(start) < left) start = timeline.ceilingKey(left);
        int newleft = left;
        int newright = right;
        while(start != null && start <= newright && timeline.get(start) >= newleft){
            newleft = Math.min(newleft,start);
            newright = Math.max(newright,timeline.get(start));
            timeline.remove(start);
            start = timeline.higherKey(start);
        }
        timeline.put(newleft,newright);
    }

    public int count() {
        int count = 0;
        for(Map.Entry<Integer,Integer> entry : timeline.entrySet()){
            count += entry.getValue() - entry.getKey()+1;
        }
        return count;
    }

    public static void main(String[] args) {
        CountIntervals cv = new CountIntervals();
        cv.add(2,3);
        cv.add(7,10);
        cv.add(5,8);
    }
}
