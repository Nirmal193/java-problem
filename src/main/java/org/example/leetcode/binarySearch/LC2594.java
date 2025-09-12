package org.example.leetcode.binarySearch;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC2594 {
    public long repairCars(int[] ranks, int cars) {
        Arrays.sort(ranks);
        long lo = 1,hi = Long.MAX_VALUE;
        long ans = 0;
        while(lo <= hi){
            long mid = lo + (hi-lo)/2;
            if(isPossible(ranks,cars,mid)){
                ans = mid;
                hi = mid-1;
            }else{
                lo = mid+1;
            }
        }
        return ans;
    }
    public boolean isPossible(int ranks[],int cars, long target){
        long x = 0;
        for(int rank: ranks){
            x += (int)Math.sqrt(target/rank);
        }
        return x>=cars;
    }

    public static void main(String[] args) {
        int ranks[] = {31,31,5,19,19,10,31,18,19,3,16,20,4,16,2,25,10,16,23,18,21,23,28,6,7,29,11,11,19,20,24,19,26,12,29,29,1,14,17,26,24,7,11,28,22,14,31,12,3,19,16,26,11};
        LC2594 lc = new LC2594();
        System.out.println(lc.repairCars(ranks,736185));
    }
}
