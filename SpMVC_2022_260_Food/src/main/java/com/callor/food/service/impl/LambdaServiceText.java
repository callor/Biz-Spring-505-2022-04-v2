package com.callor.food.service.impl;

import com.callor.food.service.LambdaService;

public class LambdaServiceText {
	
	
	public static void main(String[] args) {
		
		// Java 8 에서 사용하는 functional interface 라는 개념
		// LambdaService interface 에 한개의 method 만 존재할 경우
		// 별도의 클래스를 선언하거나 하지 않고 직접 Lambda 코드를 
		// 사용하여 객체를 생성하고, 
		// 생성된 객체의 method 를 사용할수 있다
		LambdaService lService = (nation,name,num) ->{
			return nation + name + num;
		};
		String result = lService.getObject("대한민국","홍길동",100);
		System.out.println(result);
	
		result((nation,name, num)->{
			return null;
		});
		
	}
	private static String result(LambdaService lService) {
		return lService.getObject("Korea", "이몽룡", 20);
	}
	
}
