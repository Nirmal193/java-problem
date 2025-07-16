package org.example.leetcode.others;

import java.util.Arrays;
import java.util.Comparator;
import java.util.*;

public class ReconstructQueue {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people,Comparator.<int[]>comparingInt(a->a[0]).reversed()
                .thenComparingInt(a->a[1]));
        List<int[]> ls = new ArrayList<>();
        for(int[] arr : people){
            ls.add(arr[1],arr);
        }
        return ls.toArray(new int[ls.size()][]);
    }
}
