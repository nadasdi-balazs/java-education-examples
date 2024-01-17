package com.mindflytech.education.oop.abstraction;

import com.mindflytech.util.Utils;

public class PartiallyImplementedAbstractClassExample {
    static private abstract class AbstractClassWithTwoAbstractMethods {
        abstract void doOneThing();
        abstract String doOtherThing(String input);

        protected String doThirdThingForFree(String input) {
            String currentDateTime = Utils.generateCurrentDateTime();
            System.out.println("-- input received at: " + currentDateTime);
            String output = currentDateTime + "_" + input;
            return output;
        }
    }

    static private abstract class SomeImplementation extends AbstractClassWithTwoAbstractMethods {
        final String parameter;

        private SomeImplementation(String parameter) {
            this.parameter = parameter;
        }

        @Override
        public void doOneThing() {
            System.out.println("-- doOneThing doesn't know what to do, so it calls superclass' abstract" +
                    " method");
            doThirdThingForFree(parameter);
            System.out.println("-- it also calls the other abstract method doOneThing()");
            String result = doOtherThing(parameter);
            System.out.println("-- in doOneThing, we called doOtherThing with: '" + parameter + "', which " +
                    "resulted in: '" + result + "'");
        }
    }

    static private class FinalImplementation extends SomeImplementation implements Comparable<FinalImplementation> {
        private FinalImplementation(String parameter) {
            super(parameter);
            //compiler error
            //Cannot reference 'SomeImplementation.parameter' before supertype constructor has been called
//            super(parameter);
        }

        @Override
        String doOtherThing(String input) {
            System.out.println("-- I will finally implement everything");
            //calling it with super. is unnecessary
//            String result = super.doThirdThingForFree(input);
            String result = super.doThirdThingForFree(input);
            return result;
        }

        @Override
        public int compareTo(FinalImplementation other) {
            String ourParameter = this.parameter;
            String otherParameter = other.parameter;
            return ourParameter.compareTo(otherParameter);
        }
    }

    public static void main(String[] args) {
        FinalImplementation implementation = new FinalImplementation("I_give_you_some_argument");
        System.out.println("-- will call implementation.doOneThing();");
        implementation.doOneThing();
        System.out.println("-- will call implementation.doOtherThing");
        String resultOne = implementation.doOtherThing("other-argument");
        System.out.println("-- this call resulted in: '" + resultOne + "'");
        System.out.println("-- will call implementation.doThirdThingForFree");
        String resultTwo = implementation.doThirdThingForFree("third-dummy-argument");
        System.out.println("-- this call resulted in: '" + resultTwo + "'");

        SomeImplementation anonymousImplementation = new SomeImplementation("another-dummy-argument") {
            @Override
            String doOtherThing(String input) {
                String result = Utils.generateRandomString() + "_" + input;
                return result;
            }
        };
        anonymousImplementation.doOneThing();
        String otherResult = anonymousImplementation.doOtherThing("other-arg");
        System.out.println("-- anonymousImplementation.doOtherThing resulted in: '" + otherResult + "'");
        String thirdResult = anonymousImplementation.doThirdThingForFree("third-arg");
        System.out.println("-- anonymousImplementation.doThirdThingForFree resulted in: '" + thirdResult + "'");
    }
}
