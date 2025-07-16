package org.example.leetcode.others;

import java.util.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class countSmaller {
    int bit[];
    int n;
    public countSmaller(int n){
        this.n = n;
        bit = new int[n+1];
    }
    public void update(int i, int delta){
        while(i <= n){
            bit[i]++;
            i += (i & -i);
        }
    }
    public int query(int i){
        int ans = 0;
        while(i > 0){
            ans += bit[i];
            i -= (i & -i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int nums[] = {5,2,6,1};
        TreeSet<Integer> set = new TreeSet<>();
        for(Integer num : nums){
            set.add(num);
        }
        Map<Integer,Integer> rank = new HashMap<>();
        int idx = 1;
        for (int val : set) rank.put(val, idx++);
        countSmaller cs = new countSmaller(nums.length);
        int len = nums.length;
        List<Integer> ans =  new ArrayList<>();
        for(int i=len-1;i>=0;i--){
            ans.add(cs.query(rank.get(nums[i])));
            cs.update(rank.get(nums[i]),1);
        }
        Collections.reverse(ans);
    }
}
