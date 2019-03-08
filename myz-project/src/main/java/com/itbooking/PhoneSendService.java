package com.itbooking;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
/**
 * 
 * 短信验证码
 * PhoneValidator
 * 创建人:弗兰纳忠
 * 时间：2016年7月21日-下午4:20:44 
 * @version 1.0.0
 *
 */
public class PhoneSendService {
	//发送地址
	private String url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
	private String accout = "cf_mkjy";
	private String password = "3B5E1782A77403AB7473E2C3816380BD";
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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

	/**
	 * 发送短信的接口 
	 * com.tz.util.phone 
	 * 方法名：sendMsg
	 * 创建人：弗兰纳忠 
	 * 时间：2016年7月21日-下午4:20:52 
	 * @param mobile
	 * @param mobile_code
	 * @return boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public  boolean sendMsg(String mobile,int mobile_code){
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(url); 
		client.getParams().setContentCharset("UTF-8"); 
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
	    String content = new String("验证码：【"+mobile_code+"】，请您在【20】分钟内填写。如非本人操作，请忽略本短信。"); 
		NameValuePair[] data = {//提交短信
			   new NameValuePair("account", accout), 
			   new NameValuePair("password", password),
			   new NameValuePair("mobile", mobile), 
			   new NameValuePair("content", content),
		};
		method.setRequestBody(data);	
		
		try {
			client.executeMethod(method);	
			String SubmitResult =method.getResponseBodyAsString();
			Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();
			String code = root.elementText("code");	
			if("2".equals(code)){
				return true;
			}else{
				return false;
			}
		} catch (HttpException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (DocumentException e) {
			e.printStackTrace();
			return false;
		}	
	}
}