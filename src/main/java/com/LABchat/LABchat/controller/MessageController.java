package com.LABchat.LABchat.controller;

import com.LABchat.LABchat.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private UsuarioController usuarioController;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public MessageController() {
    }

    @MessageMapping("/chat")
    @SendTo("/topic/greetings")
    public String message (Message messageDTO) throws Exception {
        logger.debug("Received message: {}", messageDTO.getMessage());
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        return messageDTO.getFrom() +" : "+messageDTO.getMessage() +" " + time;
    }

    @MessageMapping("/user-connected")
    public void userConnected(@Payload String username) {
        String message = "Usuário " + username + " se conectou.";
        simpMessagingTemplate.convertAndSend("/topic/greetings", message);
    }



}
