package org.example.leetcode.bitManupulation;

import java.util.Arrays;
import java.util.Collections;

public class makesquare {
    public boolean makesquare(int[] matchsticks) {
        Arrays.stream(matchsticks).boxed().sorted(Collections.reverseOrder());
        long sum = Arrays.stream(matchsticks).sum();
        if(sum%4 != 0)
            return false;
        return solve(matchsticks,0,new int[4],sum/4);
    }
    public boolean solve(int[] matchSticks, int index,int[] sides,long target){
        if(index == matchSticks.length){
            return sides[0] == target && sides[1] == target && sides[2] == target && sides[3] == target;
        }
        for(int i=0;i<4;i++){
            if(sides[i] + matchSticks[index] <= target){
                sides[i] += matchSticks[index];
                solve(matchSticks, index+1, sides, target);
            }
            sides[i] -= matchSticks[index];
        }
        return false;
    }
}
