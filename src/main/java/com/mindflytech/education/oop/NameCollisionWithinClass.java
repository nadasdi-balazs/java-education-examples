package com.mindflytech.education.oop;

public class NameCollisionWithinClass {
    private String nameOne;
    private String nameTwo;

    //Compile error, even if the types are different
    //Variable 'nameTwo' is already defined in the scope
//    private int nameTwo;

    //This does not collide fields with the same name
    public String nameOne() {
        //moreOver, it's even practicle
        return nameOne;
    }

    public String nameTwo() {
        return nameTwo;
    }

    //Compile error, even if the return type is different
    //'nameTwo()' is already defined in 'com.mindflytech.education.oop.NameCollisionWithinClass'
//    public int nameTwo() {
//        return 27;
//    }
}
