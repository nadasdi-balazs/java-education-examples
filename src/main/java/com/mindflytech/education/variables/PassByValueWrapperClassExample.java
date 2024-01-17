package com.mindflytech.education.variables;

import java.util.ArrayList;
import java.util.List;

public class PassByValueWrapperClassExample {
    //Don't do this in real life - we don't expect changes on the caller side, but still,
    //don't reassign parameter

    /**
     * This method prints the given value, increments it
     * and prints it again
     * @<code>given++;</code>
     * {@link com.mindflytech.education.variables.PassByValueExample}
     * @author Balazs Nadasdi
     * @param given the integer given
     * @return void
     */
    public void giveMeAnIntAndModify(int given) {
        System.out.println("---- I was given an int: " + given);
        given++;
        System.out.println("---- I modified the int what was given: " + given);
    }

    public void giveMeAnIntegerAndModify(Integer given) {
        System.out.println("---- I was given an Integer: " + given);
        given++;
        System.out.println("---- I modified the Integer what was given: " + given);
    }

    public static void main(String[] args) {
        PassByValueWrapperClassExample wrappers = new PassByValueWrapperClassExample();
        int willBeGiven = 27;
        System.out.println("-- calling int parameter method with int argument: " + willBeGiven);
        wrappers.giveMeAnIntAndModify(willBeGiven);
        System.out.println("-- after calling int parameter method with int argument: " + willBeGiven);

        Integer integerArgument = 27;
        System.out.println("-- calling int parameter method with Integer argument: " + integerArgument);
        wrappers.giveMeAnIntAndModify(integerArgument);
        System.out.println("-- after calling int parameter method with Integer argument: " + integerArgument);

        willBeGiven = 27;
        System.out.println("-- calling Integer parameter method with int argument: " + willBeGiven);
        wrappers.giveMeAnIntegerAndModify(willBeGiven);
        System.out.println("-- after calling Integer parameter method with int argument: " + willBeGiven);

        integerArgument = 27;
        System.out.println("-- calling Integer parameter method with Integer argument: " + integerArgument);
        wrappers.giveMeAnIntegerAndModify(integerArgument);
        System.out.println("-- after calling Integer parameter method with Integer argument: " + integerArgument);

        long longValue = 2268242437676535888L;
        int intValue = (int) longValue;
        System.out.println("-- casted long to int: " + intValue);

        var someValue = "what is my type?";
        var listOfList = new ArrayList<List<String>>();
        System.out.println("-- listOfList's inferred type: " + listOfList.getClass());
    }
}
