package com.jocamav.springbootreststart;

public class Person {

    private final Long id;
    private final String name;
    private final String familyName;

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
        this.familyName = "Snow";
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFamilyName() {
        return familyName;
    }
}
