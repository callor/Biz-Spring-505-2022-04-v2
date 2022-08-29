package com.callor.exp;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		log.debug("여기는 홈 컨트롤러, 홈 method");
		return "home";
	}

	@RequestMapping(value = "/my", method = RequestMethod.GET)
	public String my(String name, Model model) {
		log.debug("여기는 홈 컨트롤러, my method");
		log.debug("name = {}",name);
		return "home";
	}

	
}
