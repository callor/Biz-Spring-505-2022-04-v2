package com.callor.exp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.exp.model.UserVO;
import com.callor.exp.service.UserService;

import lombok.extern.slf4j.Slf4j;

/*
 * Sts 도구를 사용하여 Project 를 시작했을때
 * 아무런 설정을 하지 않을 상태에서
 * 프로젝트의 home 화면을 열면
 * base-package.HomeContrroller 클래스에 선언된
 * home() method 가 Request를 수신하여 사용자에게 Response 를 수행한다
 * 라는 것을 알수 있다
 */
@Slf4j
@Controller
public class HomeController {
	
	private final UserService userService;
	public HomeController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value="/",method=RequestMethod.GET)
	public String home( ) {
		log.debug("HomeController Home Method");
		return "home";
	}
	
	/*
	 * 누군가(Web browser) "POST : http://localhost:8080/exp/" 로
	 * 요청을 보냈때 만약 username, password 라는 변수에 데이터를 담아서
	 * 보내면 String username, String password 변수에 받겠다라는 선언
	 */
	@RequestMapping(value="/",method=RequestMethod.POST)
	public String home(String username, String password) {
		log.debug("POST Home username = {}, passwoerd = {}",
							username, password);
		
		UserVO userVO = UserVO.builder()
						.username(username)
						.password(password)
						.realname("홍길동")
						.nickname("길동이")
						.tel("010-111-1111")
						.build();
		/*
		 * VO(DTO)클래스에 담긴 데이터는 어느순간 데이터자체가 null 일 수 있다
		 * UserVO userVO = userServicde.findByid("callor") 코드에서
		 * Table 에 callor 라는 id 가 없으면 userVO 에 담기는 
		 * 데이터는 null이 된다
		 * 
		 *  null.toString() 이라는 메서드를 실행하는 명령이 수행될텐데
		 *  null 데이터에는 어떠한 method 도 존재하지 않는다
		 *  이때 NullPointerException 이 발생한다
		 */
		if(userVO != null) {
			log.debug("Insert 하기 위한 데이터" + userVO.toString());	
		}
		
		// logback 에서 String Templagte( " {} ") 을 사용하면
		// logback 자체에서 null 값을 검증하여 표현하기 때문에
		// null pointer exception 이 발생하지 않는다
		log.debug("Insert 하기 위한 데이터 {}" , userVO);
		
		userService.insert(userVO);
		return "home";
	
	}
}
