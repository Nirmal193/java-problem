package org.example.leetcode.graph;
import java.util.*;
public class FindAllReciepies {
    public List<String> findAllRecipes(String[] recipes,
                                       List<List<String>> ingredients, String[] supplies) {
        int size = recipes.length;
        Set<String> recipesSet = new HashSet<>(Arrays.asList(recipes));
        Set<String> suppliesSet = new HashSet<>(Arrays.asList(supplies));
        Map<String,Integer> indegree  = new HashMap<>();
        for(int i=0;i<size;i++){
            indegree.put(recipes[i], 0);
        }
        for(int i=0;i<recipes.length;i++){
            for(String ing : ingredients.get(i)){
                if(!suppliesSet.contains(ing) && recipesSet.contains(ing) == false){
                    indegree.replace(recipes[i] , indegree.get(recipes[i]) + 1);
                }
            }
        }
        Queue<String> queue = new LinkedList<>();
        for(String recipe : recipesSet){
            if(indegree.get(recipe) == 0){
                queue.add(recipe);
            }
        }
        List<String> ans = new ArrayList<>();
        Set<String> visisted = new HashSet<>();
        while(queue.isEmpty() == false){
            String curr = queue.poll();
            if(checkReachability(visisted,suppliesSet,recipesSet, ingredients.get(Arrays.asList(recipes).indexOf(curr)), 0)){
                ans.add(curr);
                indegree.replace(curr, indegree.get(curr) - 1);
                if(indegree.get(curr) == 0){
                    queue.add(curr);
                }
            }
        }
        return ans;
    }
    public boolean checkReachability(Set<String> visited,Set<String> supplies,Set<String> recepies,List<String> required, int index){
        if(index == required.size())
            return true;
        if(visited.contains(required.get(index)) || supplies.contains(required.get(index))){
            boolean isPossible = checkReachability(visited, supplies, recepies, required, index+1);
            if(isPossible && recepies.contains(required.get(index)))
                visited.add(required.get(index));
            return isPossible;
        }else
            return false;
    }

}
