package org.example.revesion.trees;

public class LazyPropagation {
    int n;
    int tree[],lazy[];
    public LazyPropagation(int arr[]){
        n = arr.length;
        tree = new int[4*n];
        lazy = new int[4*n];
    }
    public void build(int arr[], int node,int l, int r){
        if(l==r) tree[node] = arr[l];
        else {
            int mid = l + (r - l) / 2;
            build(arr, 2 * node + 1, l, mid);
            build(arr, 2 * node + 2, mid + 1, r);
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }
    public void propagate(int node,int start, int end){
        if(lazy[node] !=0){
            tree[node] += (end - start + 1) * lazy[node];
            if(start != end) {
                lazy[2 * node + 1] += lazy[node];
                lazy[2 * node + 2] += lazy[node];
            }
            lazy[node]=0;
        }
    }
    public void update_range(int node, int l, int r,int ql,int qr, int val){
        propagate(node, l,r);
        if(l > qr || r < ql) return;
        if(l>=ql && r <=qr){
            lazy[node] += val;
            propagate(node,l,r);
            return;
        }
        int mid = l + (r-l)/2;
        update_range(2 * node + 1,l,mid,ql,qr,val);
        update_range(2 * node + 2,mid+1,r,ql,qr,val);
        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }
    public int query_range(int node, int l, int r, int ql, int qr){
        if(l > qr || r < ql) return 0;
        propagate(node,l,r);
        if(l >= ql && r <= qr) return tree[node];
        int mid = l + (r-l)/2;
        return query_range(2 * node + 1,l,mid,ql,qr) + query_range(2 * node + 2,mid+1,r,ql,qr);
    }
}
