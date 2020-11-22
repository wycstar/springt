package com.example.demo.controller;

import java.util.HashMap;

public class GeneralResponse {
    private int code;
    private String msg;

    public GeneralResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
