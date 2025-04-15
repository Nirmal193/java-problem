package org.example;

import org.example.ds_store.MinHeap;

import java.util.Comparator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        MinHeap<Integer> minHeap = new MinHeap<>((a,b)-> Integer.compare(a,b) <= 0);
        minHeap.insert(15);
        minHeap.insert(8);
        System.out.println(minHeap.peek());
        minHeap.insert(20);
        minHeap.insert(5);
        minHeap.insert(10);
        minHeap.insert(12);
        System.out.println(minHeap.extractMin());
        System.out.println(minHeap.peek());
        System.out.println(minHeap.extractMin());
        System.out.println(minHeap.peek());
    }
}