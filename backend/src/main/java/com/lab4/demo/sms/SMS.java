package com.lab4.demo.sms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.annotation.RegEx;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SMS {

    private String to;
    private String message;

    @Override
    public String toString() {
        return "SMS{" +
                "to='" + to + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
