package com.epam.songmanager.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "s3")
@Data
public class S3Properties {
    private String s3Url;
    private String accessKey;
    private String secretKey;
}
