package org.example.leetcode.ebay;

import java.util.Arrays;

public class LC416 {
    public static boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int half = sum/2;
        if(sum%2!=0)
            return false;
        boolean dp[] = new boolean[half+1];
        dp[0] = true;
        for(int j = 0; j < nums.length; j++){
            for(int i = half; i >= nums[j]; i--){
                dp[i] = dp[i] | dp[i - nums[j]];
            }
        }
        return dp[half];
    }

    public static void main(String[] args) {
        int nums[] = {1,2,5};
        System.out.println(canPartition(nums));
    }
}
