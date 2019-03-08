package com.mzy;

import java.util.concurrent.CountDownLatch;

public class MaxThreadDemo {

	public static void main(String[] args) {
		
		CountDownLatch countDownLatch = new CountDownLatch(1);
		try {
			Thread.sleep(20000L);
		} catch (Exception e) {
		}
		for (int i = 0; i < 5000; i++) {
			new Thread(()->{
				try {
					countDownLatch.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
			System.out.println("i = "+i);
		}
	}
}
