package org.example.leetcode.dsStore;

public class GreatTree {
    int data[];
    int n;
    public GreatTree(int nums[]){
        this.n = nums.length;
        data = new int[4 * nums.length];
        build(0,0,n-1,nums);
    }
    public void build(int node,int start, int end, int nums[]){
        if(start == end){
            data[node] = nums[start];
            return;
        }
        int mid = start + (end-start)/2;
        int leftChild = node * 2 + 1;
        int rightChild = node * 2 + 2;
        build(leftChild,start,mid,nums);
        build(rightChild,mid+1,end,nums);
        data[node] = Math.max(data[leftChild],data[rightChild]);
    }
    public int query(int start, int end){
        return queryHelper(0,0,n-1,start,end);
    }
    public int queryHelper(int node, int left, int right, int start, int end){
        if(left > end || right < start)
            return Integer.MIN_VALUE;
        if(left>= start && right <= end){
            return data[node];
        }
        int mid = left + (right-left)/2;
        int leftValue = queryHelper(2 * node + 1,left, mid,start,end);
        int rightValue = queryHelper(2 * node + 2,mid+1, right,start,end);
        return Math.max(leftValue,rightValue);
    }
    public void update(int index,int value){
        updateHelper(0,0,n-1,index,value);
    }
    private void propagate(int node, int start, int end){

    }
    private void updateHelper(int node, int start, int end , int index, int value){
        if(start == end){
            data[node] = value;
            return;
        }
        int mid = start + (end-start)/2;
        if(index <= mid)
            updateHelper(2 * node + 1, start , mid, index, value);
        else
            updateHelper(2 * node  +2,mid+1,end,index,value);
        data[node] = Math.max(data[2*node+1], data[2 * node + 2]);
    }
}
