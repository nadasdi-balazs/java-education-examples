package com.mindflytech.education.variables;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is a class for demonstrating pass by value behavior in Java
 */
public class PassByValueExample {
    class MutableExampleClass {
        private static List<MutableExampleClass> allInstances = new LinkedList<>();

        MutableExampleClass(String value1, int value2) {
            this.value1 = value1;
            this.value2 = value2;
            allInstances.add(this);
        }
        String value1;
        int value2;

        public static void printAllInstances() {
            String allInstancesAsString = allInstances.stream().map(MutableExampleClass::toString).collect(Collectors.joining(", "));
            System.out.println("-- all instances: [" + allInstancesAsString + "]");
        }

        @Override
        public String toString() {
            return "MutableExampleClass{" +
                    "value1='" + value1 + '\'' +
                    ", value2=" + value2 +
                    '}';
        }
    }

    public static void main(String[] args) {
        PassByValueExample example = new PassByValueExample();
        example.caller();
    }

    private void caller() {
        String originalValue = "original value";
        int magicConstant = 27;
        MutableExampleClass object = new MutableExampleClass(originalValue, magicConstant);
        System.out.println("-- object after creation: " + object);
        mutateObject(object);
        System.out.println("-- object after mutation: " + object);
//        mutateAndReassingObject(object);
        MutableExampleClass newIstanceOfMutable = mutateReassingAndReturnObject(object);
        System.out.println("-- object after another mutation: " + object);
        MutableExampleClass.printAllInstances();
//        newIstanceOfMutable.printAllInstances();
    }

    private void mutateObject(MutableExampleClass mutable) {
        mutable.value1 = "something else";
    }

    private void mutateAndReassingObject(MutableExampleClass mutable) {
        mutable.value1 = "other something";
        mutable = new MutableExampleClass("new object created", -234);
    }

    private MutableExampleClass mutateReassingAndReturnObject(MutableExampleClass mutable) {
        //These two operations are *not* recommended. Unexpected and can easily cause bugs
        mutable.value1 = "other something";
        mutable = new MutableExampleClass("new object created", -234);
        return mutable;
    }

    interface FinalParameters {
        void method(final String parameter);
    }

    class FinalImplementation implements FinalParameters {
        //final parameters on interface do not enforce anything in the implementing class
        @Override
        public void method(String parameter) {

        }
    }
}
