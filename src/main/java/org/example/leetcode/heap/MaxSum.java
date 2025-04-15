package org.example.leetcode.heap;

import java.util.*;

public class MaxSum {
    public static void main(String[] args) {
        int[] array1 = {7, 10, 3, 3, 7, 7, 0};
        int[] array2 = {5, 5, 9, 2, 10, 5, 2};

        int[][] twoDArray = new int[2][array1.length];

        twoDArray[0] = array1;
        twoDArray[1] = array2;

        int limit[] = new int[] {3,7};
        System.out.println(maxSum(twoDArray,limit,7));
    }
    public static long maxSum(int[][] grid, int[] limits, int k) {
        int n = grid.length, m = grid[0].length;
        long sum = 0;
        PriorityQueue<int[]> result = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                result.add(new int[]{grid[i][j], i});
            }
        }
        int[] caught = new int[n];
        while (k > 0 && !result.isEmpty())
        {
            int[] limited = result.poll();
            int value = limited[0], row = limited[1];

            if (caught[row] < limits[row])
            {
                sum += value;
                caught[row]++;
                k--;
            }
        }

        return sum;
    }
}
