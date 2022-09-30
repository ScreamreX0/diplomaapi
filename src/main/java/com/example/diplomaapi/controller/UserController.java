package com.example.diplomaapi.controller;

import com.example.diplomaapi.entities.UserEntity;
import com.example.diplomaapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
        UserEntity userEntity = userService.findUserByLogin(body.get("login").toString());

        Map<String, Object> response = new HashMap<>();


        if (userEntity == null) {
            response.put("status", "wrong login");
            response.put("body", "");
            return response;
        }

        if (body.get("password").toString().equals(userEntity.getPassword())) {
            response.put("status", "success");
            response.put("body", userEntity);
            return response;
        }

        response.put("status", "wrong password");
        response.put("body", "");
        return response;
    }
}
