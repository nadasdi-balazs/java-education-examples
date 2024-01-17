package com.mindflytech.education.oop.thistest;

public class ThisInNestedClasses {
    private int field = 27;

    public static void main(String[] args) {
        ThisInNestedClasses nested = new ThisInNestedClasses();
        nested.callThis();

        NestedInnerClass nestedInnerClass = nested.new NestedInnerClass();
        nestedInnerClass.method();

        StaticClass staticClass = new StaticClass();
        staticClass.method();
    }

    private void callThis() {
        System.out.println("-- this.getClass normally, in a method call" + this.getClass());
        class LocalClass implements Runnable {
            @Override
            public void run() {
                System.out.println("-- LocalClass.run, this.getClass: " + this.getClass());
            }
        }
        LocalClass instance  = new LocalClass();
        instance.run();

        //hiding
        int field = 28;
        System.out.println("local variable hidden a field, its value: " + field);
        System.out.println("the hidden field's value: " + this.field);
    }

    private void callMaybeThis() {
        System.out.println(" --ThisInNestedClasses.callMaybeThis called");
    }

//    folytatni sima nested classel es anonim inner classel, majd megnezni a getClass-t futas kozben
//    esetleg az interface-es lehetosegeket is vegignezzuk
    class NestedInnerClass {
        public void method() {
            System.out.println("--  in NestedInnerClass.method, using reference ThisInNestedClasses.this.callThis() to call " +
                    "the enclosing class' object reference");
            ThisInNestedClasses.this.callMaybeThis();
            System.out.println("-- in NestedInnerClass.method, this.getClass(): " + this.getClass());
            System.out.println("-- in NestedInnerClass.method, ThisInNestedClasses.this.getClass(): " + ThisInNestedClasses.this.getClass());

            Runnable anonymRunnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("-- anonymous class in NestedInnerClass.method(), this.getClass(): " + this.getClass());
                    System.out.println("-- same place, NestedInnerClass.this.getClass(): " + NestedInnerClass.this.getClass());
                    System.out.println("-- same place, ThisInNestedClasses.this.getClass(): " + ThisInNestedClasses.this.getClass());
                }
            };
            anonymRunnable.run();
        }
    }

    static class StaticClass {
        public StaticClass() {
            this("random argument", "another random argument");
            System.out.println("-- StaticClass's constructor, this.getClass(): " + this.getClass());
        }

        public StaticClass(String argument, String anotherArgument) {
            System.out.println("-- StaticClass' other constructor called via this reference with arguments: '"
                    + argument + "', '" + anotherArgument + "'");
        }

        public void method() {
            System.out.println("-- StaticClass.method,  this.getClass:" + this.getClass());
            //compile error
            //'com.mindflytech.education.oop.thistest.ThisInNestedClasses.this' cannot be referenced from a static context'
//            System.out.println("-- StaticClass.method, outer class reference: " + ThisInNestedClasses.this.getClass());
        }
    }
}
