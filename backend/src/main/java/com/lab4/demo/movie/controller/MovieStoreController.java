package com.lab4.demo.movie.controller;

import com.lab4.demo.email.EmailService;
import com.lab4.demo.movie.mapper.MovieMapper;
import com.lab4.demo.movie.model.Movie;
import com.lab4.demo.movie.model.dto.MovieDTO;
import com.lab4.demo.movie.service.MovieService;
import com.lab4.demo.order.dto.OrderDTO;
import com.lab4.demo.order.service.OrderService;
import com.lab4.demo.sms.SMS;
import com.lab4.demo.sms.service.SMSService;
import com.lab4.demo.user.UserService;
import com.lab4.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

import java.util.Random;

import static com.lab4.demo.UrlMapping.*;

@RestController
@RequestMapping(API_PATH+SELL_MOVIE)
@RequiredArgsConstructor
public class MovieStoreController {

    private final MovieService movieService;
    private final EmailService emailService;
    private final OrderService orderService;
    private final UserService userService;

    private final MovieMapper movieMapper;

    @PostMapping(ENTITY_MOVIE+ENTITY_USER)
    public void sell(@PathVariable Long movie_id, @PathVariable Long user_id) throws MessagingException {

        Long countOrders = orderService.countOrdersByUserID(user_id);

        User user = userService.findById(user_id);
        Movie movie = movieMapper.fromDTO(movieService.findById(movie_id));

        SMSService smsService = new SMSService();

        Random rand = new Random();
        int chance = -1;

        //loyal customer -> challenge your luck
        if(countOrders >= 5)
            chance = rand.nextInt(10);

        // chance = 0 -> lucky
        if(chance != 0){
            //go on with the payment
            //TODO: payment accepted???????
            if(true){
                orderService.create(OrderDTO.builder()
                        .user(user)
                        .movie(movie)
                        //.card_number()
                        .build());
                countOrders++;
                if(countOrders==1){
                    SMS sms = new SMS(user.getPhone_number(), "Thank you for choosing us! You will receive the download link for the movie "+movie.getName()+" on your email. Enjoy!");
                    smsService.send(sms);
                }

                String download_link = movieService.findLinkById(movie_id);
                emailService.sendEmail(download_link, user.getEmail(), movie.getName());
            }
            else{
                SMS sms = new SMS(user.getPhone_number(), "Something went wrong with the payment of your order for the movie "+movie.getName()+". Please try again!");
                smsService.send(sms);
            }

        }
        else {
            //free movie -> no payment
            orderService.create(OrderDTO.builder()
                    .user(user)
                    .movie(movie)
                    //.card_number()
                    .build());
            countOrders++;

            SMS sms = new SMS(user.getPhone_number(), "Good news! You will receive the movie "+movie.getName()+" for free! This is a 'thank you' for being the best customer ever! As usual, check your email for the download link. Enjoy!");
            smsService.send(sms);

            String download_link = movieService.findLinkById(movie_id);
            emailService.sendEmail(download_link, user.getEmail(), movie.getName());

        }
        //send sms notification when the customer reaches a loyalty goal
        if(countOrders == 5) {
            SMS sms = new SMS(user.getPhone_number(), "Hello, friend! We are very happy to announce you that you've unlocked the loyalty bonus with the last purchase! This means that you have 1/10 chances of getting your movies for free in the future! Enjoy!");
            smsService.send(sms);
            //TODO: websocket notification here
        }

    }
}
