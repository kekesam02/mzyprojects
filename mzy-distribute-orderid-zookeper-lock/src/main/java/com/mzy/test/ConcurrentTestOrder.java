package com.mzy.test;

import java.util.concurrent.CyclicBarrier;

import com.mzy.service.OrderService;
import com.mzy.service.OrderServiceImpl;

public class ConcurrentTestOrder {

	
	public static void main(String[] args) {
		//并发数50
		int threadcount = 10;
		//循环屏障
		CyclicBarrier cb = new CyclicBarrier(threadcount);
		//订单业务
		OrderService orderService = new OrderServiceImpl();
		
		//多线程模拟高并发
		for (int i = 0; i < threadcount; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()+"========我已经准备好了....");
					//等待一起出发
					try {
						cb.await();
					} catch (Exception e) {
					}
					
					//模拟并发环境
					orderService.createOrder();
				}
			}).start();
		}
	}
}
