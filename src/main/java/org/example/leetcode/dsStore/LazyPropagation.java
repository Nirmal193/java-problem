package org.example.leetcode.dsStore;


public class LazyPropagation {
    private int[] tree;
    private int[] lazy;
    private int n;

    public LazyPropagation(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];
        lazy = new int[4 * n];
        buildTree(arr, 0, 0, n - 1);
    }

    private void buildTree(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        buildTree(arr, 2 * node + 1, start, mid);
        buildTree(arr, 2 * node + 2, mid + 1, end);
        tree[node] = Math.max(tree[2 * node + 1], tree[2 * node + 2]);
    }

    private void propagate(int node, int start, int end) {
        if (lazy[node] != 0) {
            tree[node] += lazy[node];
            if (start != end) {
                lazy[2 * node + 1] += lazy[node];
                lazy[2 * node + 2] += lazy[node];
            }
            lazy[node] = 0;
        }
    }

    public void updateRange(int l, int r, int val) {
        updateRangeUtil(0, 0, n - 1, l, r, val);
    }

   /**
    * tionUpdates a range of values in the segment tree with lazy propaga
    * @param node Current node in the segment tree
    * @param start Start index of the current segment
    * @param end End index of the current segment
    * @param l Left boundary of the range to update
    * @param r Right boundary of the range to update
    * @param val Value to add to all elements in the range [l,r]
    */
   private void updateRangeUtil(int node, int start, int end, int l, int r, int val) {
       // First propagate any pending lazy updates to children
       propagate(node, start, end);

       // If current segment is outside the update range, return
       if (start > end || start > r || end < l) return;

       // If current segment is completely within update range
       if (start >= l && end <= r) {
           // Update current node
           tree[node] += val;
           // If not leaf node, mark children for lazy update
           if (start != end) {
               lazy[2 * node + 1] += val;
               lazy[2 * node + 2] += val;
           }
           return;
       }

       // If partial overlap, recursively update left and right children
       int mid = (start + end) / 2;
       updateRangeUtil(2 * node + 1, start, mid, l, r, val);
       updateRangeUtil(2 * node + 2, mid + 1, end, l, r, val);
       // Update current node to maintain max property
       tree[node] = Math.max(tree[2 * node + 1], tree[2 * node + 2]);
   }

    public int queryRange(int l, int r) {
        return queryRangeUtil(0, 0, n - 1, l, r);
    }

    private int queryRangeUtil(int node, int start, int end, int l, int r) {
        if (start > end || start > r || end < l) return Integer.MIN_VALUE;

        propagate(node, start, end);

        if (start >= l && end <= r) return tree[node];

        int mid = (start + end) / 2;
        return Math.max(queryRangeUtil(2 * node + 1, start, mid, l, r),
                       queryRangeUtil(2 * node + 2, mid + 1, end, l, r));
    }
}
