package com.mindflytech.education.method;

import com.mindflytech.education.generics.StringSuperClass;
import com.mindflytech.education.generics.StringSubClass;

public class MethodSignatureExample {
    public static void main(String[] args) {
        MethodSignatureExample example = new MethodSignatureExample();
        example.printText("jmerijk");
        StringSuperClass otherStringy = new StringSuperClass("nvjrekn");
        example.printText(otherStringy);
    }

    public void printText(String text) {
        System.out.println("-- I am printing text: '" + text + "'");
        text = "other text";
        System.out.println(text);
    }

    public void printText(CharSequence text) {
        System.out.println("-- I am printing text: '" + text + "'");
    }

    public void printText(StringSubClass text) {
        System.out.println("-- I am printing text: '" + text + "'");
    }

    //compile error
    //'method()' is already defined in 'com.mindflytech.education.method.MethodSignatureExample
//    public String method() {
//        return "string return type";
//    }
//
//    public int method() {
//        return 27;
//    }

    class MethodSignatureExampleSubClass extends MethodSignatureExample {
        //compile error
        //Method does not override method from its superclass
//        @Override
//        public void printText(StringSuperClass text) {
//            System.out.println("-- I am printing text: '" + text + "'");
//        }
    }

}
