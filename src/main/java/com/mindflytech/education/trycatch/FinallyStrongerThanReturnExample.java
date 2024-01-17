package com.mindflytech.education.trycatch;

public class FinallyStrongerThanReturnExample {
    public static void main(String[] args) {
        FinallyStrongerThanReturnExample example = new FinallyStrongerThanReturnExample();
        example.callFinallyMethod();
    }

    private void callFinallyMethod() {
        String returnFromFinally = returnFromFinally();
        System.out.println("-- returnFromFinally() returned with: '" + returnFromFinally + "'");
    }

    private String returnFromFinally() {
        try {
            return "from-try-block";
        } finally {
            return "from-finally";
        }
    }
}
