package com.epam.songmanager;

import com.epam.songmanager.config.BucketProperties;
import com.epam.songmanager.config.FileProperties;
import com.epam.songmanager.config.MessageDigestProperties;
import com.epam.songmanager.config.LocationProperties;
import com.epam.songmanager.config.bean_config.CreateSongsAndResourceConfig;
import com.epam.songmanager.config.s3client_config.S3Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
public class SongManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SongManagerApplication.class, args);
	}

}
