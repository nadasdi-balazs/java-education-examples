package com.mindflytech.education.functionalinterface;

public class FunctionalInterfaceExample {

    /**
     * A functional interface can extends another interface only when it does not have any abstract method.
     * Said the java tutorial: https://www.javatpoint.com/java-8-functional-interfaces
     * but it is not true, see below
     */
    public interface Tagging {
        //no abstract methods
    }

    public interface WithOneAbstractMethod {
        void abstractMethod();
    }

    @FunctionalInterface
    public interface ExtendsOneWithNoAbstractMethods extends Tagging {
        void abstractMethod();
    }

    @FunctionalInterface
    public interface ExtendsOneWithOneAbstractMethod extends WithOneAbstractMethod {
        //this is valid
    }

//    @FunctionalInterface
//    public interface DemonstrateThatTwoAbstractMethodsAreInvalidInFuncionalInterfaces {
//        void abstractOne();
//
//        void abstractTwo();
//    }
}
