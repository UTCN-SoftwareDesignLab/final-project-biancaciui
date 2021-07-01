package com.lab4.demo.websocket;

import com.lab4.demo.websocket.dto.Message;
import com.lab4.demo.websocket.dto.ResponseMessage;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {

    @MessageMapping("/messages")
    @SendTo("/movies/messages")
    public ResponseMessage getMessage(final Message message) {
        return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessage()));
    }

}
