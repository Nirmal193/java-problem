package org.example.leetcode.binarySearch;

public class lc2972 {
    public long incremovableSubarrayCount(int[] nums) {
        int n = nums.length;
        int left = 0;
        while (left + 1 < n && nums[left] < nums[left + 1]) {
            left++;
        }

        if (left == n - 1) {
            return n * (n + 1) / 2;
        }

        int right = n - 1;
        while (right - 1 >= 0 && nums[right - 1] < nums[right]) {
            right--;
        }
        int ans = 0;
        int j = right;
        for (int i = 0; i <= left; i++) {
            while (j < n && (i > 0 && nums[i - 1] >= nums[j])) {
                j++;
            }
            ans += (n - j);
        }
        ans += (n - right) * (right);

        return ans;
    }
}
