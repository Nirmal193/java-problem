package org.example.leetcode.graph;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class LC1562 {
    public int findLatestStep(int[] arr, int m) {
        int n = arr.length;
        int dp[] = new int[n+2];
        int count = 0;
        int step = -1;
        for(int i=0;i<n;i++){
            int index = arr[i];
            int left = dp[index-1];
            int right = dp[index+1];
            int total = 1 + left + right;
            if(m == left)
                count--;
            if(m == right)
                count--;
            if(m == total)
                count++;
            if(count > 0)
                step = i+1;
            dp[index] = total;
            dp[index - left] = total;
            dp[index + right] = total;
        }
        return step;
    }

    public static void main(String[] args) {
        int arr[] = {8,16,10,4,7,5,1,11,14,12,13,6,3,2,9,17,15,19,18};
        LC1562 lc = new LC1562();
        System.out.println(lc.findLatestStep(arr,7));
    }

}
