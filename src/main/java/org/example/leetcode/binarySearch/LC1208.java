package org.example.leetcode.binarySearch;

import java.util.TreeSet;
import java.util.stream.IntStream;

public class LC1208 {
    public int equalSubstring(String s, String t, int maxCost) {
        int len = s.length();
        int prefix[] = new int[len];
        for(int i=0;i<len;i++){
            prefix[i] =(Math.abs(s.charAt(i) - t.charAt(i)));
        }
        int ans  = solve(prefix,maxCost);
        return ans;
    }
    public int solve(int nums[],int target){
        int left = 0,right = 0;
        int sum = 0,ans = 0;
        while(right <  nums.length && left <= right){
            sum += nums[right];
            while(sum > target){
                sum -= nums[left++];
            }
            ans = Math.max(ans,right-left+1);
            right++;
        }
        return ans;
    }
    public int bsearch(int nums[], int target){
        int lo = 0,hi = nums.length;
        while(lo<hi){
            int mid = lo + (hi-lo)/2;
            if(nums[mid] <= target){
                lo = mid+1;
            }else{
                hi = mid;
            }
        }
        return lo-1;
    }

    public static void main(String[] args) {
        String s = "krrgw";
        String t = "zjxss";
        LC1208 lc = new LC1208();
        System.out.println(lc.equalSubstring(s,t,19));
    }
}
