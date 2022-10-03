package com.example.diplomaapi.controller;

import com.example.diplomaapi.entities.UserEntity;
import com.example.diplomaapi.services.EmailSenderService;
import com.example.diplomaapi.services.UserService;
import com.example.diplomaapi.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getall")
    public List<UserEntity> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/signin")
    public Map<String, Object> signIn(@RequestBody Map<String,Object> body) {
        return userService.signInUser(
                body.get("login").toString(),
                body.get("password").toString());
    }

    @PostMapping("/signup")
    public Map<String, Object> signUp(@RequestBody Map<String, Object> body) {
        return userService.signUpUser(
                body.get("login").toString(),
                body.get("password").toString(),
                body.get("username").toString()
        );
    }

    @PostMapping("/confirmemail/send")
    public Map<String, Object> sendConfirmEmail(@RequestBody Map<String, Object> body) {
        return userService.confirmEmail(
                body.get("email").toString()
        );
    }

    @PostMapping("/confirmemail/check")
    public Map<String, Object> checkConfirmCode(@RequestBody Map<String, Object> body) {
        return userService.checkConfirmCode(
                body.get("email").toString(),
                body.get("code").toString()
        );
    }
}
