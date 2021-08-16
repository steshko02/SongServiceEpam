package com.epam.songmanager.jms.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class Listener {


//    @JmsListener(destination = "zip")
//    @SendTo("outbound.zip")
//    public String receiveQaMessage(String string) {
//        System.out.println("Received: " + string);
//        return string;
//    }
//    @JmsListener(destination = "inbound.queue")
//    public void receiveMessage(final Message jsonMessage) throws JMSException {
//        String messageData = null;
//        System.out.println("Received message " + jsonMessage);
//        if(jsonMessage instanceof TextMessage) {
//            TextMessage textMessage = (TextMessage)jsonMessage;
//            messageData = textMessage.getText();
//        }
//        producer.sendMessage("outbound.queue", messageData);
//    }
//
//    @JmsListener(destination = "inbound.topic")
//    @SendTo("outbound.topic")
//    public String receiveMessageFromTopic(final Message jsonMessage) throws JMSException {
//        String messageData = null;
//        System.out.println("Received message " + jsonMessage);
//        String response = null;
//        if(jsonMessage instanceof TextMessage) {
//            TextMessage textMessage = (TextMessage)jsonMessage;
//            messageData = textMessage.getText();
//            Map map = new Gson().fromJson(messageData, Map.class);
//            response  = "Hello " + map.get("name");
//        }
//        return response;
//    }
}