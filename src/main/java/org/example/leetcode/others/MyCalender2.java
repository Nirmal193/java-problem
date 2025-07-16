package org.example.leetcode.others;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class MyCalender2 {
    public TreeMap<Integer,Integer> timeline;
    public MyCalender2(){
        timeline = new TreeMap<>();
    }
    public boolean book(int startTime, int endTime) {
        timeline.put(startTime,timeline.getOrDefault(startTime,0) + 1);
        timeline.put(endTime,timeline.getOrDefault(endTime,0) - 1);
        int active = 0;
        for(Map.Entry<Integer,Integer> event : timeline.entrySet()){
            active += event.getValue();
            if(active > 2){
                timeline.put(startTime,timeline.get(startTime)-1);
                if(timeline.get(startTime) == 0) timeline.remove(startTime);
                timeline.put(endTime,timeline.get(endTime)+1);
                if(timeline.get(endTime) == 0) timeline.remove(endTime);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MyCalender2 mc = new MyCalender2();
        mc.book(24,40);
        mc.book(43,50);
        mc.book(27,43);
        mc.book(5,21);
        mc.book(30,40);
        mc.book(14,19);

    }
}
