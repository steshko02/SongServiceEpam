package com.epam.songmanager;

import com.epam.songmanager.config.StorageProperties;
import com.epam.songmanager.service.ResourceService;
import com.epam.songmanager.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class SongManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SongManagerApplication.class, args);
	}


	@Bean
	CommandLineRunner init(StorageService storageService,ResourceService resourceService) {
		return (args) -> {
			//storageService.init();
		};
	}

}
