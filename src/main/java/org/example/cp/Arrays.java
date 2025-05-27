package org.example.cp;
import java.util.*;
public class Arrays {
    private final int mod = (int)1e9+7;
    public static void main(String[] args) {
        int nums[] = {1,2,5,6
        };
        boolean visited[] = new boolean[nums.length];
        permutation(nums,new ArrayList<>(),visited);
    }
    public static void subArraySum(int nums[]){
        int len = nums.length;
        int best = 0;
        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++){
                int sm = 0;
                for(int k=i;k<=j;k++){
                    sm += nums[k];
                }
                best = Math.max(sm,best);
            }
        }
        System.out.println(best);
    }
    public static void subArraySum3(int nums[]){
        int len = nums.length;
        int best = 0;
        int sum = 0;


        for(int i=0;i<len;i++){
            sum = Math.max(sum+nums[i],nums[i]);
            best = Math.max(sum,best);
        }
        System.out.println(best);
    }
    public static void subArray(int nums[] , int k,ArrayList<Integer> list){
        if(k==nums.length){
            System.out.println(list);
            return;
        }
        subArray(nums,k+1,list);
        list.add(nums[k]);
        subArray(nums,k+1,list);
        list.removeLast();
    }
    public static void permutation(int nums[],ArrayList<Integer> list,boolean visited[]){
        if(list.size() == nums.length) {
            System.out.println(list);
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(visited[i])
                continue;
            visited[i] = true;
            list.add(nums[i]);
            permutation(nums,list,visited);
            visited[i] = false;
            list.removeLast();
        }
    }

}
