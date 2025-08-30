package org.example.leetcode.binarySearch;


import java.util.Arrays;

public class LC1300 {
    public int findBestValue(int[] arr, int target) {
        int lo = 0, hi = Arrays.stream(arr).max().getAsInt();
        int ans = lo;
        int diff = Integer.MAX_VALUE;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int currSum = solve(arr, mid);
            int currDiff = Math.abs(currSum - target);

            if (currDiff < diff || (currDiff == diff && mid < ans)) {
                ans = mid;
                diff = currDiff;
            }

            if (currSum < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }
    public int solve(int arr[], int value){
        int sum = 0;
        for(int val : arr){
            if(val > value)
                sum += value;
            else
                sum += val;
        }
        return sum;
    }
}
