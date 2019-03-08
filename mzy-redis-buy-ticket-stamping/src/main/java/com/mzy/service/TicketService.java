package com.mzy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class TicketService {
	
	private static final Logger logger = LoggerFactory.getLogger(TicketService.class);
	
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	
	@Autowired
	DataBaseService databaseService;
	
	
	//@Cacheable() 不管你是用注解的来实现还是用redistemplate来实现都会实现 1 2 3 的步骤。
	public Object queryTicketStock(final String ticketSeq) {
		
		//1:先从redis中读取余票信息
		String value = stringRedisTemplate.opsForValue().get(ticketSeq);
		if(value!=null) {
			System.out.println(Thread.currentThread().getName()+" 缓存中获取余票数据是："+value);
			return value;
		}
		
		//2:缓存没有从数据库中获取
		value = databaseService.queryForDatabase(ticketSeq);
		System.out.println(Thread.currentThread().getName()+" 从数据库获取："+value);
		
		//塞到redis缓存中120秒过期时间 (缓存时间)
		final String v = value;
		stringRedisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.setEx(ticketSeq.getBytes(), 120, v.getBytes());
			}
		});  
		
		return value;
	}
}
