package org.example.leetcode.dp;

public class MatrixChainMultiplication {
    public int matrixChainOrder(int[] p) {
        int n = p.length;
        int[][] dp = new int[n][n];
        for (int len = 2; len < n; len++) {
            for (int i = 1; i < n - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j]);
                }
            }
        }
        return dp[1][n - 1];
    }

    public static void main(String[] args) {
        MatrixChainMultiplication mcm = new MatrixChainMultiplication();
        int[] p = {1, 2, 3, 4, 3};
        System.out.println("Minimum number of multiplications is " + mcm.matrixChainOrder(p));
    }
}
