package com.mzy.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.mzy.pojo.UserAddress;

/**
 * 
 * @author mzy
 *
 */
@Component
@Service//暴露的服务
public class UserAddressImpl implements IUserAddressService{

	@Override
	public List<UserAddress> getUserAddresses(Long userId) {
		UserAddress address1 = new UserAddress(1L, "北京海淀区xxx458室 7004");
		UserAddress address2 = new UserAddress(2L, "北京海淀区xxx458室 7004");
		return Arrays.asList(address1,address2);
	}

}
