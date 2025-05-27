package org.example.leetcode.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.*;

public class getLastMoment {
    public int getLastMoment(int n, int[] left, int[] right) {
        int rightMin = Arrays.stream(right).min().orElse(Integer.MAX_VALUE);
        return 0;
    }
    public static void solve(int nums[]){
        int number = 90;
        String num = String.valueOf(number);
        System.out.println(num);
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare( String s1, String s2) {
                int in1 = 0;
                int in2 = 0;
                int len1 = s1.length();
                int len2 = s2.length();
                while(in1 < s1.length() || in2 < s2.length()){
                    if(s1.charAt(in1%len1) > s2.charAt(in2%len2)){
                        return 1;
                    }else if(s1.charAt(in1%len1) < s2.charAt(in2%len2)) {
                        return -1;
                    }
                    in1++;
                    in2++;
                }
                return 0;
            }
        };
        String numbers[] = {"3","39","34","5","9"};
        Arrays.sort(numbers, comparator);
        StringBuilder sb = new StringBuilder();
        for(int i=numbers.length-1;i>=0;i--)
            sb.append(numbers[i]);
        System.out.println(sb.toString());
    }
    public int maximumGap(int[] nums) {
        int len = nums.length;
        if(len <= 1)
            return 0;
        int min = Arrays.stream(nums).min().orElse(-1);
        int max = Arrays.stream(nums).max().orElse(-1);
        int numOfGaps =  len-1;
        Bucket buckets[] = new Bucket[numOfGaps];
        for(int i=0;i<numOfGaps;i++)
            buckets[i] = new Bucket();
        double gapLength = (max-min)/(len-1);
        for(int num: nums){
            if (num == min || num == max) continue;
            int index = (int)((num - min) / gapLength);
            if (index == numOfGaps) index--;
            buckets[index].update(num);
        }
        int ans = 0;
        int prev = min;
        for(int i=0;i<buckets.length;i++){
            if(buckets[i].isEmpty)
                continue;
            ans = Math.max(ans,buckets[i].min - prev);
            prev = buckets[i].max;
        }
        return Math.max(ans, max - prev);
    }
    private static class Bucket{
        public int max;
        public int min;
        public boolean isEmpty;
        public Bucket(){
            isEmpty = true;
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
        }
        public void update(int num){
            max = Math.max(max,num);
            min = Math.min(min,num);
            isEmpty = false;
        }

    }
    public static void main(String[] args) {
       getLastMoment gt = new getLastMoment();
       int nums[] = {1,10000000};
        System.out.println(gt.maximumGap(nums));
    }
}
