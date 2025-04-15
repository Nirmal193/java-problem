package org.example.leetcode.dp;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SortIntegerByThePowerValue {
    public static void main(String[] args) {
        System.out.println(getKth(7,11,4));
    }
    public static int getKth(int lo, int hi, int k) {
        Map<Integer,Integer> memo = new HashMap<>();
        memo.put(0,0);
        memo.put(1,0);
        for(int i=lo;i<=hi;i++){
            solve(i,memo);
        }
        List<Integer> list = new ArrayList<>();
        for(int i=lo;i<=hi;i++)
            list.add(i);
        list = list.stream().sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {
                return Integer.compare(memo.get(t1),memo.get(t2));
            }
        }).collect(Collectors.toList());
        return list.get(k-1);
    }
    public static int solve( int current ,Map<Integer,Integer> memo){
        if(current <= 1)
            return 0;
        if(memo.containsKey(current))
            return memo.get(current);
        int ans = 0;
        if(current%2 == 0){
            ans = solve(current/2,memo)+1;
        }else{
            ans =  solve(current*3+1,memo)+1;
        }
        memo.put(current,ans);
        return ans;
    }
}
