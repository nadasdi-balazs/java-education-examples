package com.mindflytech.education.operator;

public class PrecedenceExample {
    public static void main(String[] args) {
        PrecedenceExample precedence = new PrecedenceExample();
        precedence.run();
    }

    private void run() {
        incrementDecrement();
        showRelationalOperatorPrecedence();
    }

    private void showRelationalOperatorPrecedence() {
        boolean onePlusTwoLesserThanThreePlusFour = 1 + 2 < 3 + 4;
        boolean onePlusTwoLesserThanThreePlusFourWithUnnecessaryParentheses = (1 + 2) < (3 + 4);
        int four = 4;
        int another = 8 - 4;
        System.out.println("-- are four and another equal? " + (four == another));
    }

    private void incrementDecrement() {
        System.out.println("-- x = 10");
        int x = 10;
        System.out.println(x++);//10 (11)
        System.out.println("-- after operation, x = " + x);

        System.out.println("-- a = 10, b = 10");
        int a = 10;
        int b = 10;
        System.out.println("-- let's see what happens after a++ + ++a:");
        System.out.println(a++ + ++a);//10+12=22
        System.out.println("-- after that, a = " + a);

        System.out.println("-- let's see what happens after b++ + b++:");
        System.out.println(b++ + b++);//10+11=21
        System.out.println("-- after that, b = " + b);
    }
}
