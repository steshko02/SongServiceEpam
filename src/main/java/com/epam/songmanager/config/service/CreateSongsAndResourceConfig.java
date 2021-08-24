package com.epam.songmanager.config.service;

import com.epam.songmanager.config.properties.MessageDigestProperties;
import com.epam.songmanager.facades.CreateResourceImpl;
import com.epam.songmanager.model.resource.CloudStorageEntity;
import com.epam.songmanager.model.resource.FileStorageEntity;
import com.epam.songmanager.service.impl.FileSystemStorageService;
import com.epam.songmanager.service.impl.MinioService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ MessageDigestProperties.class})
public class CreateSongsAndResourceConfig {

    @Bean
    public CreateResourceImpl<FileStorageEntity> getFileObjBean(
            FileSystemStorageService fileSystemStorageService,
            MessageDigestProperties messageDigestProperties
    ){
        return new CreateResourceImpl<>(fileSystemStorageService,
                messageDigestProperties.getMessageDigestType());
    }

    @Bean
    public CreateResourceImpl<CloudStorageEntity> getCloudObjBean(
            MinioService minioService, MessageDigestProperties messageDigestProperties
    ){
        return new CreateResourceImpl<>(minioService,
                messageDigestProperties.getMessageDigestType());
    }

}
