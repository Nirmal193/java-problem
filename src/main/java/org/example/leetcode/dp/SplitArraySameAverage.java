package org.example.leetcode.dp;

import java.util.*;

public class SplitArraySameAverage {
    public static void main(String[] args) {
        int nums[] = new int[]{3,1};
        System.out.println(solve(nums));
    }
    public static boolean splitArraySameAverage(int[] nums) {
        Map<String,Boolean> memo  = new HashMap<>();
        return solve(nums,0,0,0,0,0,memo);
    }
    public static boolean solve(int nums[], int sum1, int sum2, int num1, int num2, int index, Map<String,Boolean> memo){
        if(index == nums.length) {
            if(num1 == 0 || num2 ==0)
                return false;
            return areEqualFractions(sum1, num1, sum2, num2, 1e-9);
        }
        String key = sum1/num1 + "$" + sum2/num2;
        if(memo.containsKey(key))
            return memo.get(key);
        boolean ans =  solve(nums,sum1+nums[index],sum2,num1+1,num2,index+1,memo) ||
                solve(nums,sum1,sum2+nums[index],num1,num2+1,index+1,memo);
        memo.put(key,ans);
        return ans;
    }
    public static boolean areEqualFractions(int sum1, int num1, int sum2, int num2, double epsilon) {
        if (num1 == 0 || num2 == 0) return false;
        return Math.abs((sum1 / (double) num1) - (sum2 / (double) num2)) < epsilon;
    }
    public static boolean solve(int nums[]){
        int len = nums.length;
        List<Set<Integer>> dp = new ArrayList<>();
        for(int i=0;i<=len;i++){
            dp.add(new HashSet<>());
        }
        Integer sum = Arrays.stream(nums).sum();
        dp.get(0).add(0);
        for (int num : nums) {
            for (int k = len; k >= 1; k--) {
                Set<Integer> prevSums = dp.get(k - 1);
                Set<Integer> currSums = dp.get(k);
                for (int s : prevSums) {
                    int newSum = s + num;
                    currSums.add(newSum);
                }
            }
        }
        for(int i=1;i<len;i++){
            if((sum*i)%len != 0)
                continue;
            if(dp.get(i).contains((sum*i)/len))
                return true;
        }
        return false;
    }
}
