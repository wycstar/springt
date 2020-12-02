package com.example.demo.controller;

import java.util.Date;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GeneralResponse {
    public ResponseEntity<Object> generateResponse(HttpStatus status, String msg, Object response) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Date date = new Date();
        map.put("ts", String.valueOf(date.getTime() / 1000));
        map.put("code", status.value());
        map.put("msg", msg);
        map.put("data", response);
        return new ResponseEntity<Object>(map, status);
    }
}
