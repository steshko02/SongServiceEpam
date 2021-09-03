package com.epam.songmanager.config.s3client;

import com.epam.songmanager.config.properties.S3Properties;
import io.minio.MinioClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(S3Properties.class)
public class S3Config {

    @Bean
    public MinioClient s3(S3Properties s3Properties) {
        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint(s3Properties.getS3Url())
                        .credentials(s3Properties.getAccessKey(), s3Properties.getSecretKey())
                        .build();
        return minioClient;
    }
}
