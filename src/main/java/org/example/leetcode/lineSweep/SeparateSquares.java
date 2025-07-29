package org.example.leetcode.lineSweep;
import java.util.*;
public class SeparateSquares {
    public double separateSquares(int[][] squares) {
        long totalArea = 0;
        List<int[]> events = new ArrayList<>();

        for (int[] square : squares) {
            int x = square[0];
            int y = square[1];
            int l = square[2];
            totalArea += 1L * l * l;

            // Add open and close events
            events.add(new int[]{y, 1, l});      // Opening event
            events.add(new int[]{y + l, 0, l});  // Closing event
        }

        // Sort by y, if tie: opening before closing
        events.sort((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(b[1], a[1]); // Opening first if same y
        });

        long currArea = 0;
        long combinedWidth = 0;
        int prevY = 0;

        for (int i = 0; i < events.size(); i++) {
            int y = events.get(i)[0];
            int type = events.get(i)[1];
            int l = events.get(i)[2];

            long heightDiff = y - prevY;
            long areaDiff = combinedWidth * heightDiff;

            if (currArea + areaDiff >= totalArea / 2.0) {
                // Binary search within the height to find the exact line
                double optimalHeight = (totalArea / 2.0 - currArea) / combinedWidth;
                return prevY + optimalHeight;
            }

            if (type == 1) {
                combinedWidth += l;
            } else {
                combinedWidth -= l;
            }

            currArea += areaDiff;
            prevY = y;
        }

        return -1; // Shouldn't happen for valid inputs
    }
}
