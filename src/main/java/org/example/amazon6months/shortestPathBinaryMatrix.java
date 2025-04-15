package org.example.amazon6months;

import java.util.LinkedList;
import java.util.Queue;

public class shortestPathBinaryMatrix {
    int row[] = new int[]{-1,-1,-1,0,1,1,1,0};
    int col[] = new int[]{-1,0,1,1,1,0,-1,-1};
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid[0][0] != 0)
            return -1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0});
        int n = grid.length;
        int m = grid[0].length;
        boolean visited[][]= new boolean[n][m];
        visited[0][0] = true;
        int ans = 0;
        while(!queue.isEmpty()){
            int sz = queue.size();
            for(int i=0;i<sz;i++){
                int current[] = queue.poll();
                for(int j=0;j<8;j++){
                    int nr = current[0] + row[j];
                    int nc = current[1] + col[j];
                    if(nr >= 0 && nc >= 0 && nr < grid.length && nc < grid[0].length
                            && grid[nr][nc] == 0 && visited[nr][nc] == false){
                        queue.offer(new int[]{nr,nc});
                        visited[nr][nc] = true;
                        if(nr == n-1 && nc == m-1)
                            return ans+1;
                    }
                }
            }
            ans++;
        }
        return -1;
    }

}
