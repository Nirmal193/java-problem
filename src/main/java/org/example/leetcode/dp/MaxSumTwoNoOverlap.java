package org.example.leetcode.dp;

public class MaxSumTwoNoOverlap {
    public static void main(String[] args) {
        int nums[] = {2,1,5,6,0,9,5,0,3,8};
        System.out.println(maxSumTwoNoOverlap(nums,4,3));
    }
    public static int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int len = nums.length;
        int prefix[] = new int[len];
        prefix[0] = nums[0];
        for(int i=1;i<len;i++)
            prefix[i] = prefix[i-1] + nums[i];
        return Math.max(solve(prefix,nums,firstLen,secondLen),solve(prefix,nums,secondLen,firstLen));
    }
    public static int solve(int prefix[], int nums[] ,int l,int r){
        int len = prefix.length;
        int lsum = 0;
        int rsum = 0;
        int ans = 0;
        for(int i=l-1;i<len-r;i++){
            lsum = 0;
            rsum = 0;
            for(int j = i;j >=(l-1);j--){
                lsum = Math.max(lsum,(prefix[j] - prefix[j-l+1] + nums[j-l+1]));
            }
            for(int k = i+1;k<=len-r;k++){
                rsum = Math.max(rsum,(prefix[k+r-1] - prefix[k] + nums[k]));
            }
            ans = Math.max(ans,lsum + rsum);
        }
        return ans;
    }
}
