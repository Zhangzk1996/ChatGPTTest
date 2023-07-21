package com.example.chatgpt.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.chatgpt.demo")
@EnableAutoConfiguration(exclude = { org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class })
public class ChatGptTestApplication {

	// defile logger
	private static final Logger logger = LoggerFactory.getLogger(ChatGptTestApplication.class);

	public static void main(String[] args) {
		try {
			logger.info("Starting application...");
			SpringApplication.run(ChatGptTestApplication.class, args);
			logger.info("Finish to start application...");
		} catch (Exception e) {
			logger.error("Error while starting application: " + e.getMessage());
			System.out.println(e);
			e.printStackTrace();
		}
	}

}
