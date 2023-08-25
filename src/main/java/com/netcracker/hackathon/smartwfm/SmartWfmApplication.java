package com.netcracker.hackathon.smartwfm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@SpringBootApplication
public class SmartWfmApplication {


    @Autowired
    Environment environment;

    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setUsername(environment.getProperty("mail.username"));
        mailSender.setPassword(environment.getProperty("mail.password"));
        mailSender.setHost(environment.getProperty("mail.smtp.host"));
        mailSender.setPort(Integer.valueOf(environment.getProperty("mail.smtp.port")));
        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.auth", environment.getProperty("mail.smtp.auth"));
        properties.put("mail.smtp.starttls.enable", environment.getProperty("mail.smtp.starttls.enable"));

        mailSender.setJavaMailProperties(properties);
        return mailSender;
    }

    public static void main(String[] args) {
        SpringApplication.run(SmartWfmApplication.class, args);
    }
}
