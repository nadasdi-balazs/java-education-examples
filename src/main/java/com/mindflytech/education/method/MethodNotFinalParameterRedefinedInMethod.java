package com.mindflytech.education.method;

public class MethodNotFinalParameterRedefinedInMethod {
    public void consumersPrimitiveType(/*final*/ int number) {
        System.out.println("-- consumersPrimitiveType received number: " + number);
        number = 29;
        System.out.println("-- consumersPrimitiveType reassinged value to parameter: " + number);
    }

    public void consumesReferenceType(Object object) {
        System.out.println("-- consumesReferenceType received number: " + object);
        object = new Object();
        System.out.println("-- consumesReferenceType reassinged value to parameter: " + object);
    }


    private void callExample() {
        int number = 27;
        consumersPrimitiveType(number);
        System.out.println("-- the number is still the same: " + number);
        Object object = new Object();
        System.out.println("-- the object I will call consumesReferenceType with is: " + object);
        consumesReferenceType(object);
        System.out.println("-- the object is still the same: " + object);
    }

    public static void main(String[] args) {
        MethodNotFinalParameterRedefinedInMethod redefinedInMethod = new MethodNotFinalParameterRedefinedInMethod();
        redefinedInMethod.callExample();
    }
}
