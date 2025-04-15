package org.example.amazon6months;

import java.util.LinkedList;
import java.util.Queue;

public class snakesAndLadders {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int board1d[] = new int[n*n];
        int index = 0;
        boolean leftToRight = true;
        for (int i = n - 1; i >= 0; i--) {
            if (leftToRight) {
                for (int j = 0; j < n; j++) {
                    board1d[index++] = board[i][j];
                }
            } else {
                for (int j = n - 1; j >= 0; j--) {
                    board1d[index++] = board[i][j];
                }
            }
            leftToRight = !leftToRight;
        }
        return BFSUtil(board1d,0,n);
    }
    public int BFSUtil(int board1D[],int start,int n){
        Queue<Integer> queue = new LinkedList<>();
        int n2 = n*n;
        boolean[] visited = new boolean[n2];
        queue.offer(0);
        visited[0] = true;
        int rolls = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                if (current == n2 - 1 && visited[n2-1]) {
                    return rolls;
                }

                for (int diceRoll = 1; diceRoll <= 6; diceRoll++) {
                    int next = current + diceRoll;
                    if (next < n2 && !visited[next]) {
                        visited[next] = true;
                        if (board1D[next] != -1) {
                            next = board1D[next] - 1;
                        }
                        queue.offer(next);
                    }
                }
            }
            rolls++;
        }

        return -1;
    }
}
