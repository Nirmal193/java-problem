package org.example.amazon6months;

import java.util.Arrays;
import java.util.Comparator;

public class reorderLogFiles {
    public static String[] reorderLogFiles(String[] logs) {
        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int idx1 = s1.indexOf(' ');
                int idx2 = s2.indexOf(' ');

                String id1 = s1.substring(0, idx1);
                String rem1 = s1.substring(idx1 + 1);

                String id2 = s2.substring(0, idx2);
                String rem2 = s2.substring(idx2 + 1);

                boolean isDigit1 = Character.isDigit(rem1.charAt(0));
                boolean isDigit2 = Character.isDigit(rem2.charAt(0));

                if (!isDigit1 && !isDigit2) {
                    int cmp = rem1.compareTo(rem2);
                    if (cmp != 0) return cmp;
                    return id1.compareTo(id2);
                } else if (!isDigit1) {
                    return -1;
                } else if (!isDigit2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };
        Arrays.sort(logs,comp);
       for(String str:logs)
           System.out.println(str);
        return logs;
    }

    public static void main(String[] args) {
        String log[] = {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
        reorderLogFiles(log);
    }
}
