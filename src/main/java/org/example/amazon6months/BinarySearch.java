package org.example.amazon6months;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        System.out.println(Arrays.binarySearch(nums,3));
        System.out.println(Arrays.binarySearch(nums,4));
        System.out.println(Arrays.binarySearch(nums,-1));
    }
}
