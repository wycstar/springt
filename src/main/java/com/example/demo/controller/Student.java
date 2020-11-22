package com.example.demo.controller;

public class Student {
    private final long id;
    private final String content;

    public Student(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }
}
