package com.example.diplomaapi.services;

import com.example.diplomaapi.abstractions.IUserService;
import com.example.diplomaapi.constants.EmailConstants;
import com.example.diplomaapi.entities.UserEntity;
import com.example.diplomaapi.repositories.UserRepository;
import com.example.diplomaapi.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    public UserService() {
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailSenderService emailSenderService;

    @Override
    public ResponseEntity<UserEntity> emailIn(String email, String password) {
        UserEntity userEntity = userRepository.findUserByEmail(email);

        if (userEntity == null || !password.equals(userEntity.getPassword())) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        userEntity.setToken(AuthUtils.generateToken());
        userRepository.save(userEntity);
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserEntity> usernameIn(String username, String password) {
        UserEntity userEntity = userRepository.findUserByName(username);

        if (userEntity == null || !password.equals(userEntity.getPassword())) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        userEntity.setToken(AuthUtils.generateToken());
        userRepository.save(userEntity);
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> signUp(String email, String password, String username) {
        if (userRepository.findUserByName(username) != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (userRepository.findUserByEmail(email) != null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        userRepository.save(new UserEntity(email, password, username));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> sendConfirm(String email) {
        UserEntity userEntity = userRepository.findUserByEmail(email);
        if (userEntity == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String code = AuthUtils.generateCode(6);
        emailSenderService.sendEmail(
                email,
                EmailConstants.CONFIRM_CODE_TITLE,
                EmailConstants.CONFIRM_CODE_BODY + code
        );

        userEntity.setCode(code);
        userRepository.save(userEntity);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> checkConfirm(String email, String code) {
        UserEntity userEntity = userRepository.findUserByEmail(email);
        if (userEntity == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!userEntity.getCode().equals(code)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        userEntity.setConfirmed(true);
        userRepository.save(userEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

















