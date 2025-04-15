package org.example.leetcode.dp;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.Queue;

public class maxDistance {
    public int maxDistance(int[][] grid) {
        int ans = 0;
        Queue<Integer[]> queue = new LinkedList<>();
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if(grid[i][j] == 1)
                    queue.add(new Integer[]{i,j,0});
            }
        }
        boolean visisted[][] = new boolean[grid.length][grid[0].length];
        int rc[] = new int[]{0, 0, -1, 1};
        int rr[] = new int[]{-1, 1, 0, 0};
        while(!queue.isEmpty()){
            int sz = queue.size();
            for(int i=0;i<sz;i++){
                Integer[] current = queue.poll();
                for(int j=0;j<4;j++){
                    int nr = rr[j] + current[0];
                    int nc = rc[j] + current[1];
                    ans = Math.max(ans,current[2]);
                    if(nr >=0 && nr < grid.length && nc >= 0 && nc <grid[0].length &&
                            grid[nr][nc] == 0 && visisted[nr][nc] == false){
                        queue.add(new Integer[]{nr,nc,current[2] + 1});
                        visisted[nr][nc] = true;
                    }
                }
            }
        }
        return ans;
    }
}
