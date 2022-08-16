package com.callor.food.service;

import java.util.List;

import com.callor.food.model.food.FoodItem;

public interface FoodService {

	public String queryString(String cat, String search);
	public List<FoodItem> getObjectList(String queryString);
	
}
