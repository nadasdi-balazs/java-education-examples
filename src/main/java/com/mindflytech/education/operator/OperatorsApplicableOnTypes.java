package com.mindflytech.education.operator;

public class OperatorsApplicableOnTypes {

    private final int integer = 1;
    private final int otherInt = 2;
    private final double doubleVal = 6.0d;
    private final double otherDouble = 7.2d;
    private final char character = 'c';
    private final char otherChar = 'd';
    private final boolean trueVal = true;
    private final boolean falseVal = false;
    private final String string = "string";
    private final String otherString = "other-string";
    private final int[] array = new int[]{1, 2, 3};
    private final int[] otherArray = new int[]{4, 5, 6};

    private final Object object = new Object();

    public static void main(String[] args) {
        OperatorsApplicableOnTypes operators = new OperatorsApplicableOnTypes();
        operators.plus();
        operators.minus();
        operators.increment();
        operators.roundingError();
        operators.instanceOf();
        operators.minus();
        operators.modulo();
    }

    private void modulo() {
        System.out.println("-- on integers: " + 10 % 4);
        System.out.println("-- on double: " + 10d % 4d);
        System.out.println("-- on double with decimal point: " + 10.0 % 4.0);
        System.out.println("-- on double with decimal point, actual fraction: " + 10.4 % 4.6);
        System.out.println("-- on double and integer: " + 10d % 4);
        System.out.println("-- on integer and double: " + 10 % 4d);
        System.out.println("-- on floats: " + 10f % 4f);
        System.out.println("-- on char: " + 'c' % 'd');
    }

    private void instanceOf() {
        boolean b = true;

    }

    private void roundingError() {
        double two = 2.0d;
        double anotherTwo = 2.0d;
        double result = two + anotherTwo;
        System.out.println("-- is 2.0 + 2.0 = 4.0 in Java? : " + result);

        double three = 3.0d;
        double anotherThree = 3.0d;
        result = three + anotherThree;
        System.out.println("-- is 3.0 + 3.0 = 6.0 in Java? : " + result);
    }

    private void increment() {
        double toIncrement = 0.0d;
        toIncrement++;
        System.out.println("-- incremented double: " + toIncrement);
        char letterC = 'c';
        letterC++;
        System.out.println("-- incremented letter c: '" + letterC + "'");
    }

    private void plus() {
        System.out.println("1 + 2 = " + (integer + otherInt));
        System.out.println("6.0 + 7.2 = " + (doubleVal + otherDouble));

        //operator + cannot be applied on boolean, boolean
//        trueVal + falseVal;
        //operator + cannot be applied on boolean, boolean - it seems we cannot add booleans
//        trueVal + integer

        System.out.println("'c' + 'd' = " + (character + otherChar));

        //operator + cannot be applied to int[], int[]
//        array + otherArray

        System.out.println("'string' + 'other-string' = " + (string + otherString));

        System.out.println("'string' + 2 = " + (string + otherInt));
        System.out.println("2 + 'string' = " + (otherInt + string));
        System.out.println("'string' + 7.2d = " + (string + otherDouble));
        System.out.println("7.2d + 'string' = " + (otherDouble + string));
        System.out.println("'string' + 'd' = " + (string + otherChar));
        System.out.println("'d' + 'string' = " + (otherChar + string));
        System.out.println("'string' + true = " + (string + trueVal));
        System.out.println("true + 'string' = " + (trueVal + string));
        System.out.println("[1,2,3] + 'string' = " + (array + string));
        System.out.println("'string' + [1,2,3] = " + (string + array));
        System.out.println("object + 'string' = " + (object + string));
        System.out.println("'string' + object = " + (string + object));
    }


    private void minus() {
        System.out.println("1 - 2 = " + (integer - otherInt));
        System.out.println("6.0 - 7.2 = " + (doubleVal - otherDouble));

        //operator - cannot be applied on boolean, boolean
//        trueVal - falseVal;
        //operator - cannot be applied on boolean, boolean - it seems we cannot subtract booleans
//        trueVal - integer

        System.out.println("'c' - 'd' = " + (character - otherChar));

        //operator - cannot be applied to int[], int[]
//        array - otherArray

        //operator - cannot be applied to String, String
//        System.out.println("'string' + 'other-string' = " + (string - otherString));
    }
}
