package com.mindflytech.education.polymorphism;

//This example is taken from https://docs.oracle.com/javase/tutorial/java/IandI/override.html
public class OverrideHideStaticInstanceExample {
    public static void main(String[] args) {
        Cat myCat = new Cat();
        Animal myAnimal = myCat;
        myAnimal.testInstanceMethod();
        Animal.testClassMethod();
        myAnimal.testClassMethod();
        myCat.testClassMethod();
    }
}

class Animal {
    public static void testClassMethod() {
        System.out.println("The static method in Animal");
    }
    public void testInstanceMethod() {
        System.out.println("The instance method in Animal");
    }
}

class Cat extends Animal {
    public static void testClassMethod() {
        System.out.println("The static method in Cat");
    }
    public void testInstanceMethod() {
        System.out.println("The instance method in Cat");
    }
}

class Horse {
    public String identifyMyself() {
        return "I am a horse.";
    }
}

interface Flyer {
    default public String identifyMyself() {
        return "I am able to fly.";
    }

    default public String identify() {
        return "I am able to fly.";
    }
}

interface Mythical {
    default public String identifyMyself() {
        return "I am a mythical creature.";
    }
}

class Pegasus extends Horse implements Flyer, Mythical {
    public static void main(String... args) {
        Pegasus myApp = new Pegasus();
        System.out.println(myApp.identifyMyself());
        System.out.println(myApp.identify());
        Flyer flyer = myApp;
        System.out.println(flyer.identifyMyself());
        System.out.println(flyer.identify());
        myApp.identifyAsSupers();
    }

    public void identifyAsSupers() {
        System.out.println(Flyer.super.identify());
        System.out.println(Flyer.super.identifyMyself());
        System.out.println(Mythical.super.identifyMyself());
    }
}

interface Animal2 {
    default public String identifyMyself() {
        return "I am an animal.";
    }
}

interface EggLayer extends Animal2 {
    default public String identifyMyself() {
        return "I am able to lay eggs.";
    }
}

interface FireBreather extends Animal2 {
//    default public String identifyMyself() {
//        return "I am able to lay eggs.";
//    }
}

class Dragon implements EggLayer, FireBreather {
//class Dragon implements FireBreather, EggLayer {
    public static void main (String... args) {
        Dragon myApp = new Dragon();
        System.out.println(myApp.identifyMyself());
    }
}

interface Mammal {
    String identifyMyself();
}

class Horse2 {
    public String identifyMyself() {
        return "I am a horse.";
    }
}

class Mustang extends Horse2 implements Mammal {
    public static void main(String... args) {
        Mustang myApp = new Mustang();
        System.out.println(myApp.identifyMyself());
    }
}