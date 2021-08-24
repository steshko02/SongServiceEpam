package com.epam.songmanager.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix ="storage")
@Data
public class LocationProperties {

    private String location ;

}