package com.mindflytech.education.generics;

public class Box<T> {
    private T t;

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public static <E> Box<E> runBoxExample() {
        Box<E> genericBox = new Box<E>();
        return genericBox;
    }

    public static void main(String[] args) {
        Box<?> genericBox = Box.runBoxExample();
        Box<? extends CharSequence> charSequenceExtendsBox = Box.runBoxExample();
        Box<? super CharSequence> charSequenceSuperBox = Box.runBoxExample();
        Box<Object> objectBox = Box.runBoxExample();

        //compiler error
        //Required type:        //capture of ?
        //Provided:        //String
//        genericBox.set("string");
//        genericBox.set(Integer.valueOf(27));
//        genericBox.set(new Object());
//        charSequenceExtendsBox.set("string");

        charSequenceSuperBox.set("string");

        objectBox.set("string");
    }
}

