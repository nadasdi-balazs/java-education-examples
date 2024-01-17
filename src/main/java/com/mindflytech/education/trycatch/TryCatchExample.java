package com.mindflytech.education.trycatch;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

public class TryCatchExample {
    public static void main(String[] args) {
        TryCatchExample tryCatch = new TryCatchExample();
        tryCatch.run();
        int result = tryCatch.runWithFinallyReturn();
        System.out.println("-- result is: '" + result + "'");
        System.out.println("after entire example run");
        try {
            tryCatch.runTryFinally();
        } catch (BusinessException e) {
            System.out.println("catch block in main, exception from runTryFinally, message: '"
                    + e.getMessage() + "'");
        }
    }

    private static class BusinessException extends Exception {
        public BusinessException(String alwaysThrown) {
            super(alwaysThrown);
        }
    }

    private void run() {
        try {
            System.out.println("inside try-catch 1");
        } catch (Exception e) {
            System.out.println("catch block 1");
        } /*catch(RuntimeException e) {
            //Already been caught - compile error
        }*/
//        throw new Exception("this failed");
        try {
            System.out.println("inside try-catch 2");
            if (System.nanoTime() % 4 == 0) {
                throw new BusinessException("sometimes thrown");
            }
        } catch (BusinessException | ArithmeticException e) {
            System.out.println("catch block 2");
        }
        //TODO: doesn't check ArithmeticException - why?

        try (PrintWriter writer = new PrintWriter("test.txt");
             Connection connection = null) {
            System.out.println("inside try-with-resources");
            writer.append("I append another text");
            writer.flush();
        } catch (FileNotFoundException e) {
            System.err.println("file not found!: " + e.getMessage() + " in catch block 1 of try-with-resources");
        } catch (SQLException e) {
            System.err.println("file not found!: " + e.getMessage() + " in catch block 2 of try-with-resources");
        }
    }

    private int runWithFinallyReturn() {
        try {
            System.out.println("inside try-catch 3");
            return 2;
        } finally {
            System.out.println("inside finally 1");
            //unexpected behavior
            return 3;
        }
    }

    private void runTryFinally() throws BusinessException {
        try {
            System.out.println("inside try-catch 4");
            throw new BusinessException("always thrown");
        } finally {
            System.out.println("inside finally 2");
        }
    }
}
