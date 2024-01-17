package com.mindflytech.education.basics;

public class TypePromotionExample {
    public static void main(String[] args) {
        TypePromotionExample example = new TypePromotionExample();
        example.run();
    }

    private void run() {
        Integer twentySeven = 27;
        something(twentySeven);
    }

    private void something(long parameter) {
        System.out.println("-- something(long) called with parameter: " + parameter);
    }

    private void something(Long parameter) {
        System.out.println("-- something(Long) called with parameter: " + parameter);
    }

    private void something(Number parameter) {
        System.out.println("-- something(Number) called with parameter: " + parameter);
    }
}
