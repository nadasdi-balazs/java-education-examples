package com.mindflytech.education.controlstructures;

import com.mindflytech.util.Utils;

public class IfExample {
    public static void main(String[] args) {
        long decideUpon = basicIfElseTernaryTest();
        boolean isEven = decideUpon % 2 == 0;

        //compile error
        //Not a statement
        //In this case, ternary operator *cannot* replace an if-else structure
//        isEven ? System.out.println("-- it is even") : System.out.println("-- It is odd");
        if(isEven) {
            System.out.println("-- it is even");
        } else {
            System.out.println("-- It is odd");
        }

        String isEvenAsString = isEven ? "It is even" : "It is odd";
        System.out.println("-- ternary operator has provided us a string which tells if the number was" +
                " even or odd: '" + isEvenAsString + "'");

        String isEvenAsStringWithIfElse;
        if(isEven) {
            isEvenAsStringWithIfElse = "It is even";
        } else {
            isEvenAsStringWithIfElse = "It is odd";
        }


        boolean shouldBeTrue = isEven ? alwaysReturnTrue() : alsoAlwaysReturnTrue();
        System.out.println("-- ternary expression test vol 1 done, result: " + shouldBeTrue);

        boolean shouldBeTrue2 = yetAnotherAlwaysReturnTrue() ? alwaysReturnTrue() : alsoAlwaysReturnTrue();
        System.out.println("-- ternary expression test vol 2 done, result: " + shouldBeTrue2);

        boolean shouldBeTrue3 = yetAnotherAlwaysReturnTrue() || alsoAlwaysReturnTrue() ? alwaysReturnTrue() : alsoAlwaysReturnTrue();
        System.out.println("-- ternary expression test vol 3 done, result: " + shouldBeTrue3);

        //cannot infer type: variable initializer is 'void'
//        var something = decideUpon % 2 == 0 ? returnVoid() : alsoReturnVoid();

        if(alwaysReturnTrue() || alsoAlwaysReturnTrue()) {
            System.out.println("-- it evaluates to true anyways");
        }

        ifDoesNotNeedToBeFollowedByCodeBlock();
    }

    private static void returnVoid() {
        System.out.println("-- returnVoid doesn't return anything");
    }

    private static void alsoReturnVoid() {
        System.out.println("-- alsoReturnVoid doesn't return anything");
    }

    private static long basicIfElseTernaryTest() {
        long decideUpon = System.currentTimeMillis();
        if(decideUpon % 3 == 0) {
            System.out.println("Nanosecond divisable by 3");
        } else if(decideUpon % 3 == 1) {
            System.out.println("Nanosecond's remainder of 3 is 1");
        } else if(decideUpon % 3 == 2) {
            System.out.println("Nanosecond's remainder of 3 is 2");
        }

        String oddOrEven = decideUpon % 2 == 0 ? "even" : "odd";
        System.out.println(oddOrEven);

        CharSequence oddOrEvenAsCharSequence = decideUpon % 2 == 0 ? "even" : new CharSequence(){

            @Override
            public int length() {
                return 0;
            }

            @Override
            public char charAt(int index) {
                return 0;
            }

            @Override
            public CharSequence subSequence(int start, int end) {
                return null;
            }
        };
        System.out.println("-- oddOrEvenAsCharSequence: " + oddOrEvenAsCharSequence);
        return decideUpon;
    }

    private static boolean alwaysReturnTrue() {
        System.out.println("-- alwaysReturnTrue called");
        return true;
    }

    private static boolean alsoAlwaysReturnTrue() {
        System.out.println("-- alsoAlwaysReturnTrue called");
        return true;
    }

    private static boolean yetAnotherAlwaysReturnTrue() {
        System.out.println("-- yetAnotherAlwaysReturnTrue called");
        return true;
    }

    private static void ifDoesNotNeedToBeFollowedByCodeBlock() {
        boolean boolValue = Utils.percentChance(50);
        //Don't do this, unexpected and easy to overlook
        if(boolValue) /*{ */
            System.out.println("-- boolean value was true");
        /*}*/
        System.out.println("-- This is a statement that is always executed");

        if(boolValue)
            System.out.println("-- I did it again");
//        System.out.println("this is not legal");
        else
            System.out.println("-- I did it again in the else block");
    }
}
