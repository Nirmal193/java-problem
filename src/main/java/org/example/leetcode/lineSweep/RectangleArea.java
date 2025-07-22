package org.example.leetcode.lineSweep;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class RectangleArea {
    private final int mod = (int)1e9+7;
    public int rectangleArea(int[][] rectangles) {
        TreeSet<Integer> tset = new TreeSet<>();
        for(int i=0;i<rectangles.length;i++){
            for(Integer val : rectangles[i]){
                tset.add(val);
            }
        }
        int len = rectangles.length;
        Map<Integer, Integer> rankMap = new HashMap<>();
        int count = 0;
        for(Integer val : tset){
            rankMap.put(val,count++);
        }
        Map<Integer, Integer> inverseMap = rankMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        int mines[][] = new int[201][201];
        //filling the mines
        for(int l=0;l<len;l++){
            int x1 = rankMap.get(rectangles[l][0]);
            int y1 = rankMap.get(rectangles[l][1]);
            int x2 = rankMap.get(rectangles[l][2]);
            int y2 = rankMap.get(rectangles[l][3]);
           for(int i =y1 ;i<y2;i++){
               for(int j=x1;j<x2;j++){
                   mines[i][j]++;
               }
           }
        }
        int ans  = 0;
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 200; j++) {
                if (mines[i][j] > 0) {
                    int x1 = inverseMap.get(j);
                    int x2 = inverseMap.get(j + 1);
                    int y1 = inverseMap.get(i);
                    int y2 = inverseMap.get(i + 1);
                    long dx = x2 - x1;
                    long dy = y2 - y1;
                    ans = (int)((ans + dx * dy) % mod);
                }
            }
        }
        return ans;
    }
}
