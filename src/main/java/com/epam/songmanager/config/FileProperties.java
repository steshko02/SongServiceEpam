package com.epam.songmanager.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "file")
@Data
public class FileProperties {

    private  String fileExtension;

    @Bean("fileExtension")
    public  String getBean(){
        return  fileExtension;
    }


    public FileProperties() {
    }
}
