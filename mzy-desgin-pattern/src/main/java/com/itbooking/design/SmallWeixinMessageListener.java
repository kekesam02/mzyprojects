/**
 * TODO濡沫系统平台<br/>
 * com.itbooking.design<br/>
 * WeixinMessageListener.java<br/>
 *  创建人:mofeng <br/>
 * 时间：2019年2月21日-下午4:53:18 <br/>
 * 2019濡沫公司-版权所有<br/>
 */
package com.itbooking.design;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 
 * WeixinMessageListener<br/>
 * 创建人:mofeng<br/>
 * 时间：2019年2月21日-下午4:53:18 <br/>
 * @version 1.0.0<br/>
 * 
 */

@Component 
public class SmallWeixinMessageListener implements ApplicationListener<OrderCreateEvent>{

	
	@Override
	public void onApplicationEvent(OrderCreateEvent event) {
		System.out.println("调用APP接口，发送短信.....");
	}

}
