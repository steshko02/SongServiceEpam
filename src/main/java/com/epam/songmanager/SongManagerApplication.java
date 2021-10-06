package com.epam.songmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class SongManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(SongManagerApplication.class, args);
	}
}
