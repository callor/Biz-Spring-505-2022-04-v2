package com.ballza.lunch.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
		return "home";
	}

	
	@ResponseBody
	@RequestMapping(value = {"/",""}, method=RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public List<LunchRow> home(SearchVO searchVO, Model model) {
		String queryString = lunchService.queryString(searchVO);
		log.debug("Controller {}",queryString);
		
		return lunchService.getLunchRows(queryString);
	}

	/* 검색코드
	@ResponseBody
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public List<LunchRow> home() {
		String queryString = lunchService.queryString(null);
		List<LunchRow> lunchs = lunchService.getLunchRows(queryString);
		return lunchs;
	}
	
	@ResponseBody
	@RequestMapping(value = {"/", ""}, method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String home(SearchVO search, Model model) {
		
		log.debug("받은 검색어 {}", search);
		
		String queryString2 = lunchService.queryString(null);
		List<LunchRow> lunchs2 = lunchService.getLunchRows(queryString2);

		model.addAttribute("LUNCHS", lunchs2);
		
		String queryString3 = lunchService.queryString(search);
		List<LunchRow> lunchs3 = lunchService.getLunchRows(queryString3);
		
		List<LunchRow> searchList = new ArrayList<>();
		
		
		if (search != null) {
			for (LunchRow row : lunchs3) {
				if (row.getMLSV_YMD().contains(queryString3)) {
					searchList.add(row);
				}
			}
			if (searchList.size() <1) {
				model.addAttribute("ERROR","FAIL");
			}
			model.addAttribute("LUNCHS", searchList);
		}
		
		return "home";
		
	}
	*/


/*
	@RequestMapping(value = {"/lunch_list"}, method = RequestMethod.GET)
	public String lunch_list(Model model) {

		String queryString = lunchService.queryString(null);
		List<LunchRow> lunchs = lunchService.getLunchRows(queryString);

		model.addAttribute("LUNCHS", lunchs);

		return "home";
	}
*/	 
	
	@RequestMapping(value = "/{MLSV_YMD}/menu", method = RequestMethod.GET)
	public String detail(@PathVariable("MLSV_YMD") String MLSV_YMD, Model model) {

		String queryString = lunchService.queryString(null);
		List<LunchRow> lunchs = lunchService.getLunchRows(queryString);

		LunchRow row = null;
		for (LunchRow VV : lunchs) {
			if (VV.getMLSV_YMD().equals(MLSV_YMD)) {
				row = VV;
				break;
			}

		}
		log.debug("실험결과 : " + row);
		model.addAttribute("LUNCH", row);

		return "detail/menu";
	}
	

}