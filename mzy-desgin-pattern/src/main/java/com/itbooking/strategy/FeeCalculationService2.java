/**
 * TODO濡沫系统平台<br/>
 * com.itbooking.strategy<br/>
 * FeeCalculationService.java<br/>
 *  创建人:mofeng <br/>
 * 时间：2019年2月22日-上午10:20:52 <br/>
 * 2019濡沫公司-版权所有<br/>
 */
package com.itbooking.strategy;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 
 * FeeCalculationService<br/>
 * 创建人:mofeng<br/>
 * 时间：2019年2月22日-上午10:20:52 <br/>
 * @version 1.0.0<br/>
 * 
 */
@Service
public class FeeCalculationService2 {

	
	@Resource(name="vipDiscountStrategy")
	private DiscountStrategy discountStrategy;
	//收银
	/**
	 * 
	 * (这里用一句话描述这个方法的作用)<br/>
	 * 方法名：calculation<br/>
	 * 创建人：mofeng <br/>
	 * 时间：2019年2月22日-上午10:22:22 <br/>
	 * 手机:1564545646464<br/>
	 * @param type  vip,normal 
	 * @param price 收费
	 * @return double<br/>
	 * @exception <br/>
	 * @since  1.0.0<br/>
	 */
	public double calculation(String type,double price) {
		return discountStrategy.discount(price);
	}
	
}