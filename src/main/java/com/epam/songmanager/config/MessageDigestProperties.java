package com.epam.songmanager.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "md")
@Data
@AllArgsConstructor

public class MessageDigestProperties {
    private  String messageDigestType;

    @Bean("messageDigestType")
    public  String getBean(){
        return  messageDigestType;
    }
    public MessageDigestProperties() {
    }
}


