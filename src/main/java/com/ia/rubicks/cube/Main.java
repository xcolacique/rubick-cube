package com.ia.rubicks.cube;

public class Main {
    
    public static void main(String[] args) {
        String initialMovs = args[0];
        
        System.out.println("Initial Setup: " + initialMovs);
        
        RCube.doJob(initialMovs);
    }
}
