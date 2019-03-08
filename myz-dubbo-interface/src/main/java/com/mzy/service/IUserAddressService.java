package com.mzy.service;

import java.util.List;

import com.mzy.pojo.UserAddress;

public interface IUserAddressService {

	/**
	 * 根据用户ID查询用户收货地址
	 * @param userId
	 * @return
	 */
	public List<UserAddress> getUserAddresses(Long userId); 
}
