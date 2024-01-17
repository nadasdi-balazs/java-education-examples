package com.mindflytech.education.oop.constructor;

import lombok.ToString;

@ToString(callSuper = true)
public class SubClassOverridesParentMethodCalledFromParentConstructor extends SuperClassWithNonPrivateMethodCallInConstuctor{
    public SubClassOverridesParentMethodCalledFromParentConstructor(String parameter) {
        super(parameter);
        System.out.println("-- SubClassOverridesParentMethodCalledFromParentConstructor constructor finished execution");
    }

    @Override
    void verifyString(String parameter) {
        System.out.println("-- subclass.verifyString called with parameter: '" + parameter
                + "', will throw exception if there is NO @ ! or ' in it");
        boolean containsAt = parameter.contains("@");
        boolean containsExclamation = parameter.contains("!");
        boolean containsApostrophe = parameter.contains("'");
        //overridden parent method will cause error in parent initialization!
        if(!containsAt || !containsExclamation || !containsApostrophe) {
            String message = "Class cannot be created with a parameter containg @, ! or ': " + parameter;
            System.err.println(message);
            throw new IllegalArgumentException(message);
        }
    }
}
