package com.callor.food.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.callor.food.model.FoodItem;
import com.callor.food.service.FoodService;

@Controller
@RequestMapping(value="/food")
public class FoodController {
	
	private final FoodService foodService;
	public FoodController(FoodService foodService) {
		this.foodService = foodService;
	}

	@RequestMapping(value= {"/",""},method=RequestMethod.GET)
	public String home() {
		String queryString = foodService.queryString(null);
		foodService.getFoodItems(queryString);
		return "home";
	}

	@ResponseBody
	@RequestMapping(value= "/json",method=RequestMethod.GET,
			produces = "application/json;charset=UTF-8")
	public List<FoodItem> json() {
		String queryString = foodService.queryString(null);
		List<FoodItem> foods = foodService.getFoodItems(queryString);
		return foods;
	}
}
