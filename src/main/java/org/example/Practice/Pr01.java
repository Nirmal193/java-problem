package org.example.Practice;

import java.util.Collections;
import java.util.Optional;
import java.util.PriorityQueue;

public class Pr01 {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.add(234);
        pq.add(23);
        pq.add(566);
        pq.poll();
        System.out.println(pq.toString());
    }
}
