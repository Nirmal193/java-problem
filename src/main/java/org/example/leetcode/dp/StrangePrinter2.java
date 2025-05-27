package org.example.leetcode.dp;
import java.util.*;
public class StrangePrinter2 {
    private final Map<Integer, List<Integer>> graph = new HashMap<>();

    public boolean isPrintable(int[][] targetGrid) {
        Map<Integer, List<Integer>> dict = new HashMap<>();
        int m = targetGrid.length;
        int n = targetGrid[0].length;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                final int i = row;
                final int j = col;
                int color = targetGrid[i][j];
                dict.computeIfAbsent(color, k -> Arrays.asList(j, j, i, i));
                List<Integer> coords = dict.get(color);
                coords.set(0, Math.min(coords.get(0), j));
                coords.set(1, Math.max(coords.get(1), j));
                coords.set(2, Math.min(coords.get(2), i));
                coords.set(3, Math.max(coords.get(3), i));
            }
        }

        for (int color : dict.keySet()) {
            List<Integer> rect = dict.get(color);
            for (int i = rect.get(2); i <= rect.get(3); i++) {
                for (int j = rect.get(0); j <= rect.get(1); j++) {
                    if (targetGrid[i][j] != color) {
                        graph.computeIfAbsent(color, k -> new ArrayList<>())
                             .add(targetGrid[i][j]);
                    }
                }
            }
        }

        Set<Integer> visited = new HashSet<>();
        Set<Integer> visiting = new HashSet<>();

        for (Integer color : dict.keySet()) {
            if (!visited.contains(color) && hasCycle(color, visiting, visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasCycle(int node, Set<Integer> visiting, Set<Integer> visited) {
        if (!graph.containsKey(node)) return false;
        visiting.add(node);
        visited.add(node);

        for (int neighbor : graph.get(node)) {
            if (visiting.contains(neighbor)) {
                return true;
            }
            if (!visited.contains(neighbor) && hasCycle(neighbor, visiting, visited)) {
                return true;
            }
        }

        visiting.remove(node);
        return false;
    }
}
