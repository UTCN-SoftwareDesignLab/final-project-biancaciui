package com.lab4.demo.websocket;

import com.lab4.demo.websocket.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebsocketService {

    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebsocketService(SimpMessagingTemplate messagingTemplate){
        this.messagingTemplate=messagingTemplate;
    }

    public void notifyFrontend(final String message){
        ResponseMessage responseMessage = new ResponseMessage(message);
        messagingTemplate.convertAndSend("/topic/messages", responseMessage);
    }

}
