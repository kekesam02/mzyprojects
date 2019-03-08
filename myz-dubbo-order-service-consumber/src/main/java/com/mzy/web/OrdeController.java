package com.mzy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mzy.service.IOrderService;

@RestController
public class OrdeController {

	
	@Autowired
	private IOrderService orderService;
	
	@RequestMapping("/makeorder")
	public String makeorder() {
		orderService.makeOrder(1L, 1L);
		return "success";
	}
}
