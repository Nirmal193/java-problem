package org.example.leetcode.dp;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;

public class MinSumOfLengths {
    public static void main(String[] args) {
        int nums[] = new int[] {3,1,1,1,5,1,2,1};
        System.out.println(minSumOfLengths(nums,3));
    }
    public static int minSumOfLengths(int[] arr, int target) {
        int len = arr.length;
        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
        int currentSum = 0;
        int start = 0,end = 0;
        while(end < len){
            currentSum += arr[end++];
            while(currentSum > target){
                currentSum -= arr[start++];
            }
            if(currentSum == target){
                pq.add(end-start);
                while(pq.size() > 2)
                    pq.poll();
            }
        }
        if(pq.size()<2)
            return -1;
        return pq.poll() + pq.poll();
    }
}
