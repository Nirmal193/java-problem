package org.example.leetcode.binarySearch;

public class LC540 {
    public static int singleNonDuplicate(int[] nums) {
        int hi = nums.length;
        int low = 0;
        while (low < hi){
            int mid = low + (hi-low)/2;
            int nextMid = mid%2==0 ? mid+1 : mid-1;
            if(nextMid == nums.length)
                return nums[mid];
            if(nums[mid]==nums[nextMid]){
                low = mid+1;
            }else{
                hi = mid;
            }
        }
        return nums[low];
    }

    public static void main(String[] args) {
        int nums[] = {1,1,2};
        System.out.println(singleNonDuplicate(nums));
    }
}
