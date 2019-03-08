/**
 * TODO濡沫系统平台<br/>
 * com.itbooking.design<br/>
 * OrderService.java<br/>
 *  创建人:mofeng <br/>
 * 时间：2019年2月21日-下午4:17:07 <br/>
 * 2019濡沫公司-版权所有<br/>
 */
package com.itbooking.design;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Service;

/**
 * 
 * OrderService<br/>
 * 创建人:mofeng<br/>
 * 时间：2019年2月21日-下午4:17:07 <br/>
 * @version 1.0.0<br/>
 * 
 */
@Service
public class OrderService {
	
	
	@Autowired
	private ApplicationContext applicationContext;
	
	
	public void saveOrder() {
		//此处忽略很多逻辑， 参数封装，数据查询等等逻辑
		//1:----创建订单
		System.out.println("1: 订单创建成功");
		//利用Spring机制去实现观察者
		//生产者---事件---传播---监听者
		ApplicationEvent orderCreateEvent = new OrderCreateEvent(new Object());
		applicationContext.publishEvent(orderCreateEvent);
	}
}




























