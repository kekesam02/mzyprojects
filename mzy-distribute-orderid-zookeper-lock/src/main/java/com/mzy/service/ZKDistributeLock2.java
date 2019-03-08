package com.mzy.service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

public class ZKDistributeLock2 implements Lock{
	
	//为什么要创建根目录。业务区分
	private String lockPath;
	private ZkClient zkClient;
	//当前目录
	private String currentPath;
	//关注前一个节点
	private String beforePath;
	
	public ZKDistributeLock2(String lockpath) {
		super();
		this.lockPath = lockpath;
		//连接zookeeper
		zkClient = new ZkClient("localhost:2181");
		//序列化操作
		zkClient.setZkSerializer(new MyZkSerializer());
		//判断节点释放存在
		if(!this.zkClient.exists(lockpath)) {
			try {
				this.zkClient.createPersistent(lockpath);
			} catch (ZkNodeExistsException e) {
			}
		}
	}
	
	@Override
	public void lock() {
		//如果获取不到锁，阻塞等待
		if(!tryLock()) {
			//没获得锁，阻塞自己
			waitForLock();
			//再次尝试
			lock();
		}
	}

	
	private void waitForLock() {
		CountDownLatch countDownLatch = new CountDownLatch(1);
		IZkDataListener listener = new IZkDataListener() {
			@Override
			public void handleDataDeleted(String dataPath) throws Exception {
				System.out.println("-----------收到节点被删除了------------------");
				//释放锁
				countDownLatch.countDown();
			}
			
			@Override
			public void handleDataChange(String dataPath, Object data) throws Exception {
			}
		};
		
		//订阅目录监听
		zkClient.subscribeDataChanges(beforePath, listener);
		
		//如果存在，阻塞自己
		if(this.zkClient.exists(beforePath)) {
			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//取消注册
		zkClient.unsubscribeDataChanges(beforePath, listener);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		
	}

	@Override
	public boolean tryLock() {
		//取号码，在lockpath下面创建子节点 
		if(this.currentPath==null) {
			currentPath = this.zkClient.createEphemeralSequential(lockPath+"/", "0000");
		}
		
		//获取所有的子节点
		List<String> childrens = this.zkClient.getChildren(lockPath);
		
		//排序
		Collections.sort(childrens);
	
		//判断当前节点是否是最小的
		if(currentPath.equals(lockPath+"/"+childrens.get(0))) {
			return true;//A获取到Lock
		}else {
			//取到前一个
			//得到字节的索引化
			//找到当前节点的索
			int currentIndex = childrens.indexOf(currentPath.substring(lockPath.length()+1));
			//找到当前索引的前一个节点
			beforePath = lockPath+"/"+childrens.get(currentIndex-1);//A
		}
		
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return false;
	}

	@Override
	public void unlock() {
		zkClient.delete(currentPath);
	}

	@Override
	public Condition newCondition() {
		return null;
	}

}
