package com.mindflytech.education.generics;

import lombok.Data;

import java.util.List;

public class GenericsRestrictions {
    //compile error
    //Generic array creation
//    List<Integer>[] arrayOfLists = new List<Integer>[2];
}

//compile error
//Generic class may not extend 'java.lang.Throwable'
//class MathException<T> extends Exception { /* ... */ }

//compile error
//Generic class may not extend 'java.lang.Throwable'
//class QueueFullException<T> extends Throwable { /* ... */ // compile-time error

@Data
class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
}