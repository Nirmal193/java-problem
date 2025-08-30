package org.example.leetcode.binarySearch;

import javax.swing.plaf.IconUIResource;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collectors;

public class LC532 {
    public int findPairs(int[] nums, int k) {
        long duplicate = Arrays.stream(nums).boxed()
                .collect(Collectors.groupingBy(x->x,Collectors.counting()))
                .values().stream().filter(count -> count > 1).count();
        nums = Arrays.stream(nums).sorted().distinct().toArray();
        if( k == 0){
            return (int)duplicate;
        }
        int ans = 0;
        for(int i=0;i<nums.length;i++){
            if(bsearch(nums,i+1,nums[i] + k))
                ans++;
        }
        return ans;
    }
    public boolean bsearch(int nums[],int index,int target){
        int lo = index;
        int hi = nums.length - 1;
        while(lo <= hi){
            int mid = lo + (hi - lo)/2;
            if(nums[mid] > target)
                hi = mid-1;
            else if(nums[mid] < target)
                lo = mid + 1;
            else
                return true;
        }
        return false;
    }
}
