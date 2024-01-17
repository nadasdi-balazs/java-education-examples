package com.mindflytech.education.typeconversion;

import org.springframework.util.ClassUtils;

import java.util.HashMap;
import java.util.Map;

import com.google.common.primitives.Primitives;

public class WideningExample {
    private static final Map<Class<?>, Class<?>> WRAPPER_TYPE_MAP;
    static {
        WRAPPER_TYPE_MAP = new HashMap<>(16);
        WRAPPER_TYPE_MAP.put(Integer.class, int.class);
        WRAPPER_TYPE_MAP.put(Byte.class, byte.class);
        WRAPPER_TYPE_MAP.put(Character.class, char.class);
        WRAPPER_TYPE_MAP.put(Boolean.class, boolean.class);
        WRAPPER_TYPE_MAP.put(Double.class, double.class);
        WRAPPER_TYPE_MAP.put(Float.class, float.class);
        WRAPPER_TYPE_MAP.put(Long.class, long.class);
        WRAPPER_TYPE_MAP.put(Short.class, short.class);
        WRAPPER_TYPE_MAP.put(Void.class, void.class);
    }
    public static void main(String[] args) {
        int a = 6;
        int b = 8;
        long sum = a + b;
        System.out.println("-- is sum primitive: " + WRAPPER_TYPE_MAP.containsKey(sum));
//        Primitives.isWrapperType();
    }
}
