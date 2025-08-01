package org.example.leetcode.dp;

import java.util.Arrays;

public class CmbinationSum4 {
    public int combinationSum4(int[] nums, int target) {
        int dp[] = new int[target+1];
        dp[0] = 1;
        for(int i=1;i<=target;i++){
            for(int j = 0;j<nums.length;j++){
                if(nums[j] >= i)
                    dp[i] += dp[i-nums[j]];
            }
        }
        Arrays.stream(dp).forEach(System.out::println);
        return dp[target];
    }
}
