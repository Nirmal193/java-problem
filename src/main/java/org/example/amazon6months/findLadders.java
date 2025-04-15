package org.example.amazon6months;

import java.util.*;
import java.util.stream.Collectors;

public class findLadders {
    public static void main(String[] args) {
        List<String> ls = new ArrayList<>();
        ls.add("hot");
        ls.add("dot");
        ls.add("dog");
        ls.add("lot");
        ls.add("log");
        ls.add("cog");
    }
    public static List<List<String>> findLadderss(String beginWord, String endWord, List<String> wordList) {
       List<List<String>> ans = new ArrayList<>();
       Queue<List<String>> queue = new LinkedList<>();
       queue.offer(new ArrayList<>(Arrays.asList(beginWord)));
       while(!queue.isEmpty()){
           List<String> path = queue.poll();
           String current = path.getLast();
           if(!ans.isEmpty() && ans.getLast().size() < path.size())
               continue;
           if(current.equals(endWord))
               ans.add(path);

           for(int i=0;i<wordList.size();i++){
               if(!path.contains(wordList.get(i)) && diffCheck(wordList.get(i).toCharArray(),current.toCharArray())){
                   List<String> newPath = new ArrayList<>(path);
                   newPath.add(wordList.get(i));
                   queue.offer(newPath);
               }
           }
       }
        int size = ans.stream().map(x -> x.size()).min(Comparator.comparingInt(i->i)).orElse(0);
        ans = ans.stream().filter(x->x.size() == size).collect(Collectors.toList());
        return ans;
    }
    public static boolean diffCheck(char[] s1,char[] s2){
        if(s1.length != s2.length) return false;
        int count = 0;
        for(int i = 0; i < s1.length; i++){
            if(s1[i] != s2[i]) count++;
            if(count > 1) return false;
        }
        return count == 1;
    }
    public static List<List<String>> findLaddersTLE(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        Set<String> visited = new HashSet<>();
        Queue<List<String>> queue = new LinkedList<>();
        List<List<String>> ans = new ArrayList<>();
        queue.add(new ArrayList<>(Arrays.asList(beginWord)));
        visited.add(beginWord);
        boolean foundEnd = false;
        while(!queue.isEmpty() && foundEnd == false){
            Set<String> levelVisited = new HashSet<>();
            int sz = queue.size();
            for(int z = 0;z<sz;z++){
                List<String> path = queue.poll();
                String current = path.getLast();
                List<String> nghbrs = getNeighbours(current,wordSet);
                for(String ngh : nghbrs){
                    if(visited.contains(ngh))
                        continue;
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(ngh);
                    if(ngh.equals(endWord)) {
                        ans.add(newPath);
                        foundEnd = true;
                    }else{
                        queue.offer(newPath);
                        levelVisited.add(ngh);
                    }
                }
            }
            visited.addAll(levelVisited);
        }
        int size = ans.stream().map(x -> x.size()).min(Comparator.comparingInt(i->i)).orElse(0);
        ans = ans.stream().filter(x->x.size() == size).collect(Collectors.toList());
        return ans;
    }
    public static List<String> getNeighbours(String word,Set<String> wordSet){
        char[] charArr = word.toCharArray();
        List<String> neighbours = new ArrayList<>();
        for(int i=0;i<charArr.length;i++){
            char orginal = charArr[i];
            for(char c='a';c<='z';c++){
                if(charArr[i] == c)
                    continue;
                charArr[i] = c;
                if(wordSet.contains(new String(charArr)))
                    neighbours.add(new String(charArr));
            }
            charArr[i] = orginal;
        }
        return neighbours;
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        Map<String, Set<String>> reverse = new HashMap<>(); // reverse graph start from endWord
        Set<String> wordSet = new HashSet<>(wordList); // remove the duplicate words
        wordSet.remove(beginWord); // remove the first word to avoid cycle path
        Queue<String> queue = new LinkedList<>(); // store current layer nodes
        queue.add(beginWord); // first layer has only beginWord
        Set<String> nextLevel = new HashSet<>(); // store nextLayer nodes
        boolean findEnd = false; // find endWord flag
        while (!queue.isEmpty()) { // traverse current layer nodes
            String word = queue.remove();
            for (String next : wordSet) {
                if (isLadder(word, next)) { // is ladder words
                    // construct the reverse graph from endWord
                    Set<String> reverseLadders = reverse.computeIfAbsent(next, k -> new HashSet<>());
                    reverseLadders.add(word);
                    if (endWord.equals(next)) {
                        findEnd = true;
                    }
                    nextLevel.add(next); // store next layer nodes
                }
            }
            if (queue.isEmpty()) { // when current layer is all visited
                if (findEnd) break; // if find the endWord, then break the while loop
                queue.addAll(nextLevel); // add next layer nodes to queue
                wordSet.removeAll(nextLevel); // remove all next layer nodes in wordSet
                nextLevel.clear();
            }
        }
        if (!findEnd) return ans; // if can't reach endWord from startWord, then return ans.
        Set<String> path = new LinkedHashSet<>();
        path.add(endWord);
        // traverse reverse graph from endWord to beginWord
        findPath(endWord, beginWord, reverse, ans, path);
        return ans;
    }


    private void findPath(String endWord, String beginWord, Map<String, Set<String>> graph,
                          List<List<String>> ans, Set<String> path) {
        Set<String> next = graph.get(endWord);
        if (next == null) return;
        for (String word : next) {
            path.add(word);
            if (beginWord.equals(word)) {
                List<String> shortestPath = new ArrayList<>(path);
                Collections.reverse(shortestPath); // reverse words in shortest path
                ans.add(shortestPath); // add the shortest path to ans.
            } else {
                findPath(word, beginWord, graph, ans, path);
            }
            path.remove(word);
        }
    }

    private boolean isLadder(String s, String t) {
        if (s.length() != t.length()) return false;
        int diffCount = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i)) diffCount++;
            if (diffCount > 1) return false;
        }
        return diffCount == 1;
    }

}
