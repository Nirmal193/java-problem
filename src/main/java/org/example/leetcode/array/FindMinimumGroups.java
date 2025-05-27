package org.example.leetcode.array;

import java.util.*;
import java.util.stream.Collectors;

public class FindMinimumGroups {
    public int carFleet(int target, int[] position, int[] speed) {
        List<Node> cars = new ArrayList<>();
        int len = speed.length;
        for(int i=0;i<len;i++){
            cars.add(new Node(speed[i],position[i]));
        }
        Collections.sort(cars,Comparator.<Node>comparingInt(a->a.position)
                .thenComparingInt(a->a.speed)
                .reversed());
        Stack<Double> stack = new Stack<>();
        int ans = 0;
        for(Node car : cars){
            int distance = target - car.position;
            double time = (double)distance/car.speed;
            if(stack.isEmpty() || stack.peek() < time){
                stack.push(time);
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        FindMinimumGroups fmg = new FindMinimumGroups();
        int target = 12;
        int[] position = {8,3,7,4,6,5};
        int[] speed = {4,4,4,4,4,4};
        System.out.println(fmg.carFleet(target, position, speed)); // Output: 3
    }
    private static class Node{
        public int speed;
        public int position;
        public Node(int speed, int position){
            this.speed = speed;
            this.position = position;
        }
    }
}
