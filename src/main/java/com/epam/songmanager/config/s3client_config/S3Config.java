package com.epam.songmanager.config.s3client_config;

import io.minio.MinioClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "s3")
@Data
@AllArgsConstructor
public class S3Config {

    private String s3Url;
    private String accessKey;
    private String secretKey;


    @Bean
    public MinioClient s3() {
        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint(s3Url)
                        .credentials(accessKey, secretKey)
                        .build();


        return minioClient;
    }

    public S3Config() {
    }
}
