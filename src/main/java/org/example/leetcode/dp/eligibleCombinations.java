package org.example.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

public class eligibleCombinations {
    private static final int MOD = 1_000_000_007;
    public static void main(String[] args) {
        System.out.println(checkRecord(4));
    }
    public static int checkRecord(int n) {
        Map<String,Integer> memo = new HashMap<>();
        return eligibleCombinations(n,0,0,memo);
    }
    public static int eligibleCombinations(int n,int totalAbsence,int totalConsLate,Map<String,Integer> memo){
        if(totalAbsence >= 2 || totalConsLate >= 3)
            return 0;
        if(n==0)
            return 1;
        String key = n+"$"+totalAbsence+"$"+totalConsLate;
        if(memo.containsKey(key))
            return memo.get(key);
        int count = eligibleCombinations(n-1,totalAbsence,0,memo) % MOD;
        count = (count+eligibleCombinations(n-1,totalAbsence+1,0,memo) )% MOD;
        count = (count +eligibleCombinations (n-1, totalAbsence,totalConsLate+1,memo) )% MOD;
        memo.put(key,count);
        return count;
    }
}
