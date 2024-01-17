package com.mindflytech.education.oop.method;

public class WrapperClassPassByValue {
    public static void main(String[] args) {
        WrapperClassPassByValue wrapper = new WrapperClassPassByValue();

        wrapper.callWithIntArgument();
        wrapper.callWithIntegerArgument();
    }

    private void callWithIntArgument() {
        int argument = 27;
        System.out.println("-- int parameter called with int argument: " + argument);
        intIncrementer(argument);
        System.out.println("-- value on caller side after execution " + argument);

        argument = 27;
        System.out.println("-- Integer parameter (1) called with int argument: " + argument);
        integerIncrementer1(argument);
        System.out.println("-- value on caller side after execution " + argument);

        argument = 27;
        System.out.println("-- Integer parameter (2) called with int argument: " + argument);
        integerIncrementer2(argument);
        System.out.println("-- value on caller side after execution " + argument);
    }

    private void callWithIntegerArgument() {
        Integer argument = 27;
        System.out.println("-- int parameter called with Integer argument: " + argument);
        intIncrementer(argument);
        System.out.println("-- value on caller side after execution " + argument);

        argument = 27;
        System.out.println("-- Integer parameter (1) called with Integer argument: " + argument);
        integerIncrementer1(argument);
        System.out.println("-- value on caller side after execution " + argument);

        argument = 27;
        System.out.println("-- Integer parameter (2) called with Integer argument: " + argument);
        integerIncrementer2(argument);
        System.out.println("-- value on caller side after execution " + argument);
    }

    private void intIncrementer(int param) {
        System.out.println("---- intIncrementer(int) called with: " + param);
        param++;
        System.out.println("---- at the end method execution, value is: " + param);
    }

    private void integerIncrementer1(Integer param) {
        System.out.println("---- integerIncrementer1(Integer) called with: " + param);
        param++;
        System.out.println("---- at the end method execution, value is: " + param);
    }

    private void integerIncrementer2(Integer param) {
        System.out.println("---- integerIncrementer2(Integer) called with: " + param);
        int originalValue = param.intValue();
        //IMPORTANT: note that here, originalValue++; would result in unexpected behavior
        int incrementedValue = ++originalValue;
        param = Integer.valueOf(incrementedValue);
        System.out.println("---- at the end method execution, value is: " + param);
    }
}
