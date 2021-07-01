package com.lab4.demo;

import com.lab4.demo.sms.SMS;
import com.lab4.demo.sms.service.SMSService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.mail.MessagingException;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws MessagingException {

        SpringApplication.run(DemoApplication.class, args);

   }

}
