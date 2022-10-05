package com.example.diplomaapi.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Properties;

@Service
public class EmailSenderService {
    private static final String SENDER = "no-reply@gmail.com";
    private static final String USERNAME = "diplomapp2@gmail.com";
    private static final String PASSWORD = "knbpcgisgyerfxzt";

    public ResponseEntity<String> sendEmail (
            String recipient,
            String title,
            String body) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                }
        );

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(title);
            message.setText(body);
            Transport.send(message);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
