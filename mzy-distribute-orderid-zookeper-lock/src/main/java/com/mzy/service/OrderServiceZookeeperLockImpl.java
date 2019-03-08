package com.mzy.service;

import java.util.concurrent.locks.Lock;

import com.mzy.ordercode.OrderCodeGenerator;

public class OrderServiceZookeeperLockImpl implements OrderService{

	//订单编号生成器
	private static OrderCodeGenerator orderCodeGenerator = new OrderCodeGenerator();
	
	
	//创建订单接口
	@Override
	public void createOrder() {
		//使用分布式锁机制  zkClient 不支持递归创建 
		//Lock lock = new ZKDistributeLock("/rumolock");
		Lock lock = new ZKDistributeLock2("/rumolock2");
		try {
			lock.lock();
			//获取订单号
			String orderCode = orderCodeGenerator.getOrderCode();
			
			System.out.println(Thread.currentThread().getName()+"================"+orderCode);
			//业务代码省略............
		} finally {
			lock.unlock();
		}
	}

}
