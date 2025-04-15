package org.example.leetcode.dp;

import java.util.Arrays;

public class MaximumSum {
    public static void main(String[] args) {
        int nums[] = {11,-10,-11,8,7,-6,9,4,11,6,5,0};
        System.out.println(maximumSum(nums));
    }
    public static int maximumSum(int[] arr) {
        int len = arr.length;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        int ans = 0;
        for(int i=0;i<len;i++){
            sum += arr[i];
            min = Math.min(min,Math.min(0,arr[i]));
            ans = Math.max(ans,sum - min);
            if((sum - min)  <= 0){
                sum = 0;
                min = 0;
            }
        }
        if(ans == 0) {
            return Arrays.stream(arr).max().orElse(arr[0]);
        }else
            return ans;
    }
}
