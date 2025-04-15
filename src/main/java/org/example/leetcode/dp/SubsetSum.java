package org.example.leetcode.dp;

public class SubsetSum {
    public static void main(String[] args) {
        int nums[] = {3, 1, 4,8,6,7};
        System.out.println(subsetSum(nums,11));
    }
    public static boolean subSetSum(int nums[], int target,int index){
        if(target == 0)
            return true;
        if(index ==  nums.length || target < 0){
            return false;
        }
        return subSetSum(nums,target-nums[index],index+1) ||
                subSetSum(nums,target,index+1);
    }
    public static boolean subsetSum(int nums[], int target){
        int len = nums.length;
        boolean dp[][] = new boolean [len+1][target+1];
        for(int i=0;i<=len;i++)
            dp[i][0] = true;
        for(int i=1;i<=len;i++){
            for(int j=1;j<=target;j++){
                dp[i][j] = dp[i-1][j];
                if(j >= nums[i-1]){
                    dp[i][j] = dp[i][j] || dp[i-1][j - nums[i-1]];
                }
            }
        }
        return dp[len][target];
    }
}
