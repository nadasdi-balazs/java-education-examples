package com.mindflytech.education.designpatterns;

import lombok.ToString;

import java.time.Year;

public class BuilderPattern {
    //These classes are derived from https://blogs.oracle.com/javamagazine/post/exploring-joshua-blochs-builder-design-pattern-in-java
    //which article used Joshua Bloch: Effective Java as source
    //However, I simplified the example a bit
}

@ToString
class Book {
    private final String isbn;
    private final String title;
    private final String author;
    private final Year published;

    /**
     * This constructor is only needed because of WrongBuilder, otherwise this should not have been
     * written
     */
//    Book() {
//        this.isbn = "";
//        this.title = "";
//        this.author = "";
//        this.published = Year.of(-2);
//    }

    private Book(Builder builder) {
        this.isbn = builder.isbn;
        this.title = builder.title;
        this.author = builder.author;
        this.published = builder.published;
    }

//    private Book(WrongBuilder builder) {
//        this.isbn = builder.isbn;
//        this.title = builder.title;
//        this.author = builder.author;
//        this.published = builder.published;
//    }

    public static void main(String[] args) {
        Book effective = new Builder("ISBN-1282646826", "Joshua Bloch: Effective Java")
                .withAuthor("Joshua Bloch")
                .withPublished(Year.of(2008))
                .build();

        Book effectiveJavaAgain = new Builder("ISBN-1282646826", "Joshua Bloch: Effective Java")
                .withAuthor("Joshua Bloch")
                .withPublished(2008)
                .build();
    }

    public static class Builder {
        private final String isbn;
        private final String title;
        private String author;
        private Year published;

        public Builder(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }

        public Builder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder withPublished(int publishedYear) {
            this.published = Year.of(publishedYear);
            return this;
        }

        public Builder withPublished(Year published) {
            this.published = published;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }

    /**
     * Wrong example on purpose - can't instantiate without instantiating Book class itself
     */
//    public class WrongBuilder {
//        private final String isbn;
//        private final String title;
//        private String author;
//        private Year published;
//
//        public WrongBuilder(String isbn, String title) {
//            this.isbn = isbn;
//            this.title = title;
//        }
//
//        public WrongBuilder withAuthor(String author) {
//            this.author = author;
//            return this;
//        }
//
//        public WrongBuilder withPublished(Year published) {
//            this.published = published;
//            return this;
//        }
//
//        public Book build() {
//            return new Book(this);
//        }
//    }
}