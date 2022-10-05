package com.example.diplomaapi.abstractions;

import com.example.diplomaapi.entities.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IUserService {
    ResponseEntity<UserEntity> emailIn(String email, String password);
    ResponseEntity<UserEntity> usernameIn(String username, String password);
    ResponseEntity<String> signUp(String email, String password, String username);
    ResponseEntity<String> sendConfirm(String email);
    ResponseEntity<String> checkConfirm(String email, String code);
}
