package com.mindflytech.education.oop.classmember;

import com.mindflytech.education.oop.enums.EnumRefactoringExample;
import com.mindflytech.education.oop.initialization.FinalFieldInitializedInBlock;
import lombok.Data;
import lombok.ToString;

public class EnumExample {
    interface DoStuff {
        void execute(String argument);
    }

    enum TriStateEnumLevel implements DoStuff{
        TRUE,
        FALSE,
        UNKNOWN;

        @Override
        public void execute(String argument) {
            System.out.println("-- TriStateEnumLevel.execute runs, this.name(), this.ordinal(), this.getClass(): '"
                    + this.name() + "', '" + this.ordinal() + "', '" + this.getClass() + "'");
        }
    }

    //compile error
    //Cannot inherit from enum 'com.mindflytech.education.oop.classmember.EnumExample.TriStateEnumLevel'
//    enum ChildOfEnum extends TriStateEnumLevel {
//    }

    @ToString
    enum TriStateConstantLevel implements DoStuff{
        TRUE("true") {
            @Override
            public void execute(String argument) {
                System.out.println("-- TriStateConstantLevel.TRUE.execute runs, this.name(), this.ordinal(), this.getClass(): '"
                        + this.name() + "', '" + this.ordinal() + "', '" + this.getClass() + "'");
                super.execute(argument);
            }

            @Override
            public void doStuff(String argument) {
                System.out.println("-- enum constant TRUE does stuff with argument: '" + argument + "'");
                printStuff();
            }

            public void printStuff() {
                System.out.println("-- I live in TRUE");
            }
        },
        FALSE("false"){
            @Override
            public void execute(String argument) {
                System.out.println("-- TriStateConstantLevel.FALSE.execute runs, this.name(), this.ordinal(), this.getClass(): '"
                        + this.name() + "', '" + this.ordinal() + "', '" + this.getClass() + "'");
                super.execute(argument);
            }
        },
        UNKNOWN("unknown");

        private final String otherName;

        TriStateConstantLevel(String constructorArg) {
            this.otherName = constructorArg;
        }

        @Override
        public void execute(String argument) {
            System.out.println("-- TriStateConstantLevel.execute runs, this.name(), this.ordinal(), this.getClass(): '"
                    + this.name() + "', '" + this.ordinal() + "', '" + this.getClass() + "'");
        }

        public void doStuff(String argument) {
            System.out.println("-- enum does stuff with argument: '" + argument + "'");
        }
    }

    public static void main(String[] args) {
        TriStateEnumLevel enumLevel = TriStateEnumLevel.UNKNOWN;
        enumLevel.execute("argument");

        TriStateConstantLevel constantLevel = TriStateConstantLevel.TRUE;
        constantLevel.execute("argument");
        constantLevel.doStuff("another-argument");
        //compile error
        //Cannot resolve method 'printStuff' in 'TriStateConstantLevel'
//        TriStateConstantLevel.TRUE.printStuff();

        TriStateConstantLevel unknown = TriStateConstantLevel.valueOf("UNKNOWN");
        System.out.println("-- is it unknown? " + unknown);

        EnumRefactoringExample example = new EnumRefactoringExample();
        //compile error
        //'fetchServerRouteBefore(com.mindflytech.education.oop.enums.EnumRefactoringExample.InstallationBefore)'
        // has private access in 'com.mindflytech.education.oop.enums.EnumRefactoringExample'
//        example.fetchServerRouteBefore();
    }
    
}
