package com.itbooking;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = CommonProperties.COMMON_PREFIX)
public class CommonProperties {

	public static final String COMMON_PREFIX = "itbooking.common";
	private String name;
	private int time;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

}
