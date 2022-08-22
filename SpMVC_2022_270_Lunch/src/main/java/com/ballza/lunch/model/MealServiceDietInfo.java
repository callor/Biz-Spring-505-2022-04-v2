package com.ballza.lunch.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@JacksonXmlRootElement(localName = "mealServiceDietInfo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MealServiceDietInfo {
	
		@JacksonXmlProperty(localName = "head")
		public String head;
		
		@JacksonXmlElementWrapper(useWrapping = false)
		@JacksonXmlProperty(localName = "row")
		public List<LunchRow> row;
		
		@AllArgsConstructor
		@NoArgsConstructor
		@ToString
		public class Head{
			public String list_total_count;
			public Result result;
			
		}

		@AllArgsConstructor
		@NoArgsConstructor
		@ToString
		public class Result {
			public String CODE;
			public String MESSAGE;
		}
		
}
