package com.mindflytech.education.boxing;

public class AutoboxingUnexpectedMethodSignatureMatches {
//    private void consumesNumber(int number) {
//        System.out.println("-- consumesNumber(int) received number: " + number);
//    }

//    private void consumesNumber(long number) {
//        System.out.println("-- consumesNumber(long) received number: " + number);
//    }

//    private void consumesNumber(Integer number) {
//        System.out.println("-- consumesNumber(Integer) received number: " + number);
//    }

    //if this matches with an int argument, it is because int -> boxed into Integer, then Integer matches with Number
    private void consumesNumber(Number number) {
        System.out.println("-- consumesNumber(Number) received number: " + number);
    }

//    private void consumesNumber(Long number) {
//        System.out.println("-- consumesNumber(Long) received number: " + number);
//    }

    private void runExample() {
        int number = 28;
        consumesNumber(number);

        long other = 28L;
        consumesNumber(other);
    }

    public static void main(String[] args) {
        AutoboxingUnexpectedMethodSignatureMatches matches = new AutoboxingUnexpectedMethodSignatureMatches();
        matches.runExample();
    }
}
