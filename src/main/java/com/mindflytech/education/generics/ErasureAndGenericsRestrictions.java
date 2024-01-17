package com.mindflytech.education.generics;

import com.google.common.collect.Lists;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ErasureAndGenericsRestrictions {
    //compile error
    //Generic class may not extend 'java.lang.Throwable'
//    class MathException<T> extends Exception { /* ... */ }    // compile-time error

    //compile error
    //Generic class may not extend 'java.lang.Throwable'
//    class QueueFullException<T> extends Throwable { /* ... */}

    //compiler
    //'add(Set<Integer>)' clashes with 'add(Set<String>)'; both methods have same erasure
//    void add(Set<Integer> ii) {
//    }
//    void add(Set<String> ss) {
//    }

    public static void main(String[] args) {
        ErasureAndGenericsRestrictions example = new ErasureAndGenericsRestrictions();
        example.runExample();
    }

    private void runExample() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        if(list instanceof List<Integer>) {
            System.out.println("-- list instanceof List<Integer> always true");
        }
        if(list instanceof ArrayList<?>) {
            System.out.println("-- list instanceof ArrayList<?> always true");
        }

        List<Integer> li = new ArrayList<>();
        //compile error
        //Inconvertible types; cannot cast 'java.util.List<java.lang.Integer>' to 'java.util.List<java.lang.Number>
//        List<Number>  ln = (List<Number>) li;

        List<String> l1 = Lists.newArrayList("one", "two", "three", "four");
        ArrayList<String> l2 = (ArrayList<String>)l1;
        List<?> wildcardList = (List<?>)li;

        //compile error
        //Generic array creation
//        List<Integer>[] arrayOfLists = new List<Integer>[2];  // compile-time error

        Object[] strings = new String[2];
        strings[0] = "hi";   // OK
//        strings[1] = 100;    // An ArrayStoreException is thrown.

        //compile error
        //Generic array creation
//        Object[] stringLists = new List<String>[2];  // compiler error, but pretend it's allowed
//        stringLists[0] = new ArrayList<String>();   // OK
//        stringLists[1] = new ArrayList<Integer>();  // An ArrayStoreException should be thrown,
                                                    // but the runtime can't detect it.
        List<String[]> listOfStringArrays = new ArrayList<>();
        String[] stringArray = new String[5];
        listOfStringArrays.add(stringArray);
        //compile error
        //Generic array creation
//        List<String>[] arrayOfListsOfStrings = new List<String>[5];

        RuntimeException runtimeException = new RuntimeException("this is a runtime exception thrown in " +
                "a generic method");
        execute(runtimeException);

        execute(new RuntimeException());
    }

    private <T> void throwT(List<T> list) {
        for(T element : list) {
            //compile error
            //Required type:       //Throwable
            //Provided:            //T
//            throw element;
        }
    }

    public static <T extends Exception, J> void execute(List<J> jobs) {
        //compile error
        //Cannot catch type parameters
//        try {
//            for (J job : jobs) {}
//            // ...
//        } catch (T e) {   // compile-time error
//            // ...
//        }
    }

    public <T extends Exception> void execute(T type) throws T {
        System.out.println("-- haha, I will throw an exception now");
        throw type;
    }

    public class Example {
        //compile error
        //'print(Set<String>)' clashes with 'print(Set<Integer>)'; both methods have same erasure
//        public void print(Set<String> strSet) { }
        public void print(Set<Integer> intSet) { }
    }

    class Parser<T extends Exception> {
        public void parse(File file) throws T {     // OK
            // ...
        }
    }
}
