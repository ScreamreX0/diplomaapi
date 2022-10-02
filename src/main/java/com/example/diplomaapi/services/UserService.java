package com.example.diplomaapi.services;

import com.example.diplomaapi.entities.UserEntity;
import com.example.diplomaapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    public UserService() {
    }

    @Autowired
    UserRepository userRepository;

    // statuses
    private final String SUCCESS = "Success";
    private final String SOMETHING_WRONG = "Something went wrong";

    // headers
    private final String STATUS = "status";
    private final String BODY = "body";

    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    public Map<String, Object> signInUser(String login, String password) {
        final String WRONG_LOGIN = "Wrong login";

        UserEntity userEntity = userRepository.findUserByLogin(login);

        Map<String, Object> response = new HashMap<>();

        if (userEntity == null) {
            response.put(STATUS, WRONG_LOGIN);
            response.put(BODY, "");
            return response;
        }

        if (password.equals(userEntity.getPassword())) {
            response.put("status", SUCCESS);
            response.put("body", userEntity);
            return response;
        }

        response.put(STATUS, "wrong password");
        response.put(BODY, "");
        return response;
    }

    public Map<String, Object> signUpUser(String login, String password, String username) {
        final String REPEATING_USERNAME = "Repeating username";
        final String REPEATING_LOGIN = "Repeating login";


        Map<String, Object> response = new HashMap<>();
        response.put(STATUS, "");

        if (userRepository.findUserByUsername(username) != null) {
            response.put(STATUS, REPEATING_USERNAME);
            return response;
        }
        if (userRepository.findUserByLogin(login) != null) {
            response.put(STATUS, REPEATING_LOGIN);
            return response;
        }

        userRepository.save(new UserEntity(login, password, username));

        response.put(STATUS, SUCCESS);
        return response;
    }
}
