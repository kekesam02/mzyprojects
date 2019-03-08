package com.mzy.service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.mzy.ordercode.OrderCodeGenerator;

public class OrderServiceLockImpl implements OrderService{

	//订单编号生成器
	private OrderCodeGenerator orderCodeGenerator = new OrderCodeGenerator();
	
	//使用锁机制 ReentrantLock 非公平锁 Synchroized 公平锁 排他锁 15-20
	private Lock lock = new ReentrantLock();
	
	//创建订单接口
	@Override
	public void createOrder() {
		try {
			lock.lock();//A :
			//获取订单号
			String orderCode = orderCodeGenerator.getOrderCode();
			System.out.println(Thread.currentThread().getName()+"================"+orderCode);
			//业务代码省略............
		} finally {
			lock.unlock();//A ? no 
		}
	}

}
