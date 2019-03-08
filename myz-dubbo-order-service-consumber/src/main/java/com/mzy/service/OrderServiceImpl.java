package com.mzy.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mzy.pojo.UserAddress;

@Service
public class OrderServiceImpl implements IOrderService{

	@Reference(loadbalance="roundrobin")
	private IUserAddressService userAddressService;  
	
	@Override
	public void makeOrder(Long userId, Long itemId) {
		System.out.println("订单执行成功!!!!");
		List<UserAddress> userAddress =  userAddressService.getUserAddresses(userId);
		for (UserAddress userAddress2 : userAddress) {
			System.out.println(userAddress2);
		}
	}

	
	
}
