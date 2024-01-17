package com.mindflytech.education.generics;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Random;

public class IterableRandomGenerator<T> implements Iterable<T>{

    static abstract class RandomIterator<T> implements Iterator<T> {
        private final Random random = new Random(System.currentTimeMillis());

        public RandomIterator() {
//            Class<T> persistentClass = (Class<T>)
//                    ((ParameterizedType)getClass().getGenericSuperclass())
//                            .getActualTypeArguments()[0];
//            System.out.println("-- persistentClass: " + persistentClass);
            Class<? extends RandomIterator> superClass = getClass();
            System.out.println("-- descendant's current class: " + superClass);
            Class<?> superclass = superClass.getSuperclass();
            System.out.println("-- current class: " + superclass);
            Type genericSuperclass = superClass.getGenericSuperclass();
            System.out.println("-- type before cast: " + genericSuperclass);
            ParameterizedType parameterizedType = (ParameterizedType)genericSuperclass;
            System.out.println("-- our current type: " + parameterizedType);
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public T next() {
            return null;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new RandomIterator(){};
    }
}

class RandomRunner {
    public static void main(String[] args) {
        IterableRandomGenerator<Integer> randomGenerator = new IterableRandomGenerator<>();
        Iterator<Integer> iterator = randomGenerator.iterator();
    }
}
