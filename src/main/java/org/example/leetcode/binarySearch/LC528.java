package org.example.leetcode.binarySearch;

import java.util.Arrays;

public class LC528 {
    private int total;
    private int currentTurn;
    private double[] prefix;
    public LC528(int[] w) {
        int len = w.length;
        prefix = new double[len];
        prefix[0] = w[0];
        currentTurn = 0;
        total = w[0];
        for(int i=1;i<len;i++){
            total += w[i];
            prefix[i] += prefix[i-1] + w[i];
        }
        prefix = Arrays.stream(prefix).map(weight -> weight/total).toArray();
    }

    public int pickIndex() {
        currentTurn++;
        if(currentTurn > prefix.length)
            currentTurn = 1;
        int target = currentTurn/total;
        return findIndex(target);
    }
    public int findIndex(double target){
        int lo = 0;
        int high = prefix.length - 1;
        while (lo < high){
            int mid = lo + (high - lo) / 2;
            if (prefix[mid] <= target){
                lo = mid + 1;
            } else {
                high = mid;
            }
        }
        return lo;
    }
}
