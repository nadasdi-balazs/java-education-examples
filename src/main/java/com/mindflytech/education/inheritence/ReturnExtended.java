package com.mindflytech.education.inheritence;

public class ReturnExtended extends ReturnSuper {
    private String mutableOtherData;

    public ReturnExtended(String data) {
        super(data);
    }
    public ReturnExtended(String data, String otherData) {
        super(data);
        this.mutableOtherData = otherData;
    }
}
