/**
 * TODO濡沫系统平台<br/>
 * com.itbooking.strategy<br/>
 * VipDiscountStrategy.java<br/>
 *  创建人:mofeng <br/>
 * 时间：2019年2月22日-上午10:25:54 <br/>
 * 2019濡沫公司-版权所有<br/>
 */
package com.itbooking.strategy;

import org.springframework.stereotype.Service;

/**
 * 
 * VipDiscountStrategy<br/>
 * 创建人:mofeng<br/>
 * 时间：2019年2月22日-上午10:25:54 <br/>
 * @version 1.0.0<br/>
 * 
 */
@Service
public class SVipDiscountStrategy implements DiscountStrategy{

	
	@Override
	public String type() {
		return "vip";
	}

	
	@Override
	public double discount(double fee) {
		return fee * 0.8;
	}

}
