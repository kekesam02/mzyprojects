package com.mzy;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mzy.service.IOrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MzyMakeOrderUniqueMainApplicationTests {

	@Autowired
	//@Qualifier("uuidServiceImpl")
	//@Qualifier("snowflakeIdWorkerImpl")
	@Qualifier("redisOrderServiceImpl")
	private IOrderService orderService;
	public  final int threadNum = 100;
	private  CountDownLatch countDownLatch = new CountDownLatch(threadNum);
	
	@Test
	public  void testorder() throws InterruptedException {
		System.out.println("******************test start****************");
		for (int i = 0; i < threadNum; i++) {
			new Thread(new OrderThread()).start();
			countDownLatch.countDown();
		}
		Thread.currentThread();
		Thread.sleep(3000);
		System.out.println("******************test end****************");
	}
	
	class OrderThread implements Runnable{

		@Override
		public void run() {
			try {
				countDownLatch.await();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			orderService.getOrderId();
		}
		
	}
}

