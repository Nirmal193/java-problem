package org.example.leetcode.lineSweep;
import java.util.*;
public class isRectangleCover {
    public static boolean isRectangleCover(int[][] rectangles) {
        Set<String> set = new HashSet<>();
        int len = rectangles.length;
        long area = 0;
        for (int[] rect : rectangles) {
            String[] keys = {
                    rect[0] + "," + rect[1],
                    rect[0] + "," + rect[3],
                    rect[2] + "," + rect[1],
                    rect[2] + "," + rect[3],
            };
            for (String key : keys) {
                if (!set.add(key)) {
                    set.remove(key);
                }
            }
            area += 1L * (rect[2] - rect[0]) * (rect[3] - rect[1]);
        }
        List<String> points = new ArrayList<>();

        if(points.size() == 0)
            return false;
        int leftSum = Integer.MAX_VALUE;
        int rightSum = Integer.MIN_VALUE;
        int x1=0,y1=0,x2=0,y2=0;
        for(String point : set){
            String[] s = point.split(",");
            int x = Integer.parseInt(s[0]);
            int y = Integer.parseInt(s[1]);
            int sum = x+y;
            if(sum < leftSum){
                x1 = x;
                y1 = y;
                leftSum = sum;
            }
            if(sum > rightSum){
                rightSum = sum;
                x2 = x;
                y2 = y;
            }
        }
        long total = area(x1,y1,x2,y2);
        if(area != total)
            return false;
        return true;
    }
    private static Long area(int x1,int y1, int x2,int y2){
        return 1L * (x2 - x1) * (y2 - y1);
    }

    public static void main(String[] args) {
        int[][] rectangles = {
                {1, 1, 3, 3},
                {3, 1, 4, 2},
                {3, 2, 4, 4},
                {1, 3, 2, 4},
                {2, 3, 3, 4}
        };

// Use it in your method
        boolean result = isRectangleCover(rectangles);
        System.out.println(result);
    }
}
