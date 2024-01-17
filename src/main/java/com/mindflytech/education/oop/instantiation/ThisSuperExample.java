package com.mindflytech.education.oop.instantiation;

import lombok.ToString;

public class ThisSuperExample {
    @ToString
    public static class Super {
        private final String data;

        {
            System.out.println("-- Super initialization block");
        }

        public Super(String data) {
            if(data == null || data.isBlank()) {
                System.out.println("Cannot invoke constructor with a null or blank field");
                throw new RuntimeException("Cannot invoke constructor with a null or blank field");
            }
            System.out.println("-- Super(String) constructor called");
            this.data = data;
        }

        public String getData() {
            return data;
        }
    }

    @ToString(callSuper = true)
    public static class This extends Super{
        private final int anotherInt;
        private final long creationTimestamp;

        {
            creationTimestamp = System.currentTimeMillis();
            System.out.println("-- This initialization block");
        }

        public This(String data, int anotherInt) {
            super(data);
            System.out.println("-- This(String, int) constructor called, super call has already been executed");
            this.anotherInt = anotherInt;
        }

        public This(int anotherData) {
            this("default-argument", anotherData);
            System.out.println("-- This(int) constructor called, super call has already been executed");
        }

        public This() {
            this(-37);
            System.out.println("-- This() constructor called, super call has already been executed");
        }
    }

    public static class Runner {
        public static void main(String[] args) {
            This withNoArg = new This();
            This withOneArg = new This(30);
            This WithTwoArgs = new This("super-parameter", 31);
        }
    }
}
