package org.example.leetcode.binarySearch;
import java.util.*;
public class LC1552 {
    public int maxDistance(int[] position, int m) {
        TreeSet<Integer> tset = new TreeSet<Integer>(Arrays.stream(position).boxed().toList());
        int lo = 0, hi = tset.last() - tset.first();
        while(lo < hi){
            int mid = lo + (hi-lo)/2;
            if(isPossible(tset,mid,m)){
                lo = mid+1;
            }else
                hi = mid;
        }
        System.out.println(lo);
        return lo;
    }
    public boolean isPossible(TreeSet<Integer> treeSet, int target, int m){
        Integer start =  treeSet.first();
        int count = 1;
        while(start <= treeSet.last() && count < m){
            start+=target;
            count++;
            start = treeSet.ceiling(start);
            if(start == null)
                return false;
        }
        return start <= treeSet.last();
    }

    public static void main(String[] args) {

    }
}
