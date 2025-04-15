package org.example.ds_store;

import java.util.*;

public class Binary_heap<T> {
    private final List<Node<T>> store;
    private final Map<T,Integer> map;
    public Binary_heap(){
        store = new ArrayList<>();
        map = new HashMap<>();
    }
    private static class Node<T>{
        T vertex;
        int key;
        public Node(T vertex, int key){
            this.vertex = vertex;
            this.key = key;
        }
    }
    public boolean contains(T vertex) {
        return map.containsKey(vertex);
    }

    public int getKey(T vertex) {
        return store.get(map.get(vertex)).key;
    }
    public void insert(T vertex,int key){
        Node<T> node = new Node<>(vertex,key);
        store.add(node);
        map.put(node.vertex,store.size()-1);
        decreaseKey(vertex,key);
    }
    public T extractMin(){
        if(store.isEmpty()){
            throw new IllegalStateException("The heap is empty");
        }
        T minVertex = store.get(0).vertex;
        // swap the last element with the first
        swap(0,store.size()-1);
        store.remove(store.size()-1);
        map.remove(minVertex);

        // update the index of the last element
        if(!store.isEmpty())
            minHeapify(0);
        return minVertex;
    }
    public void decreaseKey(T vertex,int newKey){
        int index = map.get(vertex);
        if(newKey > store.get(index).key)
            return;
        store.get(index).key = newKey;
        while(index > 0 && store.get(getParent(index)).key > store.get(index).key){
            swap(index,getParent(index));
            index = getParent(index);
        }
    }
    private void minHeapify(int index){
        int smallest = index;
        int left = getLeftChild(index);
        int right = getRightChild(index);
        if(left < store.size() && store.get(smallest).key > store.get(left).key)
            smallest = left;
        if(right < store.size() && store.get(smallest).key > store.get(right).key)
            smallest = right;
        if(smallest != index){
            swap(smallest,index);
            minHeapify(smallest);
        }
    }

    private void swap(int ind1, int ind2){
        Node<T> temp = store.get(ind2);
        store.set(ind2,store.get(ind1));
        store.set(ind1,temp);
        map.put(store.get(ind1).vertex,ind1);
        map.put(store.get(ind2).vertex,ind2);
    }
    public boolean isEmpty(){
        return store.isEmpty();
    }
    private int getParent(int index){
        return (index-1)/2;
    }
    private int getLeftChild(int index){
        return 2*index + 1;
    }
    private int getRightChild(int index){
        return 2*index + 2;
    }
}
