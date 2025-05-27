package org.example.leetcode.array;

import java.util.Stack;

public class CarFleet2 {
    public static double[] getCollisionTimes(int[][] cars) {
        int len = cars.length;
        Stack<Integer> stack = new Stack<>();
        double ans[] = new double[len];
        for(int i=len-1;i>=0;i--){
            while(!stack.isEmpty()){
                if(cars[stack.peek()][1] >= cars[i][1] ||
                        (ans[stack.peek()] != -1 && ans[stack.peek()] < calculate(cars[stack.peek()],cars[i]))){
                    stack.pop();
                }else
                    break;
            }

            if(stack.isEmpty()){
                ans[i] = -1;
            }else{
                ans[i] = calculate(cars[stack.peek()],cars[i]);
            }
            stack.push(i);
        }
        return ans;
    }
    public static double calculate(int[]car1, int[] car2){

        double ans =  ((double)(car2[0] - car1[0]))/(double)(car1[1] - car2[1]);
        return ans;
    }

    public static void main(String[] args) {
        int[][] cars = {{1, 2}, {2, 1}, {4, 3}, {7, 2}};
        double[] result = getCollisionTimes(cars);
        for (double time : result) {
            System.out.print(time + " ");
        }
        // Output: [1.00000, -1.00000, 3.00000, -1.00000]
    }
}
