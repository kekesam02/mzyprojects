package com.mzy.service;

import java.util.concurrent.CountDownLatch;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

public class ZKWatcherDemo {

	public static void main(String[] args) {
		//创建zk客户端
		ZkClient client = new ZkClient("localhost:2181");
		client.setZkSerializer(new MyZkSerializer());
		
		CountDownLatch countDownLatch  = new CountDownLatch(3);
		//1:启动zk服务器
		//2:create /rumo/aaa 666
		//3:set /rumo/aaa 66645
		//4:delete /rumo/aaa
		//订阅数据改变时触发
		client.subscribeDataChanges("/rumo/aaa",new IZkDataListener() {
			
			@Override
			public void handleDataDeleted(String dataPath) throws Exception {
				System.out.println("----------------收到节点被删除了----------------");//1
				countDownLatch.countDown();
			}
			
			@Override
			public void handleDataChange(String dataPath, Object data) throws Exception {
				System.out.println("----------------收到节点被改变 了----------------"+data);//3
				countDownLatch.countDown();
			}
		} );
		
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("over finished ");
		
	}
}
