package com.example.diplomaapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainController {
    @GetMapping
    public String main() {
        try {
            return "Hello world";
        } catch (Exception e) {
            return "Not hello world :(";
        }
    }
}