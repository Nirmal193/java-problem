package org.example.revesion.trees;

public class FenwickTree {
    int n;
    int bit[];
    public FenwickTree(int n){
        this.n = n;
        bit = new int[n+1];
    }
    public void update(int i, int val){
        for (; i <= n; i += i & -i)  bit[i] += val;
    }
    public int query(int i){
        int sum = 0;
        for(;i>0;i -= i& -i) sum += bit[i];
        return sum;
    }
    public int rangeQuery(int l,int r){
        return query(r) - query(l-1);
    }

    public static void main(String[] args) {
        FenwickTree ftee = new FenwickTree(4);
        int arr[] = {4,5,7,9};
        for(int i=0;i<4;i++)
            ftee.update(i+1,arr[i]);
        System.out.println(ftee.rangeQuery(1,3));
    }
}
