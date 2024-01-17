package com.mindflytech.education.basics;

public class FieldAndVariables {
    private static int classVariable = 30;
    private static int instanceCount = 0;

    private int integerField = 27;

    public FieldAndVariables() {
        System.out.println("-- constructor called, instance count before: " + instanceCount);
        int something = 42;
        integerField = 28;
        instanceCount++;
        System.out.println("-- constructor called, instance count after: " + instanceCount);
    }

    public static void main(String[] args) {
        for(String arg : args) {
            System.out.println(arg);
            int intVariable = 29;
        }
        char euro = '\u20AC';
        char euroPlusOne = (char)(euro + 1);
        System.out.println("-- euroPlusOne: " + euroPlusOne);
        int hexCodeOfEuro = 0x20AC;
        System.out.println("-- I print a euro sign: " + euro);
        System.out.println("-- are the euro char value and its hexcode equal? " + (euro == hexCodeOfEuro));
        int intVariable = 28;
        System.out.println("-- intVariable is: " + intVariable);

//        System.out.println("-- value of integerField: " + integerField);
        System.out.println("-- value of fieldAndVariablesIntegerField: " + classVariable);

        FieldAndVariables fieldAndVariables = new FieldAndVariables();
        int fieldAndVariablesIntegerField = fieldAndVariables.getIntegerField();
        System.out.println("-- fieldAndVariablesIntegerField: " + fieldAndVariablesIntegerField);

        FieldAndVariables fieldAndVariables2 = new FieldAndVariables();
        System.out.println("boo");
    }

    public int getIntegerField() {
        return integerField;
    }
}
