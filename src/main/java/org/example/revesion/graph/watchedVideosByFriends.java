package org.example.revesion.graph;

import java.util.*;
import java.util.stream.Collectors;

public class watchedVideosByFriends {
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos,
                                               int[][] friends, int id, int level) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(id);
        visited.add(id);
        while(level > 0){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int node = queue.poll();
                int friend[] = friends[node];
                for(int j=0;j<friend.length;j++){
                    if(visited.contains(friend[j]) == false){
                        queue.add(friend[j]);
                        visited.add(friend[j]);
                    }
                }
            }
            level--;
        }
        Map<String, Integer> videoCount = new HashMap<>();
        while(queue.isEmpty()){
            int node = queue.poll();
            List<String> videos = watchedVideos.get(node);
            for(String video : videos){
                videoCount.put(video, videoCount.getOrDefault(video, 0) + 1);
            }
        }
        List<String> result = new ArrayList<>(videoCount.keySet());
        result =result.stream().sorted((a,b) ->{
            if(videoCount.get(a) == videoCount.get(b)){
                return a.compareTo(b);
            }
            return videoCount.get(a) - videoCount.get(b);
        }).collect(Collectors.toList());
        return result;
    }

    public static void main(String[] args) {
        // watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 1
        List<List<String>> watchedVideos = new ArrayList<>();
        watchedVideos.add(Arrays.asList("A", "B"));
        watchedVideos.add(Arrays.asList("C"));
        watchedVideos.add(Arrays.asList("B", "C"));
        watchedVideos.add(Arrays.asList("D"));
        int[][] friends = {
                {1, 2},
                {0, 3},
                {0, 3},
                {1, 2}
        };
        int id = 0;
        int level = 2;
        watchedVideosByFriends obj = new watchedVideosByFriends();
        obj.watchedVideosByFriends(watchedVideos,friends,id, level);
    }
}
