package com.callor.food.model.food;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetFoodKr {

	@JsonProperty("header")
	public Header header;
		
	@JsonProperty("item")
	public List<FoodItem> item;

	public class Header {
		public String code;
		public String message;
	}

}
