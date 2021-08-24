package com.epam.songmanager.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix ="bucket")
@Data
public class BucketProperties {
    private String location;
}
