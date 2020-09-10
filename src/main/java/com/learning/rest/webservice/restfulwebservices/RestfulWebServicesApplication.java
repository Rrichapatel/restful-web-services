package com.learning.rest.webservice.restfulwebservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.US);
		
		return sessionLocaleResolver;
	}

	@Bean
	public MessageSource messageSource() {
		
		ResourceBundleMessageSource remessageSource = new ResourceBundleMessageSource();
		remessageSource.setDefaultLocale(Locale.US);
		remessageSource.setBasename("messages");

		return remessageSource;
	}

}
