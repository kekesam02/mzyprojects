package com.mzy.service;

import com.mzy.ordercode.OrderCodeGenerator;

public class OrderServiceImpl implements OrderService{

	//订单编号生成器
	private OrderCodeGenerator orderCodeGenerator = new OrderCodeGenerator();
	
	//创建订单接口
	@Override
	public void createOrder() {
		
		//获取订单号
		String orderCode = orderCodeGenerator.getOrderCode();
		
		System.out.println(Thread.currentThread().getName()+"================"+orderCode);
		//业务代码省略............、
		
	}

}
