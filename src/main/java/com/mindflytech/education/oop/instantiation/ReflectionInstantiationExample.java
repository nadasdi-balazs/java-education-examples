package com.mindflytech.education.oop.instantiation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectionInstantiationExample {
    static class ToBeInstantiated {
        ToBeInstantiated() {
            System.out.println("-- ToBeInstantiated constructor called");
        }

//        ToBeInstantiated(String argument) {
//            System.out.println("-- ToBeInstantiated constructor called with argument: '" + argument + "'");
//        }
    }
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<ToBeInstantiated> instantiableClass = ToBeInstantiated.class;
        //Deprecated, still uses the constructor under the hood
        instantiableClass.newInstance();
        Constructor<?>[] constructors = instantiableClass.getDeclaredConstructors();
        for(Constructor<?> constructor : constructors) {
            Object instance = constructor.newInstance();
//            constructor.newInstance("argument from reflection");
        }
    }
}
