package org.example.leetcode.dp;

import java.util.Arrays;

public class MinimizeArrayValue {
    public static void main(String[] args) {
        int nums[] = new int[]{5,1,13,15};
        System.out.println(minimizeArrayValue(nums));
    }
    public static int minimizeArrayValue(int[] nums) {
        int len = nums.length;
        int start = Arrays.stream(nums).min().orElse(nums[0]);
        int end = Arrays.stream(nums).max().orElse(nums[0]);
        int ans = -1;
        while(start < end){
            int mid = start + (end-start)/2;
            if(checkFeasibility(nums,mid) == false){
                start = mid+1;
            }else{
                ans = mid;
                end = mid;
            }
        }
        return start;
    }
    public static boolean checkFeasibility(int nums[], int num){
        int len = nums.length;
        int count = len-1;
        int rem = 0;
        while(count >= 0){
             rem = Math.max(0,(nums[count--] + rem) - num);
        }
        return rem <= 0;
    }
}
