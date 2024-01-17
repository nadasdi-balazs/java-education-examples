package com.mindflytech.education.inheritence;

public class HidingExample extends HidingExampleSuper {
    private String data = "data in HidingExample";
    private String anotherData = "anotherData in HidingExample";
    private String third = "yadda yadda";
//    private int third = 30;

    public static void main(String[] args) {
        HidingExample hiding = new HidingExample();
        hiding.hide("this is a parameter that hides the field and the superclass field");
    }

    private void hide(String thisIsNotAnotherData) {
        System.out.println("-- third in class: " + third + ", in superclass: " + super.third);
        String thirdData = "third data string in hide() method";
        System.out.println("-- data in HidingExample: '" + data + "'");
        //'data' has private access in 'com.mindflytech.education.inheritence.HidingExampleSuper'
//        System.out.println("-- super.data in HidingExample: '" + super.data + "'");
        String anotherData = "this hides both the field and the superclass field";
        System.out.println("-- anotherData local variable: '" + anotherData + "'");
        System.out.println("-- anotherData field: '" + this.anotherData + "'");
        {
            String data = "ewjnfkjrew";
            System.out.println("-- data within method, within separate block: '" + data + "'");
        }
        Runnable lambda = () -> {
            System.out.println("-- I see anotherData here in the lambda body: '" + anotherData + "'");
        };
        lambda.run();
        System.out.println("-- data within method, after separate block: '" + data + "'");

        System.out.println("-- super.anotherData in HidingExample: '" + super.anotherData + "'");

        class LocalClass {
            private final String anotherData;

            LocalClass(String anotherData) {
                this.anotherData = anotherData;
            }

            public void run() {
                System.out.println("-- LocalClass.run, anotherData in local c lass: '" + anotherData + "'");
                System.out.println("-- LocalClass.run, anotherData of the enclosing class:  '" + HidingExample.this.anotherData + "'");
                System.out.println("-- LocalClass.run, anotherData of the enclosing class's superclass:  '" + HidingExample.super.anotherData + "'");
                System.out.println("-- LocalClass.run, effectively final thirdData of enclosing scope:  '" + thirdData + "'");
            }
        }

        LocalClass localClass = new LocalClass("anotherData in LocalClass");
        localClass.run();

        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("-- anonymous runnable runs");
            }
        };
        anonymousRunnable.run();

        AbstractClass abstractsSubclassInstance = new AbstractClass("I give you some data") {

        };
        abstractsSubclassInstance.printPresence();
        



        //Compile error: Cannot resolve symbol 'super'
        //I can't access HidingExampleSuperSuper.anotherData
//        System.out.println("-- super.anotherData in HidingExample: '" + super.super.anotherData + "'");
    }

    abstract class AbstractClass {
        private final String data;

        public AbstractClass(String data) {
            this.data = data;
        }
        public void printPresence() {
            System.out.println("-- AbstractClass.printPresence, I am here");
            System.out.println("-- AbstractClass.printPresence, my field is: '" + data + "'");
        }
    }
}
