package org.example.leetcode.lineSweep;
import java.util.*;
public class LC218 {
    // the solution is correct it is just you cannot take a tree map here cause the eys will be duplicated
    public List<List<Integer>> getSkyline(int[][] buildings) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        TreeMap<Integer,Integer> tmap = new TreeMap<>();
        List<List<Integer>> ans = new ArrayList<>();
        for(int i=0;i<buildings.length;i++){
            tmap.put(buildings[i][0],buildings[i][2]);
            tmap.put(buildings[i][1],0-buildings[i][2]);
        }
        int lastMax = 0;
        pq.add(0);
        for(Map.Entry<Integer,Integer> entry : tmap.entrySet()){
            int key = entry.getKey();
            int value = entry.getValue();
            if(value > 0) pq.add(value);
            else
                pq.remove(Math.abs(value));
            if( pq.peek() != lastMax) {
                ans.add(Arrays.asList(key, pq.peek()));
                lastMax = pq.peek();
            }

        }
        return ans;
    }

    public static void main(String[] args) {
        LC218 lc = new LC218();
        int[][] array = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        List<List<Integer>> skyline = lc.getSkyline(array);
        System.out.println(skyline.toString());
    }
}
