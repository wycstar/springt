package com.example.demo.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Token {
    private static String[] alphanumericAlphabet() {
        return IntStream.concat(
            IntStream.rangeClosed('0', '9'),
            IntStream.rangeClosed('A', 'Z')
        ).mapToObj(c -> (char) c+",").collect(Collectors.joining()).split(",");
    }

    private String randomString() {
        List<Character> list3 = Arrays.asList('a','b','c','d','e');
    }
}
