package org.example.leetcode.dsStore;
import java.util.*;
public class GreaterTree {
     private int n;
     private Node data;
     public GreaterTree(int arr[]){
         this.n = arr.length;
         this.data = build(arr,0,n-1);
     }
     public Node build(int nums[],int start, int end){
         if(start == end){
            return new Node(start,end,Arrays.asList(nums[start]),null,null);
         }
         int mid = start+(end-start)/2;
         Node left = build(nums,start,mid);
         Node right = build(nums,mid+1,end);
         List<Integer> merged = new ArrayList<>();
         merged.addAll(left.sortedValue);
         merged.addAll(right.sortedValue);
         Collections.sort(merged);
         return new Node(start,end,merged,left,right);
     }
     public int serach(int start, int end, int target){
        return searchHelper(data,start,end,target);
     }
     private int searchHelper(Node node,int start,int end,int x){
        if(node == null || node.start > end || node.end < start)
            return 0;
        if(node.start >= start && node.end <= end){
            var ls = node.sortedValue;
            return upperBound(ls,x);
        }
        return searchHelper(node.left,start,end,x) + searchHelper(node.right,start,end,x);
     }
     public void update(int index, int value){
        updateHelper(data,index,value);
     }
     private void updateHelper(Node node,int index, int value){
         if(node.start == node.end && node.end == index){
             node.sortedValue.clear();
             node.sortedValue.add(value);
             return;
         }
         int mid = node.start + (node.end-node.start)/2;
         if(index <= mid)
             updateHelper(node.left,index,value);
         else
             updateHelper(node.right,index,value);

        node.sortedValue = merge(node.left.sortedValue,node.right.sortedValue);
     }
     private List<Integer> merge(List<Integer> lsa,List<Integer> lsb){
         if(lsa == null  || lsb == null)
             return lsa == null ? lsa : lsb;
         List<Integer> merged = new ArrayList<>();
         int i=0,j=0;
         while(i<lsa.size() && j <lsb.size()){
             if(lsa.get(i) <= lsb.get(j))
                 merged.add(lsa.get(i++));
             else
                 merged.add(lsb.get(j++));
         }
         while(i<lsa.size()) merged.add(lsa.get(i++));
         while(j<lsb.size()) merged.add(lsb.get(j++));
         return merged;
     }
     private int upperBound(List<Integer> ls, int target){
         int pos = Collections.binarySearch(ls,target);
         if(pos < 0){
             pos = -(pos+1);
             return ls.size() - pos;
         }
         while(pos < ls.size() && ls.get(pos) <= target)
             pos++;
         return ls.size() - pos;
     }
     private int upperBoundNaked(List<Integer> ls, int target){
         int left = 0,right = ls.size();
         while(left < right){
             int mid = left + (right-left)/2;
             if(ls.get(mid) <= target)
                 left = mid + 1;
             else
                 right = mid;
         }
         return ls.size() - left;
     }
     private static class Node{
         int start,end;
         List<Integer> sortedValue;
         Node left,right;
         public Node(int start,int end,List<Integer> sortedValue,Node left,Node right){
             this.start = start;
             this.end = end;
             this.sortedValue = sortedValue;
             this.left = left;
             this.right = right;
         }
     }

    public static void main(String[] args) {
         int nums[] = {1,3,4,3,6,2,7,19};
         GreaterTree gt = new GreaterTree(nums);
         int ans  = gt.serach(0,2,3);
        System.out.println(ans);
    }
}
