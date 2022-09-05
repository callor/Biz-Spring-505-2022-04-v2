package com.ballza.lunch.service;

import java.util.List;

import com.ballza.lunch.model.LunchRow;
import com.ballza.lunch.model.SearchVO;


public interface LunchService {
	
	public String queryString(SearchVO search);
	public List<LunchRow> getLunchRows(String queryString);

}
