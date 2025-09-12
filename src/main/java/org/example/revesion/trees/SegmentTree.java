package org.example.revesion.trees;

public class SegmentTree {
    int n;
    int tree[];
    public SegmentTree(int arr[]){
        n = arr.length;
        tree = new int[n*4];
    }
    public void build(int arr[],int node,int start, int end) {
        if (start == end) tree[node] = arr[start];
        else{
            int mid = start + (end - start)/2;
            build(arr,2*node+1,start,mid);
            build(arr,2*node+2,mid+1,end);
            tree[node] = tree[2* node+ 1] + tree[2 * node + 2];
        }
    }
    public int query(int node, int l, int r, int ql, int qr){
        if(r<ql || l > qr) return 0;//for outside range
        if(l>=ql && r<=qr) return tree[node];
        int mid = l + (r-l)/2;
        int ls = query(2 * node + 1, l,mid,ql,qr);
        int rs = query(2 * node + 2,mid+1,r,ql,qr);
        return ls+rs;
    }
    public void update(int node, int l, int r,int idx, int val){
        if(l==r) tree[node] = val;
        else {
            int mid = l + (r - l) / 2;
            if (idx <= mid) update(node, l, mid, idx, val);
            else update(node, mid + 1, r, idx, val);
            tree[node] = tree[2*node+1] + tree[2*node+2];
        }
    }
}
