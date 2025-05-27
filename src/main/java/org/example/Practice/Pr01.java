package org.example.Practice;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Pr01 {
    public static void main(String[] args) {
        List<Integer> lsit = new ArrayList<>();
        lsit.add(37);
        lsit.add(0,4);
        List.of();
        Set<Integer> set = new HashSet<>();
        set.clear();;
        Map<Integer,Integer> map = new HashMap<>();
        map.clear();
        System.out.println(lsit.get(0));
    }
    public static long createSmallest(long num){
        String str  = String.valueOf(num);
        int len = str.length();
        char[] array = str.toCharArray();
        Arrays.sort(array);
        int zero = 0;
        while(array[zero]=='0'){
            zero++;
        }
        if(zero ==  len)
            return 0;
        StringBuilder sb = new StringBuilder();
        sb.append(array[zero]);
        IntStream.range(0,zero).forEach(x->sb.append("0"));
        for(int i = zero+1;i<len;i++){
            sb.append(array[i]);
        }
        long ans = Long.parseLong(sb.toString());
        return ans;
    }
    public static long createLargest(long num){
        String str = String.valueOf(num);
        Character array[] = str.chars().mapToObj(x->(char)x).toArray(Character[]::new);
        Arrays.sort(array,Comparator.reverseOrder());
        StringBuilder sb = new StringBuilder();
        Arrays.stream(array).forEach(x->sb.append(x));
        long ans = Long.parseLong(sb.toString());
        return ans;
    }
}
