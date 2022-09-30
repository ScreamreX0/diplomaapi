package com.example.diplomaapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.annotation.WebServlet;

@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping("/")
    public ResponseEntity getUsers() {
        try {
            return ResponseEntity.ok().body("Сервер работает");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
