package com.example.demo.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Token {
    private static String[] alphanumericAlphabet() {
        return IntStream
                .concat(IntStream.rangeClosed('0', '9'),
                        IntStream.concat(IntStream.rangeClosed('A', 'Z'), IntStream.rangeClosed('a', 'z')))
                .mapToObj(c -> (char) c + ",").collect(Collectors.joining()).split(",");
    }

    private String randomString() {
        String[] alphaDict = alphanumericAlphabet();
        return IntStream.range(0, 6).map(arg0)
    }
}
