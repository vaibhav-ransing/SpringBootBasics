package com.vaibhav.microservices;

public class FaltuFile {
    public static  int tst(int a){
        int b = 12;
        if (a == 1) {
            return b;
        }
        return b;  // Noncompliant
    }
}
