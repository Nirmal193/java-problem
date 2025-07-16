package org.example.Practice;

import java.util.Arrays;

public class pr04 {
    int n;
    int data[];
    public pr04(int nums[]){
        this.n = nums.length;
        this.data = new int[4*n];
        build(nums,0,0,n-1);
    }
    private void build(int nums[],int node,int start,int end){
        if(start == end){
            data[node] = nums[start];
            return;
        }
        int mid = start + (end-start)/2;
        int leftChild = 2*node+1;
        int rightChild = 2* node+2;
        build(nums,leftChild,start,mid);
        build(nums,rightChild,mid+1,end);
        data[node] = data[leftChild] + data[rightChild];
    }
    public int search(int start, int end){
        return searchHelper(0,0,n-1,start, end);
    }
    private int searchHelper(int node, int left, int right, int start, int end){
        if(start > right || end < left)
            return 0;
        if(left >=start && right <= end )
            return data[node];
        int mid = left + (right-left)/2;
        int leftValue = searchHelper(2 * node + 1, left,mid,start,end);
        int rightValue = searchHelper(2 * node + 2,mid+1,right,start,end);
        return leftValue + rightValue;
    }
    public void update(int pos,int newValue){
        updateHelper(0,0,n-1,pos,newValue);
    }
    private void updateHelper(int node,int start, int end, int pos,int newValue){
        if(start == end) {
            data[node] = newValue;
            return;
        }
        int mid = start + (end - start)/2;
        if(pos <= mid)
            updateHelper(2*node+1 , start , mid,pos,newValue);
        else
            updateHelper(2*node+2,mid+1,end,pos,newValue);
        data[node] = data[2*node+1] + data[2*node+2];
    }

    public static void main(String[] args) {
        int nums[] = {1,2,3,4};
        int len = nums.length;
        pr04 sg = new pr04(nums);
        System.out.println(Arrays.toString(sg.data));
        sg.update(2,10);
        System.out.println(Arrays.toString(sg.data));
        System.out.println(sg.search(0,2));
    }
}
