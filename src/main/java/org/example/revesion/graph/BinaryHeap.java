package org.example.revesion.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class BinaryHeap<T> {
    private List<HeapNode<T>> heap;
    private Map<T, Integer> vertexToIndex;

    private static class HeapNode<T> {
        T vertex;
        int key;

        HeapNode(T vertex, int key) {
            this.vertex = vertex;
            this.key = key;
        }
    }

    public BinaryHeap() {
        heap = new ArrayList<>();
        vertexToIndex = new HashMap<>();
    }

    public void insert(T vertex, int key) {
        HeapNode<T> node = new HeapNode<>(vertex, key);
        heap.add(node);
        vertexToIndex.put(vertex, heap.size() - 1);
        decreaseKey(vertex, key);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public T extractMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        T minVertex = heap.get(0).vertex;
        int lastIdx = heap.size() - 1;
        swap(0, lastIdx);

        heap.remove(lastIdx);
        vertexToIndex.remove(minVertex);

        if (!heap.isEmpty()) {
            minHeapify(0);
        }

        return minVertex;
    }

    public void decreaseKey(T vertex, int newKey) {
        int idx = vertexToIndex.get(vertex);
        if (newKey > heap.get(idx).key) {
            return;
        }

        heap.get(idx).key = newKey;
        while (idx > 0 && heap.get(parent(idx)).key > heap.get(idx).key) {
            swap(idx, parent(idx));
            idx = parent(idx);
        }
    }

    public boolean contains(T vertex) {
        return vertexToIndex.containsKey(vertex);
    }

    public int getKey(T vertex) {
        int idx = vertexToIndex.get(vertex);
        return heap.get(idx).key;
    }

    private void minHeapify(int idx) {
        int smallest = idx;
        int left = leftChild(idx);
        int right = rightChild(idx);

        if (left < heap.size() && heap.get(left).key < heap.get(smallest).key) {
            smallest = left;
        }
        if (right < heap.size() && heap.get(right).key < heap.get(smallest).key) {
            smallest = right;
        }

        if (smallest != idx) {
            swap(idx, smallest);
            minHeapify(smallest);
        }
    }

    private void swap(int i, int j) {
        HeapNode<T> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);

        vertexToIndex.put(heap.get(i).vertex, i);
        vertexToIndex.put(heap.get(j).vertex, j);
    }

    private int parent(int idx) {
        return (idx - 1) / 2;
    }

    private int leftChild(int idx) {
        return 2 * idx + 1;
    }

    private int rightChild(int idx) {
        return 2 * idx + 2;
    }
}
