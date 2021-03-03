package com.jocamav.springbootrest.http;

public class Person {

    private Long id;
    private String name;
    private String familyName;

    public Person() {
    }

    public Person(Long id, String name, String familyName) {
        this.id = id;
        this.name = name;
        this.familyName = familyName;
    }

    public Person(String name, String familyName) {
        this.id = null;
        this.name = name;
        this.familyName = familyName;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
