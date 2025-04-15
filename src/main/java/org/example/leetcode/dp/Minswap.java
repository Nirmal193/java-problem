package org.example.leetcode.dp;

import java.util.Arrays;

public class Minswap {
    public static void main(String[] args) {
        int num1[] = {1,3,5,4};
        int num2[] = {1,2,3,7};
        System.out.println(minSwap(num1,num2));
    }
    public static int minSwap(int[] nums1, int[] nums2) {
        int memo[][] = new int[nums1.length][2];
        for (int[] row : memo) Arrays.fill(row, -1);
        return solve(nums1,nums2,1,false,memo);
    }
    public static int solve(int[] num1, int num2[], int index,boolean swapped,int memo[][]){
        if(index == num1.length)
            return 0;
        int sec = swapped ? 0 : 1;
        if(memo[index][sec] != -1)
            return memo[index][sec];
        int ans  = Integer.MAX_VALUE;
        int prev1 = swapped ? num2[index - 1] : num1[index - 1];
        int prev2 = swapped ? num1[index - 1] : num2[index - 1];

        if (num1[index] > prev1 && num2[index] > prev2) {
            ans = solve(num1, num2, index + 1, false,memo);
        }
        if (num1[index] > prev2 && num2[index] > prev1) {
            ans = Math.min(ans, 1 + solve(num1, num2, index + 1, true,memo));
        }
        memo[index][sec] = ans;
        return ans;
    }
    public static void swap(int[] arr1, int[] arr2, int index) {
        int temp = arr1[index];
        arr1[index] = arr2[index];
        arr2[index] = temp;
    }

}
