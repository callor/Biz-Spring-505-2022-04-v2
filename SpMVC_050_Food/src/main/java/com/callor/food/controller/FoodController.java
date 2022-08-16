package com.callor.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.callor.food.model.food.FoodItem;
import com.callor.food.service.FoodService;

@Controller
@RequestMapping(value="/food")
public class FoodController {
	
	@Autowired
	private FoodService foodService;
	
	@ResponseBody
	@RequestMapping(value={"/",""},method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public List<FoodItem> home() {
		String queryString = foodService.queryString(null, null);
		List<FoodItem> foods = foodService.getObjectList(queryString);
		return foods;
	}

}
