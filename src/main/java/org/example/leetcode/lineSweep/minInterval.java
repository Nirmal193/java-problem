package org.example.leetcode.lineSweep;

import java.util.*;
import java.util.LinkedList;

public class minInterval {
    public static int[] minInterval(int[][] intervals, int[] queries) {
        List<Integer[]> interval = new LinkedList<>();
        int len = intervals.length;
        for(int i=0;i<len;i++){
            interval.add(new Integer[]{intervals[i][0],-(intervals[i][1]-intervals[i][0]+1)});
            interval.add(new Integer[]{intervals[i][1],(intervals[i][1]-intervals[i][0]+1)});
        }
        for(int i=0;i<queries.length;i++){
            interval.add(new Integer[]{queries[i],0,i});
        }
        Collections.sort(interval, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] a, Integer[] b) {
                if(a[0] == b[0]){
                    return a[1] - b[1];
                }else
                    return a[0] - b[0];
            }
        });
        TreeMap<Integer,Integer> tmap = new TreeMap<>();
        int ans[] = new int[queries.length];
        for(int i=0;i<interval.size();i++){
            Integer intr[] = interval.get(i);
            if(intr[1] < 0)
                tmap.compute(-intr[1],(key,val)-> val == null ? 1 : val+1);
            else if(intr[1] > 0)
                tmap.compute(intr[1],(key,val) -> val == 1 ? null : val-1);
            else{
                if(tmap.isEmpty())
                    ans[intr[2]] = -1;
                else
                    ans[intr[2]] = tmap.firstKey();
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 4}, {2, 5}, {3, 6}};
        int queries[] = {2, 2, 5};
        int ans[] = minInterval(intervals,queries);
        System.out.println(Arrays.toString(ans));
    }
}
