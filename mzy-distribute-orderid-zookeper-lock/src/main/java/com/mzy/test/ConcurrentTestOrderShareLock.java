package com.mzy.test;

import java.util.concurrent.CyclicBarrier;

import com.mzy.service.OrderService;
import com.mzy.service.OrderServiceShareLockImpl;

public class ConcurrentTestOrderShareLock {

	
	public static void main(String[] args) {
		//并发数50
		int threadcount = 100;
		//循环屏障
		CyclicBarrier cb = new CyclicBarrier(threadcount);
		
		
		//多线程模拟高并发
		for (int i = 0; i < threadcount; i++) {
			new Thread(new Runnable() {
				//订单业务
				//并发执行和创建订单生成类，来模拟集群环境
				OrderService orderService = new OrderServiceShareLockImpl();
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()+"========我已经准备好了....");
					//等待一起出发
					try {
						cb.await();
					} catch (Exception e) {
					}
					orderService.createOrder();
				}
			}).start();
		}
	}
}
