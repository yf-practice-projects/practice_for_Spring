package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
@Configuration
@PropertySource("file:./src/main/resources/external-config.properties")
public class ApikeyConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(ApikeyConfig.class);
	
	@Getter
	@Value("${google.api.key}")
	private String googleApiKey;
	
	@PostConstruct
	public void logInfo() {
		
		logger.info("google.api.key: {}", googleApiKey);
		
	}
}
