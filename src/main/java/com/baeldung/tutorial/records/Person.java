package com.baeldung.tutorial.records;

import lombok.extern.log4j.Log4j2;

@Log4j2
public record Person(String name, String address) {
    public static /*final*/ String UNKNOWN_ADDRESS = "Unknown";

    Person(String address) {
        this("John Doe", address);
        UNKNOWN_ADDRESS = "something else";
    }

    public Person(String name, String address) {
        log.info("-- this is NOT the default constructor, but the overriden one");
        this.name = name;
        this.address = address;
    }

    public static void main(String[] args) {
        log.info("-- this is the unknown address: '" + Person.UNKNOWN_ADDRESS + "'");
        Person personOne = new Person("name1", "address1");
        Person personTwo = new Person("address2");
        log.info("-- the unknown address has been changed: '" + Person.UNKNOWN_ADDRESS + "'");
        log.info("-- person one, default all-arg constructor: " + personOne);
        log.info("-- person two, custom constructor: " + personTwo);
    }
}
