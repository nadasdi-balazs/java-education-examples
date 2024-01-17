package com.mindflytech.education.controlstructures;

public class WhileExample {
    public static void main(String[] args) {
        WhileExample example = new WhileExample();
        example.doWhile();
    }

    private void doWhile() {
        long millis = System.currentTimeMillis();
        while(millis > 0) {
            System.out.println("-- millis: " + millis);
            millis--;
        }
    }
}
