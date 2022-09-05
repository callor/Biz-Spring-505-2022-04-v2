package com.ballza.lunch.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ballza.lunch.model.SearchVO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class HomeController {

	/*
	private LunchService lunchService;
	
	
	public HomeController(LunchService lunchService) {
		this.lunchService = lunchService;
	}


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		String queryString = lunchService.queryString(null);
		List<LunchRow> lunchs = lunchService.getLunchRows(queryString);

		model.addAttribute("LUNCHS", lunchs);

		return "home";
	}
	*/
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
	
	/*
	 * 
	 * Spring security 가 적용된 프로젝트에서는
	 * form method=POST 로 전송할때는 반드시 Token 이 따라 와야 한다
	 * 그렇지 않으면 403 오류가 발생한다
	 * 
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String home(SearchVO searchVO) {
		
		log.debug("검색정보: {}", searchVO);
		return "home";
	}

	
}


