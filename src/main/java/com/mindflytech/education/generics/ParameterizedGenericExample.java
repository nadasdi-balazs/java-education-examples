package com.mindflytech.education.generics;

import java.util.List;

public class ParameterizedGenericExample<T> {
    public void noTypeParameterDuplicateFirstElement(List<T> list) {
        T elm = list.get(0);
        list.add(0, elm);
    }

    public  static <T> void staticNoTypeParameterDuplicateFirstElement(List<T> list) {
        T elm = list.get(0);
        list.add(0, elm);
    }
}
