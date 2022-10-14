package com.example.diplomaapi.controller;

import com.example.diplomaapi.entities.UserEntity;
import com.example.diplomaapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * @HTTPStatus 200 -> User found and authorized
     * @HTTPStatus 400 -> Wrong email or password
     * */
    @PostMapping("/email-in")
    public ResponseEntity<UserEntity> emailIn(@RequestBody Map<String, Object> body) {
        return userService.emailIn(
                body.get("email").toString(),
                body.get("password").toString());
    }

    /**
     * @HTTPStatus 200 -> User found and authorized
     * @HTTPStatus 400 -> Wrong username or password
     * */
    @PostMapping("/username-in")
    public ResponseEntity<UserEntity> usernameIn(@RequestBody Map<String, Object> body) {
        return userService.usernameIn(
                body.get("username").toString(),
                body.get("password").toString());
    }

    /**
     * @HTTPStatus 200 -> User signed up successfully
     * @HTTPStatus 400 -> User is already exist with the same username
     * @HTTPStatus 401 -> User is already exist with the same email
     * */
    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody Map<String, Object> body) {
        return userService.signUp(
                body.get("email").toString(),
                body.get("password").toString(),
                body.get("username").toString()
        );
    }

    /**
     * @HTTPStatus 200 -> Letter sent
     * @HTTPStatus 400 -> Email doesn't exist
     * */
    @PostMapping("/confirm/send")
    public ResponseEntity<String> sendConfirm(@RequestBody Map<String, Object> body) {
        return userService.sendConfirm(
                body.get("email").toString()
        );
    }

    /**
     * @HTTPStatus 200 -> The code is correct
     * @HTTPStatus 400 -> User not found
     * @HTTPStatus 401 -> Wrong code
     * */
    @PostMapping("/confirm/check")
    public ResponseEntity<String> checkConfirm(@RequestBody Map<String, Object> body) {
        return userService.checkConfirm(
                body.get("email").toString(),
                body.get("code").toString()
        );
    }
}

