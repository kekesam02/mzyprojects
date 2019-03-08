package com.mzy.service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.apache.tomcat.jni.File;

/**
 * zookeeper分布式锁
 * @author mofeng
 *
 */
public class ZKDistributeLock implements Lock{

	//创建一个znode节点
	private String lockPath;
	//zkClient客户端，
	private ZkClient zkClient;
	
	public ZKDistributeLock(String lockpath) {
		//初始化节点
		this.lockPath = lockpath;
		zkClient = new ZkClient("localhost:2181");
		zkClient.setZkSerializer(new MyZkSerializer());
	}
	
	//A 用户  去执行业务的 B:
	@Override
	public void lock() {
		//如果获取不到锁，阻塞等待
		if(!tryLock()) {
			//没获得锁，阻塞自己
			waitForLock();//CountDownLatch.wait();
			//再次尝试
			lock();
		}
	}

	private void waitForLock() {
		CountDownLatch countDownLatch = new CountDownLatch(1);
		//创建一个节点监听器，这个节点监听器就是监听临时节点什么时候是（删除该节点）否释放锁，如一点删除，那么久触发handleDataDeleted放放，
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
		
		//订阅目录监听 注册
		zkClient.subscribeDataChanges(lockPath, listener);
		
		//如果存在，阻塞自己
		if(this.zkClient.exists(lockPath)) {
			try {
				countDownLatch.await();//如果存在就阻塞
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//取消注册
		zkClient.unsubscribeDataChanges(lockPath, listener);
	}


	@Override
	public boolean tryLock() {
		try {
			//创建临时节点节点 
			zkClient.createEphemeral(lockPath);
			return true;
		} catch (ZkNodeExistsException e) {//如果临时节点存在，那么报异常
			return false;
		}
	}

	

	@Override
	public void unlock() {
		zkClient.delete(lockPath);
	}

	@Override
	public Condition newCondition() {
		return null;
	}
	
	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return false;
	}
	
	@Override
	public void lockInterruptibly() throws InterruptedException {
		
	}

}
