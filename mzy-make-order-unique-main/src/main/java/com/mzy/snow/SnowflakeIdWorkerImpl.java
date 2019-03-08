package com.mzy.snow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mzy.service.IOrderService;

@Service("snowflakeIdWorkerImpl")
public class SnowflakeIdWorkerImpl implements IOrderService {

	@Autowired
	private SnowflakeIdWorker snowflakeIdWorker;
	
	public void getOrderId() {
		System.out.println("insert into order_id(id) values('"+snowflakeIdWorker.nextId()+"');");
	}

}