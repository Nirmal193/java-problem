package org.example.revesion.bitManupulation;

public class BitBasics {
    public static void solve(){
        int number = 42; // Binary: 101010
        int i = 3;
        if((number & (1<<i)) != 0)
            System.out.println("ith bit is set");
        else
            System.out.println("ith bit is not set");
    }

    public static void main(String[] args) {
        solve();
    }
}
