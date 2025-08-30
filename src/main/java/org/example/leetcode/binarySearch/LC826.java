package org.example.leetcode.binarySearch;

import java.util.*;

public class LC826 {
    public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int ans = 0;
        TreeSet<Integer> set = new TreeSet<>();
        TreeMap<Integer,Integer> map = new TreeMap<>();
        int len = difficulty.length;
        Pair diff[] = new Pair[len];
        for (int i=0;i<len;i++){
            diff[i] = new Pair(difficulty[i],profit[i]);
        }
        Arrays.sort(diff, new Comparator<Pair>() {
            @Override
            public int compare(Pair t1, Pair t2) {
                return Integer.compare(t1.x,t2.x);
            }
        });
        for(int i=0;i<len;i++){
            set.add(diff[i].y);
            map.put(diff[i].x,Math.max(diff[i].y,set.last()));
        }
        set.clear();
        for(int i=0;i<worker.length;i++){
            Map.Entry<Integer, Integer> entry = map.floorEntry(worker[i]);
            if(entry != null){
                ans += entry.getValue();
            }
        }
        return ans;
    }
    public static class Pair{
        int x,y;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        int diff[] = {68,35,52,47,86};
        int profit[] = {67,17,1,81,3};
        int worker[] = {92,10,85,84,82};
        int ans= maxProfitAssignment(diff,profit,worker);
        System.out.println(ans);
    }
}
