package org.example.leetcode.array;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LargestInteger {
    public static void main(String[] args) {
        int nums[] = new int[]{111311,1113};
        System.out.println(largestNumber(nums));
    }
    public static String largestNumber(int[] nums) {
        int len = nums.length;
        String numbers[] = new String[len];
        int sum = Arrays.stream(nums).sum();
        if(sum == 0)
            return "0";
        for(int i=0;i<len;i++){
            numbers[i] = String.valueOf(nums[i]);
        }
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare( String s1, String s2) {
                int in1 = 0;
                int in2 = 0;
                int len1 = s1.length();
                int len2 = s2.length();
                int len = len1+len2;
                while(in1 < len){
                    if(s1.charAt(in1%len1) > s2.charAt(in2%len2)){
                        return 1;
                    }else if(s1.charAt(in1%len1) < s2.charAt(in2%len2)) {
                        return -1;
                    }
                    in1++;
                    in2++;
                }
                return Integer.compare(len1,len2);
            }
        };
        Arrays.sort(numbers, comparator);
        StringBuilder sb = new StringBuilder();
        for(int i=numbers.length-1;i>=0;i--)
            sb.append(numbers[i]);
        return sb.toString();
    }
}
