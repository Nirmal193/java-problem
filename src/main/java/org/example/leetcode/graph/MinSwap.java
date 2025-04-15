package org.example.leetcode.graph;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinSwap {
    public static void main(String[] args) {

    }
    public static int solve(int arr[]){
        int len = arr.length;
        int sortedArr[] = new int[len];
        System.arraycopy(arr,0,sortedArr,0,len);
        Arrays.sort(sortedArr);
        Map<Integer,Integer> position = new HashMap<>();
        for(int i=0;i<len;i++){
            position.put(sortedArr[i],i);
        }
         boolean visited[] = new boolean[len];
        int ans = 0;
        for(int i=0;i<len;i++){
            if(visited[i] || position.get(arr[i]) == i)
                continue;
            int curr = i;
            int cycleSize = 0;
            while(!visited[curr]){
                visited[curr] = true;
                curr = position.get(arr[curr]);
                cycleSize++;
            }
            if(cycleSize > 1)
                ans+=cycleSize-1;
        }
        return ans;
    }
}
