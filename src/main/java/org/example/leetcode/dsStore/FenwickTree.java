package org.example.leetcode.dsStore;

public class FenwickTree {
    int bit[];
    int n;
    public FenwickTree(int n){
        this.n = n;
        bit = new int[n+1];
    }
    public void update(int i, int delta){
        while(i <= n){
            bit[i] += delta;
            i += (i & -i);
        }
    }
    public int query(int i){
        int sum = 0;
        while(i > 0){
            sum += bit[i];
            i -= (i & -i);
        }
        return sum;
    }
    public int queryRange(int l, int r){
        return query(r) - query(l-1);
    }

    public static void main(String[] args) {
        int nums[] = {1,2,3,4};
        int fnums[] = new int[nums.length+1];
        System.arraycopy(nums,0,fnums,1,nums.length);
        FenwickTree ftr = new FenwickTree(nums.length);
        for(int i=1;i<fnums.length;i++){
            ftr.update(i,fnums[i]);
        }
    }
}
