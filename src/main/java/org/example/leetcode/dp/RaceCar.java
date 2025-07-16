package org.example.leetcode.dp;

import java.util.*;

public class RaceCar {
    public int racecar(int target) {
        Queue<Car> queue = new LinkedList<>();
        Set<String> memo = new HashSet<>();
        int steps = 0;
        queue.add(new Car(0,1));
        while(!queue.isEmpty()){
            int sz = queue.size();
            for(int i=0;i<sz;i++){
                String key = "";
                Car curr = queue.poll();
                if(curr.position == target){
                    return steps;
                }
                Car acc = new Car(curr.position + curr.speed, curr.speed * 2);
                key = acc.position + "$" + acc.speed;
                if(Math.abs(acc.position) <= 2 * target && !memo.contains(key)){
                    queue.add(acc);
                    memo.add(key);
                }
                Car rev = new Car(curr.position, (curr.speed > 0) ? -1 : 1);
                key = rev.position + "$" + rev.speed;
                if(Math.abs(rev.position) <= 2* target && !memo.contains(key)){
                    queue.add(rev);
                    memo.add(key);
                }
            }
            steps++;
        }
        return -1;
    }
    private static class Car{
        public int position;
        public int speed;
        public Car(int position,int speed){
            this.position = position;
            this.speed = speed;
        }
    }
}
