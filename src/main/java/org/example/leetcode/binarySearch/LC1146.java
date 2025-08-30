package org.example.leetcode.binarySearch;
import java.util.*;
import java.util.stream.IntStream;

public class LC1146 {
    Map<Integer,TreeMap<Integer,Integer>> snapshot;
    int currentVersion;
    public LC1146(int length) {
        snapshot = new HashMap<>();
        currentVersion = 0;
        IntStream.range(0,length).forEach(i->snapshot.put(i,new TreeMap<>()));
    }

    public void set(int index, int val) {
        snapshot.get(index).put(currentVersion,val);
    }

    public int snap() {
        return currentVersion++;
    }

    public int get(int index, int snap_id) {
        Map.Entry<Integer, Integer> entry = snapshot.get(index).floorEntry(snap_id);
        return entry == null ? -1 : entry.getValue();
    }
}
