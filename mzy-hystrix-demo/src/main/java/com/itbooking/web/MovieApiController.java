/**
 * TODO濡沫系统平台<br/>
 * com.itbooking.web<br/>
 * MovieApiController.java<br/>
 *  创建人:mofeng <br/>
 * 时间：2019年2月22日-下午2:00:26 <br/>
 * 2019濡沫公司-版权所有<br/>
 */
package com.itbooking.web;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itbooking.service.MovieService;

/**
 * 
 * MovieApiController<br/>
 * 创建人:mofeng<br/>
 * 时间：2019年2月22日-下午2:00:26 <br/>
 * @version 1.0.0<br/>
 * 
 */
@RestController
public class MovieApiController {

	@Autowired
	private MovieService movieService;
	
	@RequestMapping("/movie/query")
	public Map<String,Object> queryMovie(String movieCode) throws InterruptedException, ExecutionException{
		return movieService.queryMovie(movieCode);
	}
}
