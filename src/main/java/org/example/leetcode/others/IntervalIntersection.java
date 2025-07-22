package org.example.leetcode.others;

import java.util.*;

public class IntervalIntersection {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        TreeMap<Integer, List<Integer>> timeline = new TreeMap<>();
        for (int i = 0; i < firstList.length; i++) {
            List<Integer> list = timeline.getOrDefault(firstList[i][0], new ArrayList<>());
            list.add(1);
            timeline.put(firstList[i][0], list);
            list = timeline.getOrDefault(firstList[i][1], new ArrayList<>());
            list.add(-1);
            timeline.put(firstList[i][1], list);
        }
        for (int i = 0; i < secondList.length; i++) {
            List<Integer> list = timeline.getOrDefault(secondList[i][0], new ArrayList<>());
            list.add(1);
            timeline.put(secondList[i][0], list);
            list = timeline.getOrDefault(secondList[i][1], new ArrayList<>());
            list.add(-1);
            timeline.put(secondList[i][1], list);
        }
        List<int[]> ls = new ArrayList<>();
        int active = 0;
        boolean flag = false;
        boolean flag2 = false;
        int temp[] = new int[2];
        for (Map.Entry<Integer, List<Integer>> entry : timeline.entrySet()) {
            Collections.sort(entry.getValue(),Collections.reverseOrder());
            for (Integer val : entry.getValue()) {
                active += val;
                if (active >= 2) {
                    flag = true;
                    temp[0] = entry.getKey();
                } else if (flag && active < 2) {
                    temp[1] = entry.getKey();
                    flag = false;
                    flag2 = true;
                }
            }
            if (flag2) {
                ls.add(temp);
                flag2 = false;
            }
        }
        return ls.toArray(new int[ls.size()][]);
    }
}
