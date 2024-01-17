package com.mindflytech.education.controlstructures;

import com.mindflytech.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class IfWithoutBracesExample {
    public static void main(String[] args) {
        if(Utils.percentChance(50))
            System.out.println("-- it was true");System.out.println("-- this will always be displayed");

        List<String> data = List.of("oifwjwe", "caweaew", "ecwe", "ewfr", "wecwerf");
        if(true)
            data.stream().map(String::toUpperCase).map(String::length).forEach(number -> {
                System.out.println("-- I am within the lambda expression");
                System.out.println("-- current string length is: " + number);
                System.out.println("-- bye!");
            });
    }
}
