module com.mindflytech.education {
    requires jdk.incubator.concurrent;
    requires static lombok;
    requires spring.core;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.beans;
    requires spring.jdbc;
    requires spring.context;
    requires spring.data.jpa;
    requires jakarta.persistence;
    requires spring.web;
    requires org.apache.logging.log4j;
    requires org.slf4j;
    requires com.google.common;
    requires org.apache.tomcat.embed.core;
}