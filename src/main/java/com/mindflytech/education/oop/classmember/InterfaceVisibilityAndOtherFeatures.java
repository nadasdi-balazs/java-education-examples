package com.mindflytech.education.oop.classmember;

//public is optional here
public interface InterfaceVisibilityAndOtherFeatures {
    //compile error:
    //Not allowed in interface
//    static {
//    }
//    {}

    //compiler warning:
    //Modifier 'public' is redundant for interface members
    public int implicitConstant = giveMeValue();

    private static int giveMeValue() {
        System.out.println("-- initializing interface constant value");
        return 42;
    }

    private void doSomething() {
        System.out.println("-- this is a private method on the interface that will never be called: " + implicitConstant);
    }

    private void doOtherSomething() {
        System.out.println("-- this is a private method on the interface that will be used by a " +
                "default method. They can also access constants like: " + implicitConstant);
    }

    default void print() {
        System.out.println("-- this is the interface's default method, it can call private methods");
        doOtherSomething();
    }
}

//public is not allowed here
interface PublicInterface {
    //Illegal combination of modifiers: 'private' and 'public'
//    private interface NestedInterface {
//    }

    //Illegal combination of modifiers: 'protected' and 'public'
//    protected interface ProtectedInterface {
//    }

    interface ImplicitlyPublicInterface {
        //if there is no body: Static methods in interfaces should have a body
        //if final: compile error:
        //Modifier 'final' not allowed here
        static void someMethod() {
            System.out.println("-- static method someMethod called on ImplicitlyPublicInterface");
            someOtherMethod();
        }

        private static void someOtherMethod() {
            System.out.println("-- static method someOtherMethod called on ImplicitlyPublicInterface");
        }


        //compile error:
        //Modifier 'protected' not allowed here
//        protected static void someAntherMethod() {
//            System.out.println("-- static method someOtherMethod called on ImplicitlyPublicInterface");
//        }
    }

    //Modifier 'public' is redundant for interface members
    public interface InnerPublicInterface {
        //abstract methods can't be final either
        String implementMe();

        //compile error:
        //Illegal combination of modifiers: 'default' and 'private'
//        private default something() {
//
//        }
        class NestedClass {

        }
    }
}

class InterfaceUser {
    public static void main(String[] args) {
        System.out.println("-- as the interface constants are static, my hacked static interface initialization" +
                " will run as soon as the interface itself is loaded, just like static initializer blocks in classes");
        //it is static:
        int implicitConstant2 = InterfaceVisibilityAndOtherFeatures.implicitConstant;

        //it is final because:
        //compile error
        //Cannot assign a value to final variable 'implicitConstant'
//        InterfaceVisibilityAndOtherFeatures.implicitConstant = 27;
        System.out.println("-- implicit constant's value (defined in interface): " + implicitConstant2);

        PublicInterface.ImplicitlyPublicInterface.someMethod();

        InterfaceVisibilityAndOtherFeatures callDefault = new InterfaceVisibilityAndOtherFeatures() {};
        callDefault.print();
    }
}

//Modifier 'protected' not allowed here
//protected interface ProtectedInterface {
//}

//Modifier 'private' not allowed here
//private interface PrivateInterface {
//}
