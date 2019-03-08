//package com.mzy.service;
//
//import java.util.concurrent.locks.Lock;
//
//public class OrderPayService {
//
//	//描述
//	public void makeOrder(Long userId,Long itemId) {
//		Lock lock = new ZKDistributeLock2("/storenumlock");
//		lock.lock();
//		try {
//			//根据商品的id查询商品的库存
//			int storenum = itemService.getItem(itemId).getStoreNum();
//			if(storenum<=0) {
//				return ServerResponse.createError("库存不足!!!");
//			}
//			
//			new OrderServiceImpl().createOrder();
//		} finally {
//			lock.unlock();
//		}
//	}
//}
