package com.epam.songmanager.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix ="storage")
@Data
@AllArgsConstructor
public class LocationProperties {

    private String location ;

    @Bean("fileLocation")
    public String getBean(){
        return location;
    }

    public LocationProperties() {
    }
}