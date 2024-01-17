package com.mindflytech.tutorial.webserver.db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "students")
public class GreetingLog {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //TODO: BigDecimal?
    private Integer id;

    private String greeting;
}
