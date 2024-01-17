package com.mindflytech.education.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Examples taken from https://jenkov.com/tutorials/java-generics/methods.html
 */
public class GenericMethod {
    public static <T> T addAndReturn(T element, Collection<T> collection){
        collection.add(element);
        return element;
    }

    public static void main(String[] args) {
        //this works
        String stringElement = "stringElement";
        List<Object> objectList = new ArrayList<Object>();
        Object theElement = addAndReturn(stringElement, objectList);

        //this doesn't work
        Object objectElement = new Object();
        List<String> stringList = new ArrayList<String>();
        //compile error
        //                  Required type        Provided
        //element:          T                    Object
        //collection:       Collection<T>        List<String>
        //reason: no instance(s) of type variable(s) exist so that Object conforms to String inference variable T has incompatible bounds: equality constraints: String lower bounds: Object
//        Object returned = addAndReturn(objectElement, stringList);
    }
}
