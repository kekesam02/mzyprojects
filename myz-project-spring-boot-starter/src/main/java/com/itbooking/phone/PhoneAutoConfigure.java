package com.itbooking.phone;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.itbooking.PhoneSendService;

@SpringBootConfiguration
@EnableConfigurationProperties(PhoneProperties.class)
public class PhoneAutoConfigure {

	
	@Bean
	public PhoneSendService getPhoneSendService(PhoneProperties properties) {
		PhoneSendService phoneSendService = new PhoneSendService();
		phoneSendService.setAccount(properties.getAccount());
		phoneSendService.setPassword(properties.getPassword());
		phoneSendService.setUrl(properties.getUrl());
		return phoneSendService;
	}

}
