package org.spring.data.cassandra.example.demo.domain;

public class Person {
    private final String id;
    private final String name;

    public Person(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
