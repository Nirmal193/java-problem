package org.example.leetcode.ebay;

public class LC1937 {
    public static long maxPoints(int[][] points) {
        int n = points[0].length;
        int dp[] = new int[n*n];
        for(int i=0;i<points.length;i++){
            for(int l=0;l<n;l++){
                for(int j= 0;j<n;j++){
                    if(i!=0)
                        dp[(n *l)+j] = points[i][l] + dp[(n * l)+j] - Math.abs(l-j);
                    else
                        dp[(n *l)+j] = points[i][l];
                }
            }
        }
        int ans = Integer.MIN_VALUE;
        for(int i=0;i<dp.length;i++){
            ans = Math.max(dp[i],ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        int nums[][] =  {{1,2,3},{1,5,1},{3,1,1}};
        System.out.println(maxPoints(nums));
    }
}
