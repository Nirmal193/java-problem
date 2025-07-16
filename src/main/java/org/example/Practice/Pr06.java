package org.example.Practice;
import java.util.*;
public class Pr06 {
    public static void main(String[] args) {
        List<Integer> ls=  new ArrayList<>();
        for(int i=0;i<10;i++){
            ls.add(i);
        }
        System.out.println(ls);
        ls.add(2,20);
        System.out.println(ls);
    }
}
