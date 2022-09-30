package com.example.diplomaapi.services;

import com.example.diplomaapi.entities.UserEntity;
import com.example.diplomaapi.models.User;
import com.example.diplomaapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public UserService() {
    }

    @Autowired
    UserRepository userRepository;

    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    public UserEntity findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }
}
