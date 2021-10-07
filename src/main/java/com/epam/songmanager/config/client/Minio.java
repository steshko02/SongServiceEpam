package com.epam.songmanager.config.client;

import com.epam.songmanager.config.properties.S3Properties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(S3Properties.class)
public class Minio {

    @Bean
    public io.minio.MinioClient s3(S3Properties s3Properties) {
        io.minio.MinioClient minioClient =
                io.minio.MinioClient.builder()
                        .endpoint(s3Properties.getS3Url())
                        .credentials(s3Properties.getAccessKey(), s3Properties.getSecretKey())
                        .build();
        return minioClient;
    }
}
