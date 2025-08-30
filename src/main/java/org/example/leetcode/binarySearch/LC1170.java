package org.example.leetcode.binarySearch;

import java.util.Arrays;
import java.util.stream.IntStream;

public class LC1170 {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] modQueries = Arrays.stream(queries).mapToInt(x -> convert(x)).toArray();
        int[] modWords = Arrays.stream(words).mapToInt(x -> convert(x)).sorted().toArray();
        int len = words.length;
        int[] ans = IntStream.range(0, queries.length).map(i -> {
            return len - bsearch(modWords, modQueries[i]);
        }).toArray();
        return ans;
    }
    public int bsearch(int nums[], int target){
        int lo = 0,hi = nums.length;
        while(lo < hi){
            int mid = lo + (hi-lo)/2;
            if(nums[mid] <= target){
                lo = mid + 1;
            }else
                hi = mid;
        }
        return lo;
    }
    public int convert (String str){
        char[]  chars = str.toCharArray();
        Arrays.sort(chars);
        int count = 1;
        int ans = 1;
        while(count < str.length() && chars[count] == chars[count-1]){
            count++;
            ans++;
        }
        return count;
    }

    public static void main(String[] args) {
        LC1170 ls = new LC1170();
        String queries[] = {"bba","abaaaaaa","aaaaaa","bbabbabaab","aba","aa","baab","bbbbbb","aab","bbabbaabb"};
        String words[] = {"aaabbb","aab","babbab","babbbb","b","bbbbbbbbab","a","bbbbbbbbbb","baaabbaab","aa"};
        System.out.println(ls.numSmallerByFrequency(queries,words));
    }
}
