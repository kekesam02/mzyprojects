/**
 * TODO濡沫系统平台<br/>
 * com.itbooking.service<br/>
 * MovieService.java<br/>
 *  创建人:mofeng <br/>
 * 时间：2019年2月22日-下午2:01:12 <br/>
 * 2019濡沫公司-版权所有<br/>
 */
package com.itbooking.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * MovieService<br/>
 * 创建人:mofeng<br/>
 * 时间：2019年2月22日-下午2:01:12 <br/>
 * @version 1.0.0<br/>
 * 
 */
@Service
public class MovieService {
	
	class Request{
		String movieCode;
		CompletableFuture<Map<String,Object>> future; // 接受结果
	}
	
	//用对了存储所有请求 ,积攒请求，（每隔N毫秒，去把队列中的批量执行一次）
	LinkedBlockingDeque<Request> queue = new LinkedBlockingDeque<>();
	
	// 定时任务的实现 构造器-->自动注入-->PostConstrut-->InitializingBean-->xml中配置init方法
	@PostConstruct //在对象初始化之后调用的方法
	public void init() {
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		scheduledExecutorService.scheduleAtFixedRate(()->{
			// 1: 去除queue的请求，生产一次批量查询
			int size = queue.size();
			if(size == 0) {
				return;
			}
			
			ArrayList<Request> requests = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				Request request = queue.poll();
				requests.add(request);
			}
			
			System.out.println("批量处理数据量是 :" + size);
			
			// 2: 组装一个批量查询
			ArrayList<String> movieCodes = new ArrayList<>(); 
			for (Request request : requests) {
				movieCodes.add(request.movieCode);
			}
			
			// 查询所有的数据
			List<Map<String,Object>> responses = queryServiceRemoteCall.queryMovieInfoByCode(movieCodes);
			
			// 3:将结果相应给每个单独的用户请求，由定时任务处理线程，->1000个用户的请求线程
			
			HashMap<String, Map<String,Object>> responseMap = new HashMap<>();
			
			for (Map<String, Object> response : responses) {
				String code = response.get("code").toString();
				responseMap.put(code, response);
			}
			
			for (Request request : requests) {
				// 根据请求中携带的能表示唯一产生，去批量查询的结果中找到对应的响应
				Map<String, Object> result = responseMap.get(request.movieCode);
				// 将线程的结果返回到对应的请求线程
				request.future.complete(result);
			}
			
		}, 0,10,TimeUnit.MILLISECONDS);
	}

	@Autowired
	private QueryServiceRemoteCall queryServiceRemoteCall;
	
	//1000个用户请求，1000个线程去执行
	public Map<String,Object> queryMovie(String movieCode) throws InterruptedException, ExecutionException{
		
		// 1000 怎么样才能变成，更少的接口调用
		// 思路：将不同用户的同类请求合并起来
		// 并非立刻发起接口调用，请求收集起来，再进行
		Request request = new Request();
		request.movieCode = movieCode;
		
		// juc中有一个Future 异步变成
		CompletableFuture<Map<String,Object>> future = new CompletableFuture<>();
		request.future = future;
		//请求添加到队列中
		queue.add(request);
		//此处get方法，会阻塞现场运行，知道future有返回才得到结果
		return future.get();
		// 什么时候返回结果呢？
		// return queryServiceRemoteCall.queryMovieInfoByCode(movieCode);
	}
	
	
	
	
	
	
	
	
	
	
	
}
