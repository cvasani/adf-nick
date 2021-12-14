package com.cvasani.jdeveloper.cookbook.java.collection;

import java.util.HashSet;
import java.util.Set;

// /scratch/ws_jdev/rough/mywork/book/ADF-Faces-Cookbook/Chapter 3/Complete App/Chapter03/Model/src/com/cvasani/jdeveloper/cookbook/java/collection/testing.java

public class CreateHashSetExample {
    public static void main(String[] args) {
        // Creating a HashSet
        Set<String> daysOfWeek = new HashSet<>();

        // Adding new elements to the HashSet
        daysOfWeek.add("Monday");
        daysOfWeek.add("Tuesday");
        daysOfWeek.add("Wednesday");
        daysOfWeek.add("Thursday");
        daysOfWeek.add("Friday");
        daysOfWeek.add("Saturday");
        daysOfWeek.add("Sunday");

        // Adding duplicate elements will be ignored
        daysOfWeek.add("Monday");

        System.out.println(daysOfWeek);
    }
}
