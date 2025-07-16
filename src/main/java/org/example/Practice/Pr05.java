package org.example.Practice;
import java.util.*;
public class Pr05 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 3, 5, 7, 9);

        // Return values explained:

        // Case 1: Element found in list
        // Returns positive number (index where element is found)
        int case1 = Collections.binarySearch(list, 5);  // Returns 2

        // Case 2: Element not found
        // Returns negative: -(insertion_point + 1)
        int case2 = Collections.binarySearch(list, 4);  // Returns -3
        // Here -3 means element should be at index 2
        // To get insertion point: -(-3 + 1) = 2

        // Case 3: Element smaller than all elements
        int case3 = Collections.binarySearch(list, 0);  // Returns -1
        // -1 means element should be at index 0

        // Case 4: Element larger than all elements
        int case4 = Collections.binarySearch(list, 10); // Returns -6
        // -6 means element should be at index 5 (end)

        System.out.println("Element found at: " + case1);          // 2
        System.out.println("Insert 4 at: " + (-case2 - 1));       // 2
        System.out.println("Insert 0 at: " + (-case3 - 1));       // 0
        System.out.println("Insert 10 at: " + (-case4 - 1));      // 5
        TreeMap<Integer,Integer> map = new TreeMap<>();
        map.put(1,10);
        map.put(2,30);
        map.put(4,20);
        map.put(10,40);
        System.out.println(map.ceilingKey(5));
    }
}
