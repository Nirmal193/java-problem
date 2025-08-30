package org.example.leetcode.binarySearch;

import java.util.Stack;

public class F132Pattern {
    public boolean find132pattern(int[] nums) {
        int len = nums.length;
        Stack<Integer> stk =  new Stack<>();
        stk.push(nums[len-1]);
        for(int i = len-2;i>=0;i--){
            if(stk.isEmpty() || nums[i] > stk.peek())
                stk.push(nums[i]);
            else if(nums[i] < stk.peek()){
                while(!stk.isEmpty() && stk.peek() > nums[i]){
                    stk.pop();
                }
                if(stk.isEmpty() == false)
                    return true;
            }else
                continue;
        }
        return false;
    }
}
