package org.example.leetcode.binarySearch;

import java.util.Arrays;

public class LC825 {
    public static int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int ans = 0;
        for(int i=1;i<ages.length;i++){
            if(ages[i] == ages[i-1])
                ans++;
        }
        for(int i=1;i< ages.length;i++){
            if(ages[i] >= 14){
                int index = lowerBound(ages,(ages[i]/2 + 7),i-1);
                ans += (i - index);
            }
        }
        return ans;
    }
    public static int lowerBound(int nums[], int target,int index){
        int lo = 0;
        int high = index;
        while(lo<high){
            int mid = lo + (high-lo)/2;
            if (nums[mid] > target) {
                high = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    public static void main(String[] args) {

    }
}
