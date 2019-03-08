package com.itbooking;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
@EnableConfigurationProperties(CommonProperties.class)
public class CommonAutoConfigure {

	
	@Bean
	public CommonService getCommonService(CommonProperties properties) {
		CommonService commonService = new CommonService();
		commonService.setName(properties.getName());
		commonService.setTime(properties.getTime());
		return commonService;
	}

}
