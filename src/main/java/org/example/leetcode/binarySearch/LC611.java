package org.example.leetcode.binarySearch;

import java.util.Arrays;

public class LC611 {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        for(int i=0;i<n-2;i++){
            for(int j=i+1;j<n-1;j++){
                int low = j+1, high = n;
                int sum = nums[i] + nums[j];
                while(low < high){
                    int mid = low + (high - low)/2;
                    if(nums[mid] < sum){
                        low = mid+1;
                    }else{
                        high =mid;
                    }
                }
                ans += (low - j -1);
            }
        }
        return ans;
    }
}
