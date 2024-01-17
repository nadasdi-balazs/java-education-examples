package com.mindflytech.education.oop.supertest;

public class SuperKeywordExample {
    public static void main(String[] args) {
        SubClassOne subClassOne = new SubClassOne();
        subClassOne.visible();

        System.out.println("==========================================");

        SubClassTwo subClassTwo = new SubClassTwo();
        subClassTwo.visible();
    }
}

class SuperClass {
    public int publicField = 42;
    protected int protectedField = 43;
    int defaultField = 44;
    private int privateField = 45;

    private String privateMethod() {
        String message = "-- SuperClass.privateMethod called";
        System.out.println(message);
        return message;
    }

    String defaultMethod() {
        String message = "-- SuperClass.defaultMethod called";
        System.out.println(message);
        return message;
    }

    protected String protectedMethod() {
        String message = "-- SuperClass.protectedMethod called";
        System.out.println(message);
        return message;
    }

    public String publicMethod() {
        String message = "-- SuperClass.publicMethod called";
        System.out.println(message);
        return message;
    }
}

class SubClassOne extends SuperClass {
    public void visible() {
        System.out.println("-- visible from SubClassOne.visible():");
        System.out.println("-- this.defaultField: " + this.defaultField);
        System.out.println("-- this.protectedField: " + this.protectedField);
        System.out.println("-- this.publicField: " + this.publicField);
        System.out.println("-- this.defaultMethod(): " + this.defaultMethod());
        System.out.println("-- this.protectedMethod(): " + this.protectedMethod());
        System.out.println("-- this.publicMethod(): " + this.publicMethod());
        System.out.println("-- super.defaultField: " + super.defaultField);
        System.out.println("-- super.protectedField: " + super.protectedField);
        System.out.println("-- super.publicField: " + super.publicField);
        System.out.println("-- super.defaultMethod(): " + super.defaultMethod());
        System.out.println("-- super.protectedMethod(): " + super.protectedMethod());
        System.out.println("-- super.publicMethod(): " + super.publicMethod());
    }
}

class SubClassTwo extends SuperClass {
    public int publicField = 46;
    protected int protectedField = 47;
    int defaultField = 48;
    private int privateField = 49;

    private String privateMethod() {
        String message = "-- SubClassTwo.privateMethod called";
        System.out.println(message);
        return message;
    }

    @Override
    String defaultMethod() {
        String message = "-- SubClassTwo.defaultMethod called";
        System.out.println(message);
        super.defaultMethod();
        return message;
    }

    @Override
    protected String protectedMethod() {
        String message = "-- SubClassTwo.protectedMethod called";
        System.out.println(message);
        super.protectedMethod();
        return message;
    }

    @Override
    public String publicMethod() {
        String message = "-- SubClassTwo.publicMethod called";
        System.out.println(message);
        super.publicMethod();
        return message;
    }

    public void visible() {
        System.out.println("-- visible from SubClassTwo.visible():");
        System.out.println("-- this.defaultField: " + this.defaultField);
        System.out.println("-- this.protectedField: " + this.protectedField);
        System.out.println("-- this.publicField: " + this.publicField);
        System.out.println("-- this.defaultMethod(): " + this.defaultMethod());
        System.out.println("-- this.protectedMethod(): " + this.protectedMethod());
        System.out.println("-- this.publicMethod(): " + this.publicMethod());
        System.out.println("-- super.defaultField: " + super.defaultField);
        System.out.println("-- super.protectedField: " + super.protectedField);
        System.out.println("-- super.publicField: " + super.publicField);
        System.out.println("-- super.defaultMethod(): " + super.defaultMethod());
        System.out.println("-- super.protectedMethod(): " + super.protectedMethod());
        System.out.println("-- super.publicMethod(): " + super.publicMethod());

        //compile error: super can't be used as an argument in method call
        //'.' expected
//        calledWithSuper(super);
    }

    private void calledWithSuper(SuperClass superClass) {
        System.out.println("-- SubClassTwo.calledWithSuper");
    }
}

class SubSubClassOne extends SubClassOne {
    public void superSuperCaller() {
        //compile error
        //'.' expected
//        return super;
        //Call to 'super()' must be first statement in constructor body
//        super();
        //compile error
        //Cannot resolve symbol 'super'
//        super.super
    }
}
