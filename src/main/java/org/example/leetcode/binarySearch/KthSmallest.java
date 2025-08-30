package org.example.leetcode.binarySearch;

import java.util.Collections;
import java.util.PriorityQueue;

public class KthSmallest {
    public static int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                pq.add(matrix[i][j]);
                while(pq.size() > k)
                    pq.poll();
            }
        }
        return pq.peek();
    }
    public static boolean kSmallCheck(int matrix[][], int mid, int k,int n){
        int row = n-1;
        int col = 0;
        int count = 0;
        while(row >=0 && col < n){
            if(matrix[row][col] <= mid){
                col++;
                count += row+1;
            }else
                row--;
        }
        return count >= k;
    }
    public static int kthSmallestOptimized(int matrix[][], int k){
        int low = matrix[0][0];
        int high = matrix[matrix.length-1][matrix[0].length];
        int n = matrix.length;
        while(low<high){
            int mid = low + (high-low)/2;
            if(kSmallCheck(matrix,mid,k,n)){
                high = mid;
            }else{
                low = mid+1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        System.out.println(kthSmallest(matrix,5));
    }
}
