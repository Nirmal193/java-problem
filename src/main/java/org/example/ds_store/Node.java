package org.example.ds_store;

public class Node<T,W> {
    T key;
    W weight;
    public Node(T key,W weight){
        this.key = key;
        this.weight = weight;
    }
}
