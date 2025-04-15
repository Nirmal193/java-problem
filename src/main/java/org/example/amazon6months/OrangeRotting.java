package org.example.amazon6months;

import java.util.*;

public class OrangeRotting {
    public static void main(String[] args) {
        Set<AbstractMap.SimpleEntry<Integer,Integer>> set = new HashSet<>();
        set.add(new AbstractMap.SimpleEntry<>(1,2));
        System.out.println(set.contains(new AbstractMap.SimpleEntry<>(1,2)));
    }
    int row[] = {0,-1,0,1};
    int col[] = {-1,0,1,0};
    public int orangesRotting(int[][] grid) {
        int lenr = grid.length;
        int lenc = grid[0].length;
        Queue<AbstractMap.SimpleEntry<Integer,Integer>> queue = new LinkedList<>();
        Set<AbstractMap.SimpleEntry<Integer,Integer>> visited = new HashSet<>();
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++) {
                if (grid[i][j] == 2) {
                    AbstractMap.SimpleEntry<Integer, Integer> integerIntegerSimpleEntry = new AbstractMap.SimpleEntry<>(i, j);
                    visited.add(integerIntegerSimpleEntry);
                    queue.add(integerIntegerSimpleEntry);
                }
            }
        }
        int ans = 0;
        while(!queue.isEmpty()){
            int sz = queue.size();
            boolean flag = false;
            for(int i=0;i<sz;i++){
                AbstractMap.SimpleEntry<Integer,Integer> current = queue.poll();
                for(int j=0;j<4;j++){
                    int nr =current.getKey() + row[j];
                    int nc = current.getValue() + col[j];
                    AbstractMap.SimpleEntry<Integer, Integer> ncr = new AbstractMap.SimpleEntry<>(nr, nc);
                    if(nr>=0 && nc>=0 && nr<lenr && nc<lenc && grid[nr][nc] == 1 && !visited.contains(ncr)){
                        queue.add(ncr);
                        visited.add(ncr);
                        grid[nr][nc] = 2;
                        flag = true;
                    }
                }
            }
            if(flag)
                ans++;
        }
        for(int i=0;i<lenr;i++){
            for(int j =0;j<lenc;j++){
                if(grid[i][j] == 1)
                    return -1;
            }
        }
        return ans;
    }
}
