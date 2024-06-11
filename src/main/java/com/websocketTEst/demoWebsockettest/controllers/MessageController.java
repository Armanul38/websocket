package com.websocketTEst.demoWebsockettest.controllers;

import com.websocketTEst.demoWebsockettest.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    public static String message;
    public static String name;
    @MessageMapping("/message")
    //@SendTo("/topic/return-to")
    public void getContent (@RequestBody Message message) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        MessageController.message = message.getContent();
        MessageController.name = message.getName();
        simpMessagingTemplate.convertAndSend("/topic/return-to", message);
    }

    @MessageMapping("/message12")
    @SendTo("/topic/sendmessage1")
    public Message getMessage1 (@RequestBody Message message) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return message;
//        MessageController.message = message.getContent();
//        MessageController.name = message.getName();
    }

    @SubscribeMapping("/message1")
    public String sendOneTimeMessage() {
        return "server one-time message via the application";
    }

    @GetMapping("api/hello")
    public ResponseEntity broadcastMessage() {
        ResponseEntity response = new ResponseEntity<>("success", HttpStatus.OK);;
        Message message = new Message("common", "this is a common message for all");
        simpMessagingTemplate.convertAndSend("/topic/return-to", message);
        return response;
    }
}
