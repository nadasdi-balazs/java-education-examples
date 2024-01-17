package com.mindflytech.education.inheritence;

public class InheritenceSuper {
    public ReturnSuper operation() {
        return new ReturnSuper("data");
    }

    public void operationTwo(ReturnSuper data) {
        System.out.println("-- received: " + data);
    }
}
