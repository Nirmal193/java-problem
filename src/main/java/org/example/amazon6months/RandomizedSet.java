package org.example.amazon6months;

import java.util.*;

public class RandomizedSet {
    ArrayList<Integer> ds;
    Map<Integer,Integer> map;
    public RandomizedSet() {
        ds = new ArrayList<>();
        map = new HashMap<>();
    }

    public boolean insert(int val) {
        if(map.containsKey(val))
            return false;
        ds.addLast(val);
        map.put(val,ds.size()-1);
        return true;
    }

    public boolean remove(int val) {
       if(!map.containsKey(val))
           return false;

        int ind = map.get(val);
        int last = ds.getLast();

        ds.set(ind, last);
        ds.removeLast();

        map.put(last, ind);
        map.remove(val);

        return true;
    }

    public int getRandom() {
        Random ran = new Random();
        int idx = ran.nextInt(ds.size());
        return ds.get(idx);
    }
}
