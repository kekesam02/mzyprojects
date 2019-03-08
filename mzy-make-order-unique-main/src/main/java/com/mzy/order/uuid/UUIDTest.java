package com.mzy.order.uuid;

public class UUIDTest {

	public static void main(String[] args) {
		for (int i = 1; i <=100; i++) {
			new UUIDServiceImpl().getOrderId();
		}
	}
}
