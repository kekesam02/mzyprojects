/**
 * TODO濡沫系统平台<br/>
 * com.itbooking.service<br/>
 * QueryServiceRemoteCall.java<br/>
 *  创建人:mofeng <br/>
 * 时间：2019年2月22日-下午2:02:06 <br/>
 * 2019濡沫公司-版权所有<br/>
 */
package com.itbooking.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

/**
 * 
 * QueryServiceRemoteCall<br/>
 * 创建人:mofeng<br/>
 * 时间：2019年2月22日-下午2:02:06 <br/>
 * @version 1.0.0<br/>
 * 
 */
@Service
public class QueryServiceRemoteCall {

	
	/**
	 * 
	 * 模拟远程调用接口
	 * 
	 * 方法名：queryMovieInfoByCode<br/>
	 * 创建人：徐柯老师 <br/>
	 * 时间：2019年2月22日-下午5:13:55 <br/>
	 * 手机:15074826437<br/>
	 * QQ : 1571828260
	 * @param movieCode
	 * @return Map<String,Object><br/>
	 * @exception <br/>
	 * @since  1.0.0<br/>
	 */
	public Map<String,Object> queryMovieInfoByCode(String movieCode){
		
		try {
			Thread.sleep(50);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		Map<String,Object> map = new HashMap<>();
		map.put("movieId",new Random().nextInt(99999999));
		map.put("code",movieCode);
		map.put("star","xuke");
		map.put("isHadsom",true);
		return map;
	}
	
	
	/**
	 * 批量查询--调用远程接口
	 * 方法名：queryMovieInfoByCode<br/>
	 * 创建人：徐柯老师 <br/>
	 * 时间：2019年2月22日-下午5:15:12 <br/>
	 * 手机:15074826437<br/>
	 * QQ : 1571828260
	 * @param movieCode
	 * @return List<Map<String,Object>><br/>
	 * @exception <br/>
	 * @since  1.0.0<br/>
	 */
	public List<Map<String,Object>> queryMovieInfoByCode(List<String> movieCodes){
		
		List<Map<String,Object>> result = new ArrayList<>();
		for (String movieCode : movieCodes) {
			Map<String,Object> map = new HashMap<>();
			map.put("movieId",new Random().nextInt(99999999));
			map.put("code",movieCode);
			map.put("star","xuke");
			map.put("isHadsom",true);
			result.add(map);
		}
		
		return result;
	}
	
	
	
	
}
