package org.example.leetcode.binarySearch;

import java.util.Arrays;

public class LC1482 {
    /*
    Input: bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
    Output: 12
    Explanation: We need 2 bouquets each should have 3 flowers.
    Here is the garden after the 7 and 12 days:
    After day 7: [x, x, x, x, _, x, x]
    We can make one bouquet of the first three flowers that bloomed. We cannot make another bouquet from the last three flowers that bloomed because they are not adjacent.
    After day 12: [x, x, x, x, x, x, x]
    It is obvious that we can make two bouquets in different ways.
     */
    public int minDays(int[] bloomDay, int m, int k) {
        int lo = 0, hi = Arrays.stream(bloomDay).max().getAsInt()+1;
        while(lo < hi){
            int mid = lo + (hi-lo)/2;
            if(isPossible(bloomDay,m,k,mid))
                hi = mid;
            else
                lo = mid+1;
        }
        if(isPossible(bloomDay,m,k,lo))
            return lo;
        return -1;
    }
    public boolean isPossible(int bloomDay[],int m, int k, int days){
        int len = bloomDay.length;
        int count = 0,start  = 0;
        for(int i=0;i<len;i++){
            if(bloomDay[i] <= days)
                start++;
            else
                start = 0;
            if(start == k){
                count++;
                start = 0;
            }
        }
        return count >= m;
    }

    public static void main(String[] args) {
        LC1482 lc = new LC1482();
        int bloomDay[] = {7,7,7,7,12,7,7};
        System.out.println(lc.minDays(bloomDay,2,3));
    }
}
