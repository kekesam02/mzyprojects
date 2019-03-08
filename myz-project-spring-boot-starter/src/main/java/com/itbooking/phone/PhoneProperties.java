package com.itbooking.phone;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = PhoneProperties.COMMON_PREFIX)
public class PhoneProperties {

	public static final String COMMON_PREFIX = "itbooking.phone";
	
	private String url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
	private String accout = "cf_mkjy";
	private String password = "3B5E1782A77403AB7473E2C3816380BD";

	public String getAccount() {
		return accout;
	}

	public void setAccount(String accout) {
		this.accout = accout;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
