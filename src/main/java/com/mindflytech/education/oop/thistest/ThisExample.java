package com.mindflytech.education.oop.thistest;

import java.util.function.Consumer;

public class ThisExample {
    private int integer;
    private String text;

    public ThisExample() {
        this(-1, "no arguments given");
        System.out.println("-- this.integer: " + this.integer);
        this.sampleMethod();
        //compile error
        //Cannot return a value from a method with void result type
//        return this;
    }

    public ThisExample(int integer, String text) {
        this.integer = integer;
        this.text = text;
    }

    private void sampleMethod() {
        System.out.println("-- sample method runs");
    }

    private void callConsumer() {
        ThisConsumer consumer = ThisConsumer.createInstance();
        consumer.accept(this);
        consumer.accept(new ThisExample());
    }

    public static void main(String[] args) {
        ThisExample example = new ThisExample();
        example.callConsumer();
    }
}

class ThisConsumer implements Consumer<ThisExample> {
    public static ThisConsumer createInstance() {
        ThisConsumer consumer = new ThisConsumer();
        return consumer;
    }

    @Override
    public void accept(ThisExample thisObject) {
        System.out.println("-- I have consumed _this_: " + thisObject);
    }
}
