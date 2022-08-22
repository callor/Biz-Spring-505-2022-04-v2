package com.ballza.lunch.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ballza.lunch.model.LunchRow;
import com.ballza.lunch.model.SearchVO;
import com.ballza.lunch.service.LunchService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/lunch")
public class LunchController {
	
	private final LunchService lunchService;

	public LunchController(LunchService lunchService) {
		this.lunchService = lunchService;
	}

	@RequestMapping(value = {"/",""}, method=RequestMethod.GET)
	public String home() {
		return "lunch/search";
	}

	
	@ResponseBody
	@RequestMapping(value = {"/",""}, method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public List<LunchRow> home(SearchVO searchVO) {
		String queryString = lunchService.queryString(searchVO);
		log.debug("Controller {}",queryString);
		return lunchService.getLunchRows(queryString);
	}
	

}
