package org.example.leetcode.binarySearch;

public class LC275 {
    public static int hIndex(int[] citations) {
        int ans = -1, len = citations.length;
        int lo = 0, hi = len-1;
        while(lo<=hi){
            int mid = lo + (hi-lo)/2;
            int rem = len - mid;
            if(citations[mid] >= rem){
                ans = mid;
                hi = mid-1;
            }else
                lo = mid+1;
        }
        return citations[ans];
    }

    public static void main(String[] args) {
        int arr[] = {0,1,3,5,6};
        System.out.println(hIndex(arr));
    }
}
