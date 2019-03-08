package com.mzy.util;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by mofeng
 */
@Component
public class RedisPool {
    private static JedisPool pool;//jedis连接池
    private static Integer maxTotal = Integer.parseInt(PropertiesUtil.getProperty("redis.max.total","20")); //最大连接数
    private static Integer maxIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.max.idle","20"));//在jedispool中最大的idle状态(空闲的)的jedis实例的个数
    private static Integer minIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.min.idle","20"));//在jedispool中最小的idle状态(空闲的)的jedis实例的个数

    private static Boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.borrow","true"));//在borrow一个jedis实例的时候，是否要进行验证操作，如果赋值true。则得到的jedis实例肯定是可以用的。
    private static Boolean testOnReturn = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.return","true"));//在return一个jedis实例的时候，是否要进行验证操作，如果赋值true。则放回jedispool的jedis实例肯定是可以用的。

    private static String redisIp = PropertiesUtil.getProperty("redis.ip");
    private static Integer redisPort = Integer.parseInt(PropertiesUtil.getProperty("redis.port"));
    private static String password = PropertiesUtil.getProperty("redis.password");

    public RedisPool() {
    	initPool();
    }

    private  void initPool(){
        JedisPoolConfig config = new JedisPoolConfig();

        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);

        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);

        config.setBlockWhenExhausted(true);//连接耗尽的时候，是否阻塞，false会抛出异常，true阻塞直到超时。默认为true。

        pool = new JedisPool(config,redisIp,redisPort,1000*2,password);
    }


    public  Jedis getJedis(){
        return pool.getResource();
    }


    public  void returnBrokenResource(Jedis jedis){
    	jedis.close();
    }


    public  void returnResource(Jedis jedis){
    	jedis.close();
    }
    
    public Long getIncr(Jedis jedis,String key,int timeout) {
    	try {
    		long id = jedis.incr(key);
    		if(timeout>0) {
    			jedis.expire(key, timeout);
    		}
    		return id;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			if(jedis!=null) {
				jedis.close();
			}
		}
    }


    public static void main(String[] args) {
    	RedisPool pool = new RedisPool();
        Jedis jedis = pool.getJedis();
        jedis.set("username","zhangsan");
        pool.returnResource(jedis);
        System.out.println("program is end");
    }



}