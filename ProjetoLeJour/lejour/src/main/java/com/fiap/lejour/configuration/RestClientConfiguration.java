package com.fiap.lejour.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class RestClientConfiguration {
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
