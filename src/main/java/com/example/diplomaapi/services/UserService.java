package com.example.diplomaapi.services;

import com.example.diplomaapi.constants.CommonConstants;
import com.example.diplomaapi.constants.EmailConstants;
import com.example.diplomaapi.constants.ServiceConstants;
import com.example.diplomaapi.entities.TokenEntity;
import com.example.diplomaapi.entities.UserEntity;
import com.example.diplomaapi.repositories.TokenRepository;
import com.example.diplomaapi.repositories.UserRepository;
import com.example.diplomaapi.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.SendFailedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    public UserService() {
    }

    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    EmailSenderService emailSenderService;


    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    public Map<String, Object> signInUser(String login, String password) {
        final String WRONG_LOGIN = "Wrong login";

        UserEntity userEntity = userRepository.findUserByLogin(login);

        Map<String, Object> response = new HashMap<>();

        if (userEntity == null) {
            response.put(ServiceConstants.STATUS, WRONG_LOGIN);
            response.put(ServiceConstants.BODY, "");
            return response;
        }

        if (password.equals(userEntity.getPassword())) {
            response.put("status", ServiceConstants.SUCCESS);
            response.put("body", userEntity);
            return response;
        }

        response.put(ServiceConstants.STATUS, "wrong password");
        response.put(ServiceConstants.BODY, "");
        return response;
    }

    public Map<String, Object> signUpUser(String login, String password, String username) {
        final String REPEATING_USERNAME = "Repeating username";
        final String REPEATING_LOGIN = "Repeating login";


        Map<String, Object> response = new HashMap<>();
        response.put(ServiceConstants.STATUS, "");

        if (userRepository.findUserByUsername(username) != null) {
            response.put(ServiceConstants.STATUS, REPEATING_USERNAME);
            return response;
        }
        if (userRepository.findUserByLogin(login) != null) {
            response.put(ServiceConstants.STATUS, REPEATING_LOGIN);
            return response;
        }

        userRepository.save(new UserEntity(login, password, username));

        response.put(ServiceConstants.STATUS, ServiceConstants.SUCCESS);
        return response;
    }

    public Map<String, Object> confirmEmail(
            String recipientEmail
    ) {
        String wrongLogin = "Wrong login";

        String code = AuthUtils.generateCode(6);
        HashMap<String, Object> response = new HashMap<>();

        response.put(ServiceConstants.STATUS, "");

        UserEntity user = userRepository.findUserByLogin(recipientEmail);
        if (user == null) {
            response.put(ServiceConstants.STATUS, wrongLogin);
            return response;
        }

        emailSenderService.sendEmail(recipientEmail,
                EmailConstants.CONFIRM_CODE_TITLE,
                EmailConstants.CONFIRM_CODE_BODY + code);

        TokenEntity token = tokenRepository.findConfirmTokenByOwner(user.getId());
        if (token == null) {
            tokenRepository.save(new TokenEntity(
                    code,
                    CommonConstants.EMAIL_CONFIRMATION_TYPE,
                    user.getId()));
        } else {
            token.setToken(code);
            tokenRepository.save(token);
        }

        response.put(ServiceConstants.STATUS, ServiceConstants.SUCCESS);
        return response;
    }

    public Map<String, Object> checkConfirmCode(
            String recipientEmail,
            String code
    ) {
        String userNotFoundError = "User not found";
        String tokenNotFoundError = "Token not found";
        String wrongTokenError = "Wrong token";

        UserEntity user = userRepository.findUserByLogin(recipientEmail);

        HashMap<String, Object> response = new HashMap<>();
        response.put(ServiceConstants.STATUS, "");

        if (user == null) {
            response.put(ServiceConstants.STATUS, userNotFoundError);
            return response;
        }

        TokenEntity token = tokenRepository.findConfirmTokenByOwner(user.getId());
        if (token == null) {
            response.put(ServiceConstants.STATUS, tokenNotFoundError);
            return response;
        }

        if (code.equals(token.getToken())) {
            response.put(ServiceConstants.STATUS, ServiceConstants.SUCCESS);
        } else {
            response.put(ServiceConstants.STATUS, wrongTokenError);
        }
        return response;
    }
}

















