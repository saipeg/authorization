package com.example.demo.services;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

import static com.example.demo.services.EmailSender.sendFromGMail;

@Component
public class MyApplicationListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    private static final Logger LOG = Logger.getLogger(String.valueOf(MyApplicationListener.class));

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        Object userName = event.getAuthentication().getPrincipal();
        Object credentials = event.getAuthentication().getCredentials();
        LOG.info("Failed login using USERNAME [" + userName + "]");
        LOG.info("Failed login using PASSWORD [" + credentials + "]");

        String from = "NordClanTestMail";
        String pass = "put password here";
        String[] to = {"out recipient here"}; // list of recipient email addresses
        String subject = "Java send mail example";
        String body = userName + " failed login try ";

        sendFromGMail(from, pass, to, subject, body);

    }

}