package com.lab4.demo.sms.service;

import com.lab4.demo.sms.SMS;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.util.MultiValueMap;

@Component
public class SMSService {

    //@Value("#{systemEnvironment['AC954666c5139fd707d82617c04e92f3e2']}")
    private final String ACCOUNT_SID = "AC954666c5139fd707d82617c04e92f3e2";

    //@Value("#{systemEnvironment['c1a7c7015c2aab3d047bbad60fa655ab']}")
    private final String AUTH_TOKEN = "c1a7c7015c2aab3d047bbad60fa655ab";

   // @Value("#{systemEnvironment['(347)757-4655']}")
    private final String FROM_NUMBER = "+15189636519";

    public void send(SMS sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message =
                Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(FROM_NUMBER),
                        sms.getMessage())
                .create();

        System.out.println("id of the transaction: "+message.getSid());

    }

    public void receive(MultiValueMap<String, String> smsCallBack) { }

}
