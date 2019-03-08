/**
 * TODO濡沫系统平台<br/>
 * com.itbooking.design<br/>
 * OrderEvent.java<br/>
 *  创建人:mofeng <br/>
 * 时间：2019年2月21日-下午4:50:49 <br/>
 * 2019濡沫公司-版权所有<br/>
 */
package com.itbooking.design;

import org.springframework.context.ApplicationEvent;

/**
 * 
 * OrderEvent<br/>
 * 创建人:mofeng<br/>
 * 时间：2019年2月21日-下午4:50:49 <br/>
 * @version 1.0.0<br/>
 * 
 */
public class OrderCreateEvent extends ApplicationEvent{

	
	/**
	 * 创建一个新的实例 OrderEvent.
	 * @param source
	 */
	public OrderCreateEvent(Object source) {
		super(source);
	}
	
	
	

}
