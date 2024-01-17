package com.mindflytech.education.designpatterns;

import java.time.Year;

/**
 * This class serves as a demonstration of how to call a builder from outside of its enclosing class
 */
public class BuilderPatternUser {
    public static void main(String[] args) {
        Year twoThousandEight = Year.of(2008);
        String joshuaBloch = "Joshua Bloch";
        Book effective = new Book.Builder("ISBN-1282646826", "Joshua Bloch: Effective Java")
                .withAuthor(joshuaBloch)
                .withPublished(twoThousandEight)
                .build();
        System.out.println("-- Effective Java created with the proper Builder: " + effective);

        //compile error
        //'com.mindflytech.education.designpatterns.Book' is not an enclosing class
//        new Book.WrongBuilder("ISBN-1282646826", "Joshua Bloch: Effective Java")

//        Book unusedBook = new Book();
//        System.out.println("-- auxiliary book object is needed because of the failed implementation " +
//                "of WrongBuilder: " + unusedBook);
//        Book.WrongBuilder wrongBuilder = unusedBook.new WrongBuilder("ISBN-1282646826", "Joshua Bloch: Effective Java");
//        Book actualBook = wrongBuilder.withPublished(twoThousandEight)
//                .withAuthor(joshuaBloch)
//                .build();
//        System.out.println("-- Effective Java created with the wrong implementation: " + actualBook);
    }
}
