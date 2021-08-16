package com.epam.songmanager.jms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
@EnableJms
public class JmsConfig {

    @Bean
    public DefaultJmsListenerContainerFactory containerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setSessionTransacted(true);
        factory.setMaxMessagesPerTask(1);
        factory.setConcurrency("1-5");
        return factory;
    }
//    String BROKER_URL = "tcp://localhost:61616";
//    String BROKER_USERNAME = "admin";
//    String BROKER_PASSWORD = "admin";
//
//    @Bean
//    public Queue queue() {
//        return new ActiveMQQueue("standalone.queue");
//    }
//
//    @Bean
//    public ActiveMQConnectionFactory activeMQConnectionFactory() {
//        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
//        factory.setBrokerURL(BROKER_URL);
//        return factory;
//    }
//
//    @Bean
//    public JmsTemplate jmsTemplate() {
//        return new JmsTemplate(activeMQConnectionFactory());
//    }
}
