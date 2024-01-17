package com.mindflytech.education.oop;

// It doesn't *have to be* public
public class ClassInnerClassExample {
    class InnerClass {
        class InnerInnerClass {

        }
    }

    static class StaticInnerClass {

    }
}

// we can only have on public class
class AnotherClass {
    public static void main(String[] args) {
        class LocalClass{
            static class StaticInnerClassInsideLocalClass {

            }

            class InnerClassInsideLocalClass {

            }
        }
        LocalClass localInstance = new LocalClass();
        LocalClass.StaticInnerClassInsideLocalClass inside = new LocalClass.StaticInnerClassInsideLocalClass();
        LocalClass.InnerClassInsideLocalClass innerClassInsideLocalClassInstance = localInstance.new InnerClassInsideLocalClass();

        Runnable somethingToRun = new Runnable() {
            @Override
            public void run() {
                System.out.println("-- anonymous runnable run");
            }
        };
        Runnable somethingToRun2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("-- anonymous runnable run");
            }
        };

        Runnable fromLambda = () -> System.out.println("-- lambda");
        Runnable fromLambda2 = () -> System.out.println("-- lambda 2");

        ClassInnerClassExample.StaticInnerClass innerStaticInstance = new ClassInnerClassExample.StaticInnerClass();
        ClassInnerClassExample.StaticInnerClass innerStaticInstance2 = new ClassInnerClassExample.StaticInnerClass();
        ClassInnerClassExample outer = new ClassInnerClassExample();
        ClassInnerClassExample.InnerClass innerClassInstance = outer.new InnerClass();
        ClassInnerClassExample.InnerClass.InnerInnerClass innerInnerInstance = innerClassInstance.new InnerInnerClass();

        AnotherClass currentObject = new AnotherClass();
        AnotherInnerClass anotherInnerInstance = currentObject.instantiateInnerClass();

        AnotherStaticInnerClass anotherStaticInnerClass = new AnotherStaticInnerClass();
        AnotherStaticInnerClass anotherStaticInnerClass2 = new AnotherStaticInnerClass();
//        AnotherStaticInnerClass.InnerClassInsideStaticInnerClass hidden = anotherInnerInstance.new InnerClassInsideStaticInnerClass();
        AnotherStaticInnerClass.StaticInnerClassInsideStaticInnerClass staticInside = new AnotherStaticInnerClass.StaticInnerClassInsideStaticInnerClass();
    }

    private AnotherInnerClass instantiateInnerClass() {
        AnotherInnerClass anotherInnerInstance = new AnotherInnerClass();
        return anotherInnerInstance;
    }

    static class AnotherStaticInnerClass {
        static class StaticInnerClassInsideStaticInnerClass {

        }
        public class InnerClassInsideStaticInnerClass {

        }
    }

    public class AnotherInnerClass {

    }
}
