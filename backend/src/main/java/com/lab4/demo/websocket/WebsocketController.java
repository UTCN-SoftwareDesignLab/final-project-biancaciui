package com.lab4.demo.websocket;

import com.lab4.demo.websocket.dto.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WebsocketController {

    private WebsocketService websocketService;

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody final Message message){
        websocketService.notifyFrontend(message.getMessage());
    }

}
