package org.example.leetcode.binarySearch;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class LC475 {
    public static int findRadius(int[] houses, int[] heaters) {
        int max = Arrays.stream(houses).max().orElse(Integer.MAX_VALUE);
        max = Math.max(Arrays.stream(heaters).max().orElse(Integer.MAX_VALUE),max);
        int ans = Integer.MAX_VALUE;
        int low = 0,high = max;
        while(low < high){
            int mid = low + (high -low)/2;
            if(check(houses,heaters,mid)){
                high = mid;
            }else{
                low = mid+1;
            }
        }
        return low;
    }
    public static boolean check(int houses[], int heaters[], int radius){
        int ch = 0;
        for(Integer heater : heaters){
            while(ch <  houses.length && Math.abs(houses[ch] - heater) <= radius){
                ch++;
            }
        }
        return ch == houses.length;
    }

    public static void main(String[] args) {
        int heater[] = {10};
        int house[] = {1,5};
        System.out.println(findRadius(house,heater));
    }
}
