package org.example.leetcode.binarySearch;

import java.util.*;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class LC1348TweetCount {
    Map<String, TreeMap<Integer, Integer>> tweets;
    public LC1348TweetCount() {
        tweets = new HashMap<>();
    }

    public void recordTweet(String tweetName, int time) {
        tweets.putIfAbsent(tweetName, new TreeMap<>());
        TreeMap<Integer, Integer> map = tweets.get(tweetName);
        map.put(time, map.getOrDefault(time, 0) + 1);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        List<Integer> chunks = new ArrayList<>();
        int interval = 0;
        switch (freq) {
            case "minute": interval = 60; break;
            case "hour":   interval = 3600; break;
            case "day":    interval = 86400; break;
        }
        int n = ((endTime - startTime) / interval) + 1;
        List<Integer> result = new ArrayList<>(Collections.nCopies(n, 0));

        if (!tweets.containsKey(tweetName)) return result;

        TreeMap<Integer, Integer> map = tweets.get(tweetName);
        for (Map.Entry<Integer, Integer> entry : map.subMap(startTime, true, endTime, true).entrySet()) {
            int time = entry.getKey();
            int count = entry.getValue();
            int index = (time - startTime) / interval;
            result.set(index, result.get(index) + count);
        }
        return result;
    }
}
