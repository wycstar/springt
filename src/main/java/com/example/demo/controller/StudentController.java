package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/session", produces = "application/json")
public class StudentController {
    @GetMapping("/s")
    public Student student(@RequestParam(value = "name", defaultValue = "q") String name) {
        return new Student(1, name);
    }
}
