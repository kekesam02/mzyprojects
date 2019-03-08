package com.mzy.ordercode;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 订单生成器类
 * @author Administrator
 *
 */
public class OrderCodeGenerator {

	//自增长系列
	private long  i = 0;
	
	//按照年月日时分秒-自增长序列的规则生成订单编号
	public String getOrderCode() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss-");
		return dateFormat.format(date) + ++i;
	}
}
