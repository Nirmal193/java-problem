package org.example.leetcode.binarySearch;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LC786 {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int l = arr.length;
        int len = (l*(l-1))/2;
        Node frac[] = new Node[len];
        int count = 0;
        for(int i=0;i<l;i++){
            for(int j = i+1;j<l;j++){
                frac[count++] = new Node((double) arr[i]/arr[j],arr[i],arr[j]);
            }
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node a, Node b) {
                return Double.compare(a.fraction, b.fraction);
            }
        });
        int sz = count - k + 1;
        for(int i=0;i<count;i++){
            pq.add(frac[i]);
            if(pq.size() > sz)
                pq.poll();
        }
        return new int[]{pq.peek().a,pq.peek().b};
    }
    public static class Node{
        double fraction;
        int a;
        int b;
        public Node(double fraction, int a,int b){
            this.fraction = fraction;
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) {
        LC786 lc = new LC786();
        int arr[] = {1,2,3,5};
        System.out.println(Arrays.toString(lc.kthSmallestPrimeFraction(arr,3)));
    }
}
