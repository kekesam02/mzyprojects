/**
 * TODO濡沫系统平台<br/>
 * com.itbooking.strategy<br/>
 * DiscountStrategy.java<br/>
 *  创建人:mofeng <br/>
 * 时间：2019年2月22日-上午10:24:49 <br/>
 * 2019濡沫公司-版权所有<br/>
 */
package com.itbooking.strategy;

/**
 * 
 * DiscountStrategy<br/>
 * 创建人:mofeng<br/>
 * 时间：2019年2月22日-上午10:24:49 <br/>
 * @version 1.0.0<br/>
 * 
 */
public interface DiscountStrategy {

	public String type();
	public double discount(double fee);
}
