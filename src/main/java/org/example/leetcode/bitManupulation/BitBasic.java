package org.example.leetcode.bitManupulation;

public class BitBasic {
    public int removeDuplicate(int arr[]){
        int result = 0;
        for(int i=0;i<arr.length;i++){
            result ^= arr[i];
        }
        return result;
    }
    public void printAllCombination(String str){
        int len = str.length();
        for(int i=0;i<(1<<len);i++){
            StringBuilder sb = new StringBuilder();
            for(int j=0;j<len;j++){
                if((i & (1 << j)) != 0){
                    sb.append(str.charAt(j));
                }
            }
            System.out.println(sb.toString());
            sb.setLength(0);
        }
    }
    public static void main(String[] args) {
        int i = 3;
        System.out.println(i>>1);
    }
}
