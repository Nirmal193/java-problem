package org.example.leetcode.others;

import java.util.*;

public class MyCalendar {
    private TreeMap<Integer, Integer> calendar;

    public MyCalendar() {
        calendar = new TreeMap<>();
    }
    public boolean book(int startTime, int endTime){
        Integer prev = calendar.floorKey(startTime);
        if(prev != null && calendar.get(prev) > startTime) return false;
        Integer next = calendar.ceilingKey(startTime);
        if(next != null && calendar.get(next) < endTime) return false;

        calendar.put(startTime,endTime);
        return true;
    }
}
