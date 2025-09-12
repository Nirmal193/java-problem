package org.example.revesion.trees;

class LazySegTree {
    int n;
    long[] tree;
    long[] lazySet;
    boolean[] isSet;

    LazySegTree(int[] arr) {
        n = arr.length;
        tree = new long[4 * n];
        lazySet = new long[4 * n];
        isSet = new boolean[4 * n];
        build(arr, 0, 0, n - 1);
    }

    void build(int[] arr, int node, int l, int r) {
        if (l == r) {
            tree[node] = arr[l];
            return;
        }
        int mid = (l + r) / 2;
        build(arr, 2 * node + 1, l, mid);
        build(arr, 2 * node + 2, mid + 1, r);
        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }

    void applySet(int node, int l, int r, long val) {
        tree[node] = (r - l + 1) * val;
        lazySet[node] = val;
        isSet[node] = true;
    }

    void push(int node, int l, int r) {
        if (isSet[node]) {
            int mid = (l + r) / 2;
            applySet(2 * node + 1, l, mid, lazySet[node]);
            applySet(2 * node + 2, mid + 1, r, lazySet[node]);
            isSet[node] = false;
        }
    }

    void updateSet(int node, int l, int r, int ql, int qr, long val) {
        if (ql > r || qr < l) return;
        if (ql <= l && r <= qr) {
            applySet(node, l, r, val);
            return;
        }
        push(node, l, r);
        int mid = (l + r) / 2;
        updateSet(2 * node + 1, l, mid, ql, qr, val);
        updateSet(2 * node + 2, mid + 1, r, ql, qr, val);
        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }

    long query(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return 0;
        if (ql <= l && r <= qr) return tree[node];
        push(node, l, r);
        int mid = (l + r) / 2;
        return query(2 * node + 1, l, mid, ql, qr) +
                query(2 * node + 2, mid + 1, r, ql, qr);
    }
}

