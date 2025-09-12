package org.example.Practice;

import java.util.Scanner;

public class Bs01 {
    private static Scanner scan = new Scanner(System.in);
    public static boolean check(int num){
        System.out.println("->" + num);
        String str = scan.nextLine();
        if(str.equals("yes")) return true;
        return false;
    }
    public static void search(){
        int l  = 0,r = 10;
        while(l<=r){
            int mid = l  + (r-l)/2;
            if(check(mid)) l = mid+1;
            else r = mid-1;
        }
        System.out.println("final number is : " + (l-1));
    }
    //finding fthe exact match for a number
    public static void search1(int[] nums, int target){
        int lo = 0;
        int hi = nums.length-1;
        while(lo<=hi){
            int mid = lo + (hi-lo)/2;
            if(nums[mid] < target) lo = mid+1;
            else if(nums[mid] > target) hi = mid-1;
            else {
                System.out.println(mid);
                return;
            }
        }
    }
    public static int firstccurance(int nums[], int target){
        int lo = 0, hi = nums.length-1;
        while (lo<hi){
             int mid = lo + (hi-lo)/2;
             if(nums[mid] < target) lo = mid+1;
             else hi = mid;
        }
        if(nums[lo] == target)
            System.out.println(lo);
        else
            System.out.println(-1);
        return lo;
    }
    public static int search(int nums[], int target){
        int lo = 0,hi = nums.length-1;
        while(lo<hi){
            int mid = lo + (hi-lo)/2;
            if(nums[mid] < target) lo = mid+1;
            else hi = mid;
        }
        System.out.println(lo);
        return lo;
    }
    public static int lastOccurance(int nums[],int target){
        int lo = 0;
        int hi = nums.length - 1;
        int result = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] == target) {
                result = mid;       // Found target, but keep searching right
                lo = mid + 1;       // Look for later occurrences
            } else if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        System.out.println(result);
        return result;
    }
    public static int findFirstOccurance(int nums[],int target){
        int lo =0, hi = nums.length;
        int result = -1;
        while(lo<=hi){
            int mid = lo + (hi-lo)/2;
            if(nums[mid] == target){
                result = mid;
                hi = mid-1;
            }else if(nums[mid]<target)
                lo = mid+1;
            else
                hi = mid-1;
        }
        return result;
    }

    public static void main(String[] args) {
       int nums[] = {2,2,2,2,2,5,6,6,8,8,9};
       lastOccurance(nums,2);
        System.out.println(findFirstOccurance(nums,2));
    }
}
