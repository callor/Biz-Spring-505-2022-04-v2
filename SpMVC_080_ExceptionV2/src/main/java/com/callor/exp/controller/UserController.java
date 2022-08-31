package com.callor.exp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/user")
public class UserController {

	/*
	 * return 을 null 하는 경우
	 * view file 을 찾을때 이 method 가 호출된
	 * RequestURL(Mapping) 부분이 return 부분에
	 * 자동으로 설정된다
	 * 
	 * 만약 RequestMapping 을 변경하면서
	 * view 파일의 경로나 파일명을 같이 변경하고자할때
	 * return null 로 설정이 되어 있으면 Mapping 부분과
	 * 파일 관련된 부분만 설정하면 된다
	 * return 은 자동으로 설정된다
	 */
	
	@RequestMapping(value="/join",method=RequestMethod.GET)
	public String join() {
		return null;
	}
	
}





