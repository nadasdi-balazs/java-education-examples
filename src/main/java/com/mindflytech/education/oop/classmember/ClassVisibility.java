package com.mindflytech.education.oop.classmember;

class YetAnotherTopLevelClass {

}

//Modifier 'private' not allowed here
//Modifier 'protected' not allowed here
public class ClassVisibility {
    private class NestedClass {

    }
}

//Class 'OtherTopLevelClass' is public, should be declared in a file named 'OtherTopLevelClass.java'
//Modifier 'protected' not allowed here
//Modifier 'private' not allowed here
class OtherTopLevelClass {
    private transient   int privateInt = 27;

    class Inner {

    }
}

interface CanItHasProtectedMembers {
    //Modifier 'public' is redundant for interface members
    public int publicInt = 43;

    //Modifier 'protected' not allowed here
//    protected int protectedString = "protected string";

    String implicitlyPublicString = "implicitly public string";

    //Modifier 'private' not allowed here
//    private String privateString = "private string";

    //Modifier 'public' is redundant for interface members
    public void publicMethod();

    void implicitlyPublicMethod();

    //Modifier 'protected' not allowed here
//    protected void protectedMethod();

    //Private methods in interfaces should have a body
//    private void privateMethod();
    private void privateMethod() {
        System.out.println("I highly doubt that anyone can call this method");
    }
}