package com.example.board.common.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailService {

    public void sendMail(){
        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo("수신메일");
        msg.setSubject("메일제목");
        msg.setText("메일내용");

        JavaMailSender javaMailSender = new JavaMailSenderImpl();
        javaMailSender.send(msg);
    }
}
