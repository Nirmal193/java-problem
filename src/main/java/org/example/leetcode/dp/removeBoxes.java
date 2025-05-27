package org.example.leetcode.dp;

import java.util.Arrays;

public class removeBoxes {
    public static void main(String[] args) {
        int boxes[] = {1,3,2,2,2,3,4,3,1};
        System.out.println(solve(boxes,0,boxes.length-1,0));
    }
    public static int solve(int[] boxes,int i, int j, int k){
        if(i > j)
            return 0;
        int original = i;
        while(i <= j && boxes[i] == boxes[original]){
            i++;
            k++;
        }
        int result = k * k + solve(boxes,i,j,0);
        for(int m=i;m<=j;m++){
            if(boxes[m] == boxes[original]){
                int firstHalf = solve(boxes,i,m-1,0);
                int secondHalf = solve(boxes,m,j,k);
                result = Math.max(result,firstHalf+secondHalf);
            }
        }
        return result;
    }
}
