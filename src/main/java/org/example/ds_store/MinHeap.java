package org.example.ds_store;
import java.util.*;
import java.util.function.BiPredicate;

public class MinHeap<T> {
    private List<T> store;
    private BiPredicate<T,T> biPredicate;
    public MinHeap ( BiPredicate<T,T> biPredicate){
        store = new ArrayList<>();
        this.biPredicate = biPredicate;
    }
    private int getParent(int index){
        return (index-1)/2;
    }
    private int getLeftChild(int index){
        return index*2+1;
    }
    private int getRightChild(int index){
        return index*2+2;
    }
    private boolean compare(T value1, T value2){
        return biPredicate.test(value1,value2);
    }
    public T peek(){
        if(store.size() == 0)
            throw new IllegalStateException("The heap is empty");
        return store.get(0);
    }
    public boolean isEmpty(){
        return store.isEmpty();
    }
    public Integer size(){
        return store.size();
    }
    public void insert(T value){
        store.addLast(value);
        int index = store.size()-1;
        heapifyUp(index);
    }
    private void heapifyUp(int index){
        if(store.size() <=1)
            return;
        while(index !=0 && compare(store.get(index),store.get(getParent(index)))){
            swap(index,getParent(index));
            index = getParent(index);
        }
    }
    private void heapifyDown(){
        int index = 0;
        while((getLeftChild(index) < store.size() && compare(store.get(getLeftChild(index)),store.get(index)))
                || (getRightChild(index) < store.size() && compare(store.get(getRightChild(index)),store.get(index)))){
            int swappedIndex = -1;
            if(getLeftChild(index) >= store.size()) swappedIndex = getRightChild(index);

            else if(getRightChild(index) >= store.size()) swappedIndex = getLeftChild(index);

            else
                swappedIndex = compare(store.get(getLeftChild(index)),store.get(getRightChild(index))) ?
                        getLeftChild(index) : getRightChild(index);

            if (!compare(store.get(swappedIndex), store.get(index))) break;

            swap(index,swappedIndex);
            index = swappedIndex;
        }
    }
    public T extractMin(){
        T min = store.getFirst();
        swap(0,store.size()-1);
        store.removeLast();
        heapifyDown();
        return min;
    }
    public void swap(int index1, int index2){
        T temp = store.get(index1);
        store.set(index1,store.get(index2));
        store.set(index2,temp);
    }


}
