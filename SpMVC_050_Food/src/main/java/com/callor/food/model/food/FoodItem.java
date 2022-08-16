package com.callor.food.model.food;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString


public class FoodItem {

	@JsonProperty("UC_SEQ")
	private String UC_SEQ;// ": 70,
	
	@JsonProperty("MAIN_TITLE")
	private String MAIN_TITLE;// ": "만드리곤드레밥",
	
	@JsonProperty("GUGUN_NM")
	private String GUGUN_NM;// ": "강서구",
	
	@JsonProperty("LAT")
	private String LAT;// ": 35.177387,
	
	@JsonProperty("LNG")
	private String LNG;// ": 128.95245,
	
	@JsonProperty("PLACE")
	private String PLACE;// ": "만드리곤드레밥",
	
	@JsonProperty("TITLE")
	private String TITLE;// ": "만드리곤드레밥",
	
	@JsonProperty("SUBTITLE")
	private String SUBTITLE;// ": "",
	
	@JsonProperty("ADDR1")
	private String ADDR1;// ": "강서구 공항앞길 85번길 13",
	
	@JsonProperty("ADDR2")
	private String ADDR2;// ": "",
	
	@JsonProperty("CNTCT_TEL")
	private String CNTCT_TEL;// ": "051-941-3669",
	
	@JsonProperty("HOMEPAGE_URL")
	private String HOMEPAGE_URL;// ": "",
	
	@JsonProperty("USAGE_DAY_WEEK_AND_TIM")
	private String USAGE_DAY_WEEK_AND_TIME;// ": "11:00a.m. ~ 21:00p.m.",
	
	@JsonProperty("RPRSNTV_MENU")
	private String RPRSNTV_MENU;// ": "돌솥곤드레정식, 호박오리훈제",
	
	@JsonProperty("MAIN_IMG_NORMAL")
	private String MAIN_IMG_NORMAL;// ": "https://www.visitbusan.net/uploadImgs/files/cntnts/20191209162810545_ttiel",
	
	@JsonProperty("MAIN_IMG_THUMB")
	private String MAIN_IMG_THUMB;// ": "https://www.visitbusan.net/uploadImgs/files/cntnts/20191209162810545_thumbL",
	
	@JsonProperty("ITEMCNTNTS")
	private String ITEMCNTNTS;// ": "곤드레밥에는 일반적으로 건조 곤드레나물이 사용되는데, 이 곳은 생 곤드레나물을 사용하

}
