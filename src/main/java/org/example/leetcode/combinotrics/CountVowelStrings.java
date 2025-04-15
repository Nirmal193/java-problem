package org.example.leetcode.combinotrics;

public class CountVowelStrings {
    public static void main(String[] args) {
        System.out.println(countVowelStrings(2));
        System.out.println(solveButtomUpApproach(2));
    }
    public static int countVowelStrings(int n) {
        return solve(0,0,n);
    }
    public static int solve(int index,int count,int n) {
        if (index >= 5) {
            return 0;
        }
        if (n == count)
            return 1;
        int ans = 0;
        for (int i = index; i < 5; i++) {
            ans += solve(i, count + 1, n);
        }
        return ans;
    }
    public static int solveButtomUpApproach(int n){
        int[][] dp = new int[5][n + 1];
        for (int i = 0; i < 5; i++) {
            dp[i][n] = 1;
        }
        for (int count = n - 1; count >= 0; count--) {
            for (int index = 4; index >= 0; index--) {
                int ans = 0;
                for (int nextIndex = index; nextIndex < 5; nextIndex++) {
                    ans += dp[nextIndex][count + 1];
                }
                dp[index][count] = ans;
            }
        }
        return dp[0][0];
    }
}
