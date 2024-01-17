package com.mindflytech.education.generics;

import java.util.List;
import static com.mindflytech.util.Utils.generateAsStringSuperClassList;
import static com.mindflytech.util.Utils.prefixedPrintList;

/**
 * Examples from https://docs.oracle.com/javase/tutorial/java/generics/capture.html
 */
public class WildcardError {
    void foo(List<?> i) {
        //Compile error
        //Required type:        //capture of ?
        //Provided:             //capture of ?
//        i.set(0, i.get(0));
    }

    void fooFixBalazs(List<? super Object> i) {
        i.set(0, i.get(0));
    }
}

class WildcardFixed {
    public void foo(List<?> i) {
        fooHelper(i);
    }

    // Helper method created so that the wildcard can be captured
    // through type inference.
    <T> void fooHelper(List<T> l) {
        l.set(0, l.get(0));
    }
}

class Runner {
    public static void main(String[] args) {
        WildcardFixed fixed = new WildcardFixed();
        List<StringSuperClass> list = generateAsStringSuperClassList();
        fixed.foo(list);
        prefixedPrintList("-- using WildcardFixed, list is: ", list);

        WildcardError error = new WildcardError();
        //compile error
        //Required type:        //List        //<? super Object>
        //Provided:             //List        //<StringSuperClass>
//        error.fooFixBalazs(list);
    }
}