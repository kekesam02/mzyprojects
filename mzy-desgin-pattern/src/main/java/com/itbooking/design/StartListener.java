
package com.itbooking.design;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * 
 * StartListener<br/>
 * 创建人:mzy<br/>
 * 时间：2019年2月21日-下午4:35:05 <br/>
 * @version 1.0.0<br/>
 * 
 */
@Service
public class StartListener implements ApplicationListener<ContextRefreshedEvent> {

	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//防止两次启动
		//if(event.getApplicationContext().getParent() != null) {
			//启动spring后执行一些代码
			System.out.println("spring 启动完毕，后执行此处逻辑代码....");
		//} 
	}

}
