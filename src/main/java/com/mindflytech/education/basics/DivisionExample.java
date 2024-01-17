package com.mindflytech.education.basics;

import java.math.BigDecimal;

import static java.math.MathContext.DECIMAL128;

public class DivisionExample {
    public static void main(String[] args) {
        double ten = 10.0;
        int three = 3;
        double result = ten / three;
        System.out.println("-- result of 10 / 3 is: " + result);
        BigDecimal resultAsBigDecimal = BigDecimal.valueOf(ten / three);
        System.out.println("-- resultAsBigDecimal of 10 / 3 is: " + resultAsBigDecimal);

        BigDecimal tenAsBd = BigDecimal.valueOf(ten);
        BigDecimal threeAsBd = BigDecimal.valueOf(three);
//        BigDecimal dividedPunctual = tenAsBd.divide(threeAsBd);
//        System.out.println("-- dividedPunctual of 10 / 3 is: " + dividedPunctual);
        BigDecimal dividedPunctual = tenAsBd.divide(threeAsBd, DECIMAL128);
        System.out.println("-- dividedPunctual of 10 / 3 is: " + dividedPunctual);
    }
}
