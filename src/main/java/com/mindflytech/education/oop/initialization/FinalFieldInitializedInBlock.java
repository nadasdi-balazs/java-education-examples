package com.mindflytech.education.oop.initialization;

import lombok.ToString;

@ToString
public class FinalFieldInitializedInBlock {
    private final String stringField;
    private final int intField;
    private static int mutable;
    //That's why we don't use initialization blocks
    private String uglyTemporaryVariableToCircumventInitializationBlockProblem;

    {
        /*
            Don't do this, I am only experimenting with initialization blocks here.
            A much nicer solution is to create two constructors as follows:
                public FinalFieldInitializedInBlock(int intField) {
                    this(intField, "machine_initialized_" + System.currentTimeMillis());
                }

                public FinalFieldInitializedInBlock(int intField, String stringField) {
                    this.intField = intField;
                    this.stringField = stringField;
                }
         */
        if(uglyTemporaryVariableToCircumventInitializationBlockProblem == null) {
            this.stringField = "machine_initialized_" + System.currentTimeMillis();
        } else {
            this.stringField = uglyTemporaryVariableToCircumventInitializationBlockProblem;
        }
    }

    public FinalFieldInitializedInBlock(int intField) {
        this.intField = intField;
        synchronized (this) {
            System.out.println("-- in a constructor, within synchronized");
            System.out.println("-- mutable before: " + mutable);
            mutable++;
            System.out.println("-- mutable after: " + mutable);
        }
    }

    public FinalFieldInitializedInBlock(int intField, String stringField) {
        this.intField = intField;
        //Compile error
        //Variable 'stringField' might already have been assigned to
//        this.stringField = stringField;
        this.uglyTemporaryVariableToCircumventInitializationBlockProblem = stringField;
    }

    public static void main(String[] args) {
        FinalFieldInitializedInBlock blockExample = new FinalFieldInitializedInBlock(27);
        FinalFieldInitializedInBlock blockExampleWithStringGiven = new FinalFieldInitializedInBlock(27, "I-am-the-joker");
        System.out.println("-- blockExample: " + blockExample);
        System.out.println("-- blockExampleWithStringGiven: " + blockExampleWithStringGiven);
    }
}
