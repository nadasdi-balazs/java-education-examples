package com.mindflytech.education.stream;

import com.google.common.collect.Lists;
import com.mindflytech.education.oop.innerclass.Lambdas;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import com.mindflytech.education.util.CitiesOfCountries;

import static java.util.stream.Collectors.*;

public class MultiMapFlatMapExamples {
    private List<CitiesOfCountries> countriesWithCities = new ArrayList<>() {{
        add(new CitiesOfCountries("Japan", Lists.newArrayList("Tokyo", "Osaka", "Kyoto")));
        add(new CitiesOfCountries("Germany", Lists.newArrayList("Berlin", "Munchen", "Hamburg")));
        add(new CitiesOfCountries("UK", Lists.newArrayList("London", "Glasgow", "Belfast")));
        add(new CitiesOfCountries("Belgium", Lists.newArrayList("Brussels", "Liege", "Antwerpen")));
        add(new CitiesOfCountries("France", Lists.newArrayList("Paris", "Toulouse", "Marseille")));
    }};

    class Item {}

    class Result {
        String name;
        Set<Item> items;
    }

    public static void main(String[] args) {
        List<Result> results = new ArrayList<>();
        results.stream()
                //map Stream<Result> to Stream<Entry<Item>,String>
                .flatMap((Result it) -> it.items.stream().map(item -> new AbstractMap.SimpleEntry<>(item, it.name)))
                //group Result.name by Item
                .collect(groupingBy(Entry::getKey, mapping(Entry::getValue, toSet())));
    }
}
