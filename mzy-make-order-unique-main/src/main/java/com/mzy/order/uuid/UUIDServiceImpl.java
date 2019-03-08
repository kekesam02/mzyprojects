package com.mzy.order.uuid;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mzy.service.IOrderService;

@Service("uuidServiceImpl")
public class UUIDServiceImpl implements IOrderService {

	public void getOrderId() {
		String orderId = UUID.randomUUID().toString();
		System.out.println("insert into order_id(id) values('"+orderId+"');");
	}
	
}
