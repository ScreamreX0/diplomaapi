package com.example.diplomaapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {
    @GetMapping
    public String test() {
        try {
            return "Hello world";
        } catch (Exception e) {
            return "Not hello world :(. Something is going wrong!";
        }
    }
}