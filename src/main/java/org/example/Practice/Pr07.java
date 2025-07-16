package org.example.Practice;

import java.util.SortedMap;
import java.util.TreeMap;

public class Pr07 {
    public static void main(String[] args) {
        TreeMap<Integer,Integer> tmap = new TreeMap<>();
        tmap.put(33,42);
        tmap.put(11,37);
        tmap.put(20,40);
        tmap.put(15,46);
        SortedMap<Integer,Integer> subMap = tmap.subMap(11,20);
        System.out.println(subMap.toString());
    }
}
