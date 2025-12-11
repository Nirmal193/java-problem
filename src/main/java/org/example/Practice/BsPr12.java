package org.example.Practice;

public class BsPr12 {
    public static int findPeak(int nums[]){
        int ans =  -1;
        int lo = 0, hi = nums.length-1;
        int len = nums.length-1;
        while(lo<=hi){
            int mid = lo  + (hi - lo)/2;
            if(mid > 0 && nums[mid]  >= nums[mid-1])
                lo = mid+1;
            else if(mid < len && nums[mid] >= nums[mid+1])
                hi = mid-1;
            else
                return mid;
        }
        return lo;
    }
    public static void main(String[] args) {
        int nums[] = {1,2,1,3,5,6,4};
        System.out.println(findPeak(nums));
    }
}
