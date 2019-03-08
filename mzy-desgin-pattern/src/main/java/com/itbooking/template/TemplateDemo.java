/**
 * TODO濡沫系统平台<br/>
 * com.itbooking.template<br/>
 * TemplateDemo.java<br/>
 *  创建人:mofeng <br/>
 * 时间：2019年2月22日-上午9:13:06 <br/>
 * 2019濡沫公司-版权所有<br/>
 */
package com.itbooking.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 
 * TemplateDemo<br/>
 * 创建人:mofeng<br/>
 * 时间：2019年2月22日-上午9:13:06 <br/>
 * @version 1.0.0<br/>
 * 
 */
public class TemplateDemo {

	
	
	
	//jdbctemplate redistemplate mongobtemplate rabiitmqtemplate ......kafkateamplate
	public void test() {
		RedisTemplate<String,String> redisTemplate = null;
		redisTemplate.execute(new RedisCallback() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				//connection 数据操作  http调用操作  ，redis操作，kafka操作等
				return null;
			}
		});
	}
	
	public void method1() {
		//1:创建连接对象---不变 
		//2:初始化连接---不变
			//3:利用连接，执行业务--这部分编号 ---jedis对象 jdbc 要一个connection
		//4:放回到连接池---不变
		
		KJdbcTemplate jdbcTemplate = null;
		
		//spring data---redis/mongo/ne/jdbctemplate/
		
//		jdbcTemplate.execute(new ConnectionCallback() {
//			@Override
//			public void donConnection(Connection connection) {
//				//connection 
//			}
//		});
//		
//		jdbcTemplate.query(sql, new ResultQueryCallback() {
//			
//			@Override
//			public void query(PreparedStatement statement, ResultSet rs) {
//				
//			}
//		});
		
	}
	
	
	
	
}
