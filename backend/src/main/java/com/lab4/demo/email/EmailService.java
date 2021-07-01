package com.lab4.demo.email;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@RequiredArgsConstructor
@Service
public class EmailService {
    public void sendEmail(String link, String emailAddress, String movieName) throws MessagingException {

        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("imdbianca.co@gmail.com", "OparolStrong!3");
            }
        });

        Message msg = new MimeMessage(session);

        msg.setSubject("Download Link");
        msg.setContent("Thank you for choosing us as your entertainment provider! \n\nYou have the download link below for " + movieName + ". \n\nEnjoy!\n\n" + link,"text/html");

        msg.setFrom(new InternetAddress("imdbianca.co@gmail.com", false));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddress));

        msg.setSentDate(new Date());

        Transport.send(msg);
    }

}


