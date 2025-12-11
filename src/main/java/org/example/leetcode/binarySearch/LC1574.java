package org.example.leetcode.binarySearch;

import java.util.LinkedList;
import java.util.*;
public class LC1574 {
    public int findLengthOfShortestSubarray(int[] arr) {
        Stack<Integer> stk = new Stack<>();
        int left = 0,right = arr.length-1;
        while(right > 0 && arr[right] >= arr[right-1])
            right--;
        int ans  = right;
        while(left<right && (left == 0 || arr[left-1] <= arr[left])){
            while(right < arr.length && arr[left] > arr[right])
                right++;
            ans = Math.min(ans,right-left-1);
            left++;
        }
        return ans;
    }
}
