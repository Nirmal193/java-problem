package org.example.leetcode.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LargestInteger {
    public static void main(String[] args) {
        int nums[] = new int[]{3,9,2,1,7};
        System.out.println(largestInteger(nums,3));
    }
    public static int largestInteger(int[] nums, int k) {
        int len = nums.length;
        if(k==1 ){
            return Arrays.stream(nums).boxed().collect(Collectors
                            .groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet().stream().filter(entry -> entry.getValue() == 1)
                    .map(x -> x.getKey()).max((a, b) -> Integer.compare(a, b)).orElse(-1);
        }
        if(k==len){
            return Arrays.stream(nums).boxed().max(Integer::compare).orElse(-1);
        }
        boolean start = true;
        boolean end = true;
        for(int i=1;i<len-1;i++){
            if(nums[i] == nums[0])
                start = false;
            if(nums[i] == nums[len-1])
                end = false;
        }
        if(start && end)
            return Math.max(nums[0],nums[len-1]);
        else if(start || end){
            return start ? nums[0] : nums[len-1];
        }else
            return -1;
    }
}
