package com.epam.songmanager.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("bucket")
@ConfigurationProperties(prefix ="bucket")
@Data
public class BucketProperties {
    private String location;

    @Bean("cloudLocation")
    public  String getBean(){
        return  location;
    }

    public BucketProperties() {
    }
}
