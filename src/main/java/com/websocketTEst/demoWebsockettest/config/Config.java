package com.websocketTEst.demoWebsockettest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class Config implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
        //registry.addEndpoint("server1").setAllowedOrigins("*"); //client connects to server with this url.
        registry.addEndpoint("server1").withSockJS();
    }

//    @Override
//    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
//        return WebSocketMessageBrokerConfigurer.super.configureMessageConverters(messageConverters);
//    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);
        registry.enableSimpleBroker("/topic"); // server send url and client subscribe url must be start with /topic
        registry.setApplicationDestinationPrefixes("/app"); //client send url must be start with /app
    }
//    @Override
//    public void configureClientOutboundChannel(ChannelRegistration registration) {
//        registration.interceptors(new MyOutboundChannelInterceptor());
//    }
//
//    // Custom interceptor class
//    private static class MyOutboundChannelInterceptor implements ChannelInterceptor {
//        @Override
//        public Message<?> preSend(Message<?> message, MessageChannel channel) {
//            // Custom logic before sending the message
//            System.out.println("Outbound message: " + message);
//            return message;
//        }
//
//        @Override
//        public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
//            // Custom logic after sending the message
//            System.out.println("Outbound message sent: " + message);
//        }
//
//        @Override
//        public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
//            // Custom logic after message send completion
//            if (ex != null) {
//                System.err.println("Error sending outbound message: " + ex.getMessage());
//            }
//        }
//    }

//    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(new MyChannelInterceptor());
//    }
//
//    // Custom interceptor class
//    private static class MyChannelInterceptor implements ChannelInterceptor {
//        @Override
//        public Message<?> preSend(Message<?> message, MessageChannel channel) {
//            // Custom logic before sending the message
//            System.out.println("Inbound message: " + message);
//            return message;
//        }
//
//        @Override
//        public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
//            // Custom logic after sending the message
//            System.out.println("Inbound message sent: " + message);
//        }
//
//        @Override
//        public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
//            // Custom logic after message send completion
//            if (ex != null) {
//                System.err.println("Error sending inbound message: " + ex.getMessage());
//            }
//        }
//
//        @Override
//        public boolean preReceive(MessageChannel channel) {
//            // Custom logic before receiving a message
//            return true;
//        }
//
//        @Override
//        public Message<?> postReceive(Message<?> message, MessageChannel channel) {
//            // Custom logic after receiving a message
//            System.out.println("Inbound message received: " + message);
//            return message;
//        }
//
//        @Override
//        public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {
//            // Custom logic after message receive completion
//            if (ex != null) {
//                System.err.println("Error receiving inbound message: " + ex.getMessage());
//            }
//        }
//    }
}
