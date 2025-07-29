package org.example.leetcode.lineSweep;

import java.util.*;
import java.util.Map;
import java.util.TreeMap;

public class SeparateSquares1 {
    public double separateSquares(int[][] squares) {
        TreeMap<Integer, List<Pair<Integer, Integer>>> tmap = new TreeMap<>();
        int len = squares.length;
        long totalArea = 0L;

        for (int i = 0; i < len; i++) {
            int x = squares[i][0];
            int y = squares[i][1];
            int l = squares[i][2];

            tmap.computeIfAbsent(y, k -> new ArrayList<>()).add(new Pair<>(1, l));
            tmap.computeIfAbsent(y + l, k -> new ArrayList<>()).add(new Pair<>(0, l));
            totalArea += 1L * l * l;
        }

        int prevY = 0;
        long currentArea = 0;
        int width = 0;
        double targetArea = totalArea / 2.0;

        for (Map.Entry<Integer, List<Pair<Integer, Integer>>> entry : tmap.entrySet()) {
            int y = entry.getKey();
            int diffY = y - prevY;
            long diffArea = 1L * diffY * width;

            if (currentArea + diffArea >= targetArea) {
                double additionalHeight = (targetArea - currentArea) / width;
                return prevY + additionalHeight;
            }

            for (Pair<Integer, Integer> p : entry.getValue()) {
                if (p.key == 1) {
                    width += p.value;
                } else {
                    width -= p.value;
                }
            }

            currentArea += diffArea;
            prevY = y;
        }

        return -1;
    }
    private static class Pair<T,U>{
        T key;
        U value;
        public Pair(T key,U value){
            this.key = key;
            this.value = value;
        }
    }
}
