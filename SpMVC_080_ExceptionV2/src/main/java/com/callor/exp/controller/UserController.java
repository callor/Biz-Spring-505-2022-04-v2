package com.callor.exp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.exp.model.UserVO;
import com.callor.exp.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/user")
public class UserController {
	
	
	private final UserService userService;
	public UserController(UserService userSerivce) {
		this.userService = userSerivce;
	}

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
	
	/*
	 * form method=POST 로 데이터가 전달되면 수신하여 처리할 부분
	 * 
	 * form 으로 부터 전달된 데이터를 받을때 개별적인 변수를 매개변수로 선언하여
	 * 받을수 도 있지만, 가급적 VO 클래스를 선언하고 VO 클래의 객체를 통하여
	 * 데이터를 받는 것이 좋겠다
	 * 
	 * @ModelAttribute
	 * VO 클래스로 선언된 객체를 통해서 form 에서 전송된 데이터를 받아라 라는 의미
	 * 
	 */
	@RequestMapping(value="/join",method=RequestMethod.POST)
	public String join(@ModelAttribute UserVO userVO) {
		
		// NullpointerException 이 발생할 수 있는 코드
		log.debug("form 에서 받은 데이터 " + userVO.toString());
		
		// NullPointerException 에서 상대적으로 안전하다
		log.debug("form 에서 받은 데이터 {}", userVO);
		userService.insert(userVO);
		return null;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(String error, Model model) {
		model.addAttribute("error", error);
		return null;
	}
	
	
}





