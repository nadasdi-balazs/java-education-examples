package com.mindflytech.education.variables;

public class ShadowingExample {
    private int variableName = 26;

    public static void main(String[] args) {
        ShadowingExample example = new ShadowingExample();
//        example.shadow();
//        example.plusOperator();
        example.widening();
    }

    private void shadow() {
        System.out.println("-- before declaring variableName as local variable: '" + variableName + "'");
        int variableName = 27;
        System.out.println("-- after declaring variableName as local variable: '" + variableName + "'");
        System.out.println("-- I can still access the field even after declaring"
                + " variableName as local variable: '" + this.variableName + "'");
//        if(true) {
//            int variableName = 28;
//        }

    }

    private void plusOperator() {
        Integer a = Integer.valueOf(27);
        Integer b = Integer.valueOf(28);
        Integer sum = a + b;
        System.out.println("-- sum: '" + sum);

        String text = "text";
        String s = text + a;
        String s1 = a + text;
        String s2 = a + b + text;
        System.out.println("-- string concatenated with integers: '" + s + "', '" + s1 + "', '" + s2 + "'");
        String s3 = a + b + text + 29;
        System.out.println("-- string concatenated with integers: '" + s3 + "'");
        System.out.println("-- doing a + b + text within sout: " + a + b + text);
        System.out.println(a + b + text);
    }

    private void widening() {
        byte b = 21;
        int c = 375983275;
        //widening
        int sum1 = b + c;
        System.out.println("-- sum1: " + sum1);
        //syntax error, won't narrow, requires type cast
//        byte sum2 = b + c;

        int d = 42;
        float e = 43.0f;
        float sum3 = d + e;
        System.out.println("-- sum3: " + sum3);

        short s = 3;
        double f = 3759173.98d;
        double sum4 = s + f;
        System.out.println("-- sum4: " + sum4);

        //bad praxis
        int d2 = 42;
        float e2 = 43;
        float sum32 = d2 + e2;
        System.out.println("-- sum32: " + sum32);

        //??
        int i = 6 / 3;
        System.out.println("-- division, int result (mathematically): " + i);
        var j = 6 / 3;
        System.out.println("-- division, int result(mathematically) declared as var : " + j);
        double g = 6 / 3;
        System.out.println("-- division, int result (mathematically) declared as double: " + g);

        int twoPointFive = 10 / 4;
        System.out.println("-- division,twoPointFive: " + twoPointFive);
        double twoPointFiveAsDouble = 10 / 4;
        System.out.println("-- division,twoPointFive: " + twoPointFiveAsDouble);

        int twoPointFiveModulo= 10 % 4;
        System.out.println("-- division,twoPointFiveModulo: " + twoPointFiveModulo);
        double twoPointFiveAsDoubleModulo = 10 % 4;
        System.out.println("-- division,twoPointFiveAsDoubleModulo: " + twoPointFiveAsDoubleModulo);

        float twoPointFiveFloat = 10 / 4f;
        System.out.println("-- division,twoPointFiveFloat: " + twoPointFiveFloat);
        double twoPointFiveAsDoubleDouble = 10 / 4d;
        System.out.println("-- division,twoPointFive: " + twoPointFiveAsDoubleDouble);

        double twoPointFiveDoubleAgain = 10 / 4f;
        System.out.println("-- division,twoPointFiveDoubleAgain: " + twoPointFiveDoubleAgain);

        //This does not work - not widening but narrowing - requires type cast
//        float twoPointFiveFloatAgain = 10 / 4d;
//        System.out.println("-- division,twoPointFiveFloatAgain: " + twoPointFiveFloatAgain);

    }
}
