package org.example.leetcode.stack;

import java.util.Stack;

public class countCollisions {
    public int countCollisions(String directions) {
        int ans = 0;
        Stack<Character> stack = new Stack<>();
        int len = directions.length();
        for(int i=0;i<len;i++){
            char current = directions.charAt(i);
            if(current == 'R')
                stack.push(current);
            else{
                int prev = ans;
                if(current == 'L' && !stack.isEmpty())
                    ans++;
                while(!stack.isEmpty() && stack.peek() != 'S'){
                    ans++;
                    stack.pop();
                }
                if(ans != 0 || current == 'S'){
                    if(stack.isEmpty())
                        stack.add('S');
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        countCollisions cc = new countCollisions();
        String directions = "LLSSSLLSS";
        int result = cc.countCollisions(directions);
        System.out.println(result); // Output: 5
    }
}
