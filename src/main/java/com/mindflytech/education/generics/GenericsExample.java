package com.mindflytech.education.generics;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mindflytech.util.Utils.prefixedPrintList;
import static com.mindflytech.util.Utils.printList;

public class GenericsExample {
    /**
     * Oracle documentation at https://docs.oracle.com/javase/tutorial/java/generics/bounded.html
     * claims that if a generic type has multiple bounds and one of them is a class and the rest are
     * interfaces, the class must always be mentioned first
     */
    class A { /* ... */ }
    interface B { /* ... */ }
    interface C { /* ... */ }
    class D <T extends A & B & C> { /* ... */ }
    //compile error
    //Interface expected here
//    class E <T extends B & A & C> { /* ... */ }

    class Box<T> {
        // T stands for "Type"
        private T t;

        public void set(T t) { this.t = t; }
        public T get() { return t; }
    }

    static <T> T pick(T a1, T a2) {
        return a2;
    }

    void processStringList(List<String> stringList) {
        System.out.println("-- processStringList's argument's type is: " + stringList.getClass()
                + ", result of (stringList instanceof List<String>): " + (stringList instanceof List<String>));
        prefixedPrintList("String list being processed: ", stringList);
    }
    

    public static void main(String[] args) {
        GenericsExample example = new GenericsExample();
        example.typeParameterDemonstration();
        example.exactTypesDemonstration();
        example.typeInferenceDemonstration();
        example.typeInferenceWithTypeWitness();
        example.runBoxExample();
    }

    private <E> Box<E> runBoxExample() {
        Box<E> genericBox = new Box<E>();
        return genericBox;
    }

    public <U extends Number> void inspect(U u){

    }

    private void typeInferenceWithTypeWitness() {
        //type witness is optional: Collections.<String>emptyList();
        List<String> listOne = Collections.emptyList();
        //type witness is mandatory because we use ? (unknown type) wildcard in the declaration
        List<?> listTwo = Collections.<String>emptyList();
        //compile error
        //Required type:        //capture of ?
        //Provided:        //String
//        listTwo.add("string");

        List<? super String> listTwoWithWildcard = Collections.<String>emptyList();
        //throws runtime exception: UnsupportedOperationException because AbstractList denies
        //the interface of the superclass (note: this is a code smell)
//        listTwoWithWildcard.add("string");

        List<?> listThree = Collections.emptyList();
//        listThree.add(new Object());

        processStringList(Collections.emptyList());
        processStringList(Collections.<String>emptyList());
    }

    private void typeInferenceDemonstration() {
        Serializable picked = pick("String", new ArrayList<String>());
    }

    public static <U> void outputBoxes(java.util.List<Box<U>> boxes) {
        int counter = 0;
        for (Box<U> box: boxes) {
            U boxContents = box.get();
            System.out.println("Box #" + counter + " contains [" +
                    boxContents.toString() + "]");
            counter++;
        }
    }

    private void exactTypesDemonstration() {
        StringSuperClass one = StringSuperClass.of("one");
        StringSuperClass two = StringSuperClass.of("two");
        StringSuperClass three = StringSuperClass.of("three");
        StringSuperClass four = StringSuperClass.of("four");
        StringSubClass fourSub = StringSubClass.of("four-subclass");
        List<StringSuperClass> superSuperList = Lists.newArrayList(one, two, three, four);
        prefixedPrintList("-- list declared and only containing superclass elements: ", superSuperList);

        //compile error
        //Required type:        //List              //<StringSubClass>
        //Provided:             //ArrayList        //<StringSuperClass>
        //no instance(s) of type variable(s) exist so that StringSuperClass conforms to StringSubClass
        // inference variable E has incompatible bounds: equality constraints: StringSubClass lower bounds: StringSuperClass
//        List<StringSubClass> subSuperList = Lists.newArrayList(one, two, three, four);

//        List<StringSubClass> subSuperList = new ArrayList<>();
        //compile error
        //Required type:        //StringSubClass
        //Provided:             //StringSuperClass
//        subSuperList.add(one);

//        List<? extends StringSuperClass> subSuperList = new ArrayList<>();
        //compile error
        //Required type:        //capture of ? extends StringSuperClass
        //Provided:             //StringSuperClass
//        subSuperList.add(one);
//        List<? extends CharSequence> subSuperList = new ArrayList<>();
//        subSuperList.<StringSuperClass>add(one);

        List<? super StringSuperClass> subSuperWildcardList = new ArrayList<>();
        subSuperWildcardList.add(one);
        subSuperWildcardList.add(two);
        subSuperWildcardList.add(three);
        subSuperWildcardList.add(four);
        subSuperWildcardList.add(fourSub);
        prefixedPrintList("-- list declared as ? super StringSuperClass, containing" +
                " both superclass and subclass elements", subSuperWildcardList);

        List<StringSuperClass> superSubList = new ArrayList<>();
        superSubList.add(one);
        //accepted!
        //superclass generic accepts subclass instance, subclass generic doesn't accept superclass
        superSubList.add(fourSub);
        prefixedPrintList("-- list declared as superclass list, containing both " +
                "super and subclass elements", superSubList);

        List<? super CharSequence> charSeqList = new ArrayList<>();
        charSeqList.add(one);
        charSeqList.add(fourSub);
        charSeqList.add("five-string");
        prefixedPrintList("-- list declared as ? super CharSequence list, accepts" +
                " superclass, subclass and string: ", charSeqList);

//        List<? extends StringSubClass> subSuperListWildcard = new ArrayList<>();
        //compile error
        //Required type:        //capture of ? extends StringSubClass
        //Provided:        //StringSubClass
//        subSuperListWildcard.add(fourSub);
        //uaz, csak   Provided:        StringSuperClass
//        subSuperListWildcard.add(one);

        List<? super StringSubClass> subSuperListSuperWildcard = new ArrayList<>();
        subSuperListSuperWildcard.add(fourSub);
        //compile error
        //Required type:        //capture of ? super StringSubClass
        //Provided:        //StringSuperClass
        //superclass IS NOT a subclass
//        subSuperListSuperWildcard.add(one);

//        List<? extends StringSubClass> subSuperListExtendsWildcard = new ArrayList<>();
        //compile error
        //Required type:        //capture of ? extends StringSubClass
        //Provided:             //StringSubClass
//        subSuperListExtendsWildcard.add(fourSub);
//        subSuperListExtendsWildcard.add(one);
    }

    private void typeParameterDemonstration() {
        List<String> list = Lists.newArrayList("one", "two", "three", "four");
        duplicateFirstElement(list);

        //We need type parameters either at the beginning of the method (when the class is
        //not parameterized), or in the class declaration (in this case it is not necessary
        //at method level because the type arguments are already known to the compiler
        ParameterizedGenericExample<String> parameterized = new ParameterizedGenericExample<>();
        parameterized.noTypeParameterDuplicateFirstElement(list);

        printList(list);
    }

    public <T> void duplicateFirstElement(List<T> list) {
        T elm = list.get(0);
        list.add(0, elm);
    }

    //Compiler error
    //Cannot resolve symbol 'T'
//    public void noTypeParameterDuplicateFirstElement(List<T> list) {
//        T elm = list.get(0);
//        list.add(0, elm);
//    }

    //compile error
    //Operator '>' cannot be applied to 'T', 'T'
//    public static <T> int countGreaterThan(T[] anArray, T elem) {
//        int count = 0;
//        for (T e : anArray)
//            if (e > elem)  // compiler error
//                ++count;
//        return count;
//    }

    /**
     * Compares to Numbers based on Number.intValue
     * Note that https://docs.oracle.com/javase/tutorial/java/generics/boundedTypeParams.html
     * suggests to use <T extends Comparable<T>> which is also a good solution, but then T
     * has to implement the Comparable interface (and we will compare with
     * if (e.compareTo(elem) > 0)
     *
     * @param anArray whose elements are to be compared against the given element
     * @param elem the element against which we will compare the array
     * @return the number of elements that are greater than elem
     * @param <T>
     */
    public static <T extends Number> int countGreaterThan(T[] anArray, T elem) {
        int count = 0;
        for (T e : anArray)
            if (e.intValue() > elem.intValue())  // compiler error
                ++count;
        return count;
    }
}
