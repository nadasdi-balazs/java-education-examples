package com.mindflytech.education.polymorphism;

import com.mindflytech.education.controlstructures.ControlStructureUtils;

import javax.xml.transform.TransformerException;
import com.sun.org.apache.xpath.internal.XPathException;

public class CovariantReturnTypeExample {

}

class SuperClass {

}

class SubClass extends SuperClass {

}

class CallerSuperClass {
    public SuperClass method() {
        return new SuperClass();
    }

    public int returnsInt() {
        return 27;
    }

    public Integer returnsInteger() {
        return Integer.valueOf(27);
    }

    public Number returnsNumber(Integer number) {
        //works with 27, 27L or 27d as well
        return 27d;
    }

    public void throwsXPathException() throws TransformerException {
        if(ControlStructureUtils.isMillisDividableByFour()) {
            throw new XPathException("this is an XPathException");
        }
    }

    public void throwsTransformerException() throws TransformerException {
        if(ControlStructureUtils.isMillisDividableByFour()) {
            throw new TransformerException("this is an TransformerException");
        }
    }
}

class CallerSubClass extends CallerSuperClass {
    @Override
    public SubClass method() {
        return new SubClass();
    }

    //compile error:
    //'returnsInt()' in 'com.mindflytech.education.polymorphism.CallerSubClass'
    // clashes with 'returnsInt()' in 'com.mindflytech.education.polymorphism.CallerSuperClass';
    // attempting to use incompatible return type
//    @Override
//    public Integer returnsInt() {
//        return 27;
//    }

    //compile error
    //'returnsInteger()' in 'com.mindflytech.education.polymorphism.CallerSubClass' clashes with
    // 'returnsInteger()' in 'com.mindflytech.education.polymorphism.CallerSuperClass';
    // attempting to use incompatible return type
//    @Override
//    public int returnsInteger() {
//        return Integer.valueOf(27);
//    }

//    @Override
//    public Number returnsInteger() {
//        return Integer.valueOf(27);
//    }

    @Override
    public Integer returnsNumber(Integer number) {
        //only works with 27, 27L or 27d fails
        return 27;
    }

    //compile error
    //'throwsXPathException()' in 'com.mindflytech.education.polymorphism.CallerSubClass' clashes with
    // 'throwsXPathException()' in 'com.mindflytech.education.polymorphism.CallerSuperClass';
    // overridden method does not throw 'javax.xml.transform.TransformerException'
    @Override
    public void throwsXPathException() throws XPathException {
        if(ControlStructureUtils.isMillisDividableByFour()) {
            throw new XPathException("this is an XPathException");
        }
    }


}