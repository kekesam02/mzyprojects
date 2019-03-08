package com.itbooking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.itbooking.design.OrderService;
import com.itbooking.strategy.FeeCalculationService;
import com.itbooking.strategy.FeeCalculationService3;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MzyDesginPatternApplicationTests {
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private FeeCalculationService  feeCalculationService;
	@Autowired
	private FeeCalculationService3  feeCalculationService3;

	@Test
	public void contextLoads() {
		orderService.saveOrder();
	}
	
	@Test
	public void tests2() {
		double fee = feeCalculationService.calculation("svip",100);
		System.out.println("最终消费是："+ fee);
	}
	
	@Test
	public void tests3() {
		double fee = feeCalculationService3.calculation("vip",100);
		System.out.println("最终消费是："+ fee);
		double fee2 = feeCalculationService3.calculation("normal",100);
		System.out.println("最终消费是："+ fee2);
		double fee3 = feeCalculationService3.calculation("svip",100);
		System.out.println("最终消费是："+ fee3);
		double fee4 = feeCalculationService3.calculation("hvip",100);
		System.out.println("最终消费是："+ fee4);
	}

}
