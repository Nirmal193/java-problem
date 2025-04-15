package org.example.amazon6months;

import java.util.*;

public class countOfAtoms {
    static Map<String, Integer> finalCount = new TreeMap<>();// Use TreeMap for sorted output

    public static String countOfAtoms(String formula) {
        finalCount.clear();
        int len = formula.length();
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            char ch = formula.charAt(i);

            if (ch == '(') {
                if (!sb.isEmpty()) {
                    stack.push(sb.toString());
                    sb.setLength(0);
                }
                stack.push("(");
            } else if (ch == ')') {
                if (!sb.isEmpty()) {
                    stack.push(sb.toString());
                    sb.setLength(0);
                }
                i++;
                int multiplier = 1;
                int start = i;
                while (i < len && Character.isDigit(formula.charAt(i))) {
                    i++;
                }
                if (start < i) {
                    multiplier = Integer.parseInt(formula.substring(start, i));
                    i--;
                } else {
                    i--;
                }
                List<String> inside = new ArrayList<>();
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    inside.add(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek().equals("(")) {
                    stack.pop();
                }
                Collections.reverse(inside);
                StringBuilder merged = new StringBuilder();
                for (String str : inside) {
                    merged.append(str);
                }
                String expanded = multiplexer(merged.toString(), multiplier);
                stack.push(expanded);

            } else if (Character.isLetter(ch)) {
                if (Character.isUpperCase(ch) && sb.length() > 0) {
                    stack.push(sb.toString());
                    sb.setLength(0);
                }
                sb.append(ch);
            } else if (Character.isDigit(ch)) {
                sb.append(ch);
            }
        }

        if (!sb.isEmpty()) {
            stack.push(sb.toString());
        }
        StringBuilder full = new StringBuilder();
        while (!stack.isEmpty()) {
            full.insert(0, stack.pop());
        }
        multiplexer(full.toString(), 1);
        StringBuilder res = new StringBuilder();
        for (String atom : finalCount.keySet()) {
            res.append(atom);
            int count = finalCount.get(atom);
            if (count > 1) res.append(count);
        }
        return res.toString();
    }

    public static int convertToInt(String s, int index) {
        StringBuilder sb = new StringBuilder();
        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            sb.append(s.charAt(index++));
        }
        return sb.length() > 0 ? Integer.parseInt(sb.toString()) : 1;
    }

    public static String multiplexer(String s, int multiplier) {
        int i = 0;
        StringBuilder res = new StringBuilder();
        while (i < s.length()) {
            StringBuilder atom = new StringBuilder();
            atom.append(s.charAt(i++));
            while (i < s.length() && Character.isLowerCase(s.charAt(i))) {
                atom.append(s.charAt(i++));
            }

            StringBuilder count = new StringBuilder();
            while (i < s.length() && Character.isDigit(s.charAt(i))) {
                count.append(s.charAt(i++));
            }

            int countVal = count.length() == 0 ? 1 : Integer.parseInt(count.toString());
            countVal *= multiplier;
            finalCount.put(atom.toString(), finalCount.getOrDefault(atom.toString(), 0) + countVal);
            res.append(atom).append(countVal);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(countOfAtoms("H2O"));
        System.out.println(countOfAtoms("Mg(OH)2"));
        System.out.println(countOfAtoms("K4(ON(SO3)2)2"));
    }

    public static String countOfAtomsV2(String formula) {
        finalCount.clear();
        int len = formula.length();
        Stack<Map<String, Integer>> stack = new Stack<>();
        Map<String, Integer> currentMap = new HashMap<>();
        int i = 0;

        while (i < len) {
            char ch = formula.charAt(i);

            if (ch == '(') {
                stack.push(currentMap);
                currentMap = new HashMap<>();
                i++;
            } else if (ch == ')') {
                i++;
                int start = i;
                while (i < len && Character.isDigit(formula.charAt(i))) i++;
                int multiplier = start < i ? Integer.parseInt(formula.substring(start, i)) : 1;
                Map<String, Integer> temp = currentMap;
                currentMap = stack.pop();
                for (String atom : temp.keySet()) {
                    currentMap.put(atom, currentMap.getOrDefault(atom, 0) + temp.get(atom) * multiplier);
                }
            } else {
                int start = i++;
                while (i < len && Character.isLowerCase(formula.charAt(i))) i++;
                String atom = formula.substring(start, i);
                start = i;
                while (i < len && Character.isDigit(formula.charAt(i))) i++;
                int count = start < i ? Integer.parseInt(formula.substring(start, i)) : 1;

                currentMap.put(atom, currentMap.getOrDefault(atom, 0) + count);
            }
        }

        finalCount = new TreeMap<>(currentMap);
        StringBuilder result = new StringBuilder();
        for (String atom : finalCount.keySet()) {
            result.append(atom);
            int count = finalCount.get(atom);
            if (count > 1) result.append(count);
        }
        return result.toString();
    }
}
