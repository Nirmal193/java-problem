package org.example.leetcode.lineSweep;
import java.util.*;
public class SkyLine {
    public static List<List<Integer>> getSkyline(int[][] buildings) {
        List<int[]> heights = new ArrayList<>();
        for (int[] building : buildings) {
            heights.add(new int[]{building[0], -building[2]});
            heights.add(new int[]{building[1], building[2]});
        }

        heights.sort((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        List<List<Integer>> result = new ArrayList<>();
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        heightMap.put(0, 1);
        int prevHeight = 0;

        for (int[] height : heights) {
            if (height[1] < 0) {
                heightMap.compute(-height[1], (key, value) ->
                        value == null ? 1 : value + 1);
            } else {
                heightMap.compute(height[1], (key, value) ->
                        value == 1 ? null : value - 1);
            }

            int currentHeight = heightMap.lastKey();
            if (currentHeight != prevHeight) {
                result.add(List.of(height[0], currentHeight));
                prevHeight = currentHeight;
            }
        }

        return result;
    }
    public static List<List<Integer>> getSkyLines(int[][] buildings) {
        List<int[]> events = new ArrayList<>();

        // Create all start and end events
        for (int[] b : buildings) {
            int L = b[0], R = b[1], H = b[2];
            events.add(new int[]{L, -H, R}); // start of building
            events.add(new int[]{R, 0, 0});  // end marker
        }

        // Sort by x coordinate, and height to resolve tie-breaks
        events.sort((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];           // sort by x
            return a[1] - b[1];                             // sort by height (start before end)
        });

        // Max heap: [height, endX]
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (a, b) -> b[0] - a[0]  // sort by height descending
        );
        // Initial ground level
        maxHeap.add(new int[]{0, Integer.MAX_VALUE});

        List<List<Integer>> result = new ArrayList<>();
        int prevHeight = 0;

        for (int[] event : events) {
            int x = event[0], negH = event[1], R = event[2];

            int z = maxHeap.peek()[1];
            // Remove past buildings
            while (!maxHeap.isEmpty() && maxHeap.peek()[1] <= x) {
                maxHeap.poll();
            }

            // Add new building
            if (negH != 0) {
                maxHeap.add(new int[]{-negH, R});
            }

            int currHeight = maxHeap.peek()[0];
            if (currHeight != prevHeight) {
                result.add(Arrays.asList(x, currHeight));
                prevHeight = currHeight;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] buildings = {{0,2,3},{2,5,3}};
        List<List<Integer>> ns = getSkyLines(buildings);
        System.out.println(ns.toString());
    }
}
