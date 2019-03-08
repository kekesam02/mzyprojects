package com.mzy.redis;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mzy.service.IOrderService;
import com.mzy.util.RedisPool;

@Service("redisOrderServiceImpl")
public class RedisOrderServiceImpl implements IOrderService {

	
	@Autowired
	private RedisPool redisPool;
	
	
	public void getOrderId() {
		String key = "system:order:id";
		String prefix = getPrefix(new Date());//key: 系统名 + 模块 + 功能 + key
		long id = redisPool.getIncr(redisPool.getJedis(), key, -1);
		System.out.println("insert into order_id(id) values('"+prefix+String.format("%1$05d", id)+"');");
	}


	private String getPrefix(Date date) { // 19 005 00 005
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		String dayFmt = String.format("%1$03d", day);
		String hourFmt = String.format("%1$02d", hour);
		return(year-2000)+dayFmt+hourFmt;
	}

}