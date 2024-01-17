package com.mindflytech.education.oop.initialization;

public class InitializeFieldThroughNonFinalMethodCall {
    public static void main(String[] args) {
        InitializeFieldThroughNonFinalMethodCall initializer = new InitializeFieldThroughNonFinalMethodCall();
        initializer.testInitializers();
    }

    private void testInitializers() {
        SuperClass instance = new SubClass();
        int value = instance.getInitializableValue();
        System.out.println("-- value of initializable variabe: " + value);

        SuperClass otherInstance = new SuperClass();
        int otherValue = otherInstance.getInitializableValue();
        System.out.println("-- value of initializable variabe: " + otherValue);
    }

    private class SuperClass {
        private int initializableValue = initializerMethod();

        //I do not make it final to enable confusion mode
        //Should be final: in this case the initialization will be predictable and this method will be called
        //If not final and overriden, and if the subclass is instantiated, then the value will be that of the subclass' method value
        protected int initializerMethod() {
            System.out.println("-- initializerMethod in SuperClass called");
            return 27;
        }

        public int getInitializableValue() {
            return initializableValue;
        }
    }

    private class SubClass extends SuperClass {
        @Override
        protected int initializerMethod() {
            System.out.println("-- initializerMethod in SubClass called");
            return 28;
        }
    }
}
