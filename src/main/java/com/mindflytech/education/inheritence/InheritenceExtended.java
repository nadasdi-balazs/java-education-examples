package com.mindflytech.education.inheritence;

public class InheritenceExtended extends InheritenceSuper {
    @Override
//    public ReturnExtended operation() {
    public ReturnSuper operation() {
        return new ReturnExtended("data", "other-data");
    }

    @Override
    public void operationTwo(ReturnSuper data) {
        System.out.println("-- received: " + data);
    }

    //This does not compile - return type not covariant
//    @Override
//    public ReturnSuperSuper operation() {
//        return new ReturnExtended("data", "other-data");
//    }
}
