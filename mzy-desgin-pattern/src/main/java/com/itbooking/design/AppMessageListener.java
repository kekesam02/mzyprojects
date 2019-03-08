/**
 * TODO濡沫系统平台<br/>
 * com.itbooking.design<br/>
 * WeixinMessageListener.java<br/>
 *  创建人:mofeng <br/>
 * 时间：2019年2月21日-下午4:53:18 <br/>
 * 2019濡沫公司-版权所有<br/>
 */
package com.itbooking.design;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 
 * WeixinMessageListener<br/>
 * 创建人:mofeng<br/>
 * 时间：2019年2月21日-下午4:53:18 <br/>
 * @version 1.0.0<br/>
 * 
 */

//@Component 
//public class AppMessageListener implements ApplicationListener<OrderCreateEvent>{
//
//	
//	@Override
//	public void onApplicationEvent(OrderCreateEvent event) {
//		System.out.println("调用小程序接口，发送短信.....");
//	}
//
//}
@Component 
public class AppMessageListener implements SmartApplicationListener{

	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if(event.getClass() == OrderCreateEvent.class) {
			System.out.println("2----------调用小程序接口，发送短信.....");
		}
	}

	
	@Override
	public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
		return true;
	}


	@Override
	public boolean supportsSourceType(Class<?> sourceType) {
		return true;
	}


	@Override
	public int getOrder() {
		return 0;
	}
	
}