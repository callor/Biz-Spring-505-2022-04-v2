package com.callor.food.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FoodItem {

	@JsonProperty("UC_SEQ")
	private String UC_SEQ;		//	콘텐츠ID
	
	@JsonProperty("MAIN_TITL")
	private String MAIN_TITLE;	//	콘텐츠명
	
	@JsonProperty("GUGUN_NM")
	private String GUGUN_NM;	//	구군
	
	@JsonProperty("LAT")
	private String LAT;			//	위도
	
	@JsonProperty("LNG")
	private String LNG;			//	경도
	
	@JsonProperty("PLACE")
	private String PLACE;		//	장소
	
	@JsonProperty("TITLE")
	private String TITLE;		//	제목
	
	@JsonProperty("SUBTITLE")
	private String SUBTITLE;	//	부제목
	
	@JsonProperty("ADDR1")
	private String ADDR1;		//	주소
	
	@JsonProperty("ADDR2")
	private String ADDR2;		//	주소 기타
	
	@JsonProperty("CNTCT_TEL")
	private String CNTCT_TEL;	//	연락처
	
	@JsonProperty("HOMEPAGE_URL")
	private String HOMEPAGE_URL;	//	홈페이지
	
	@JsonProperty("USAGE_DAY_WEEK_AND_TIME")
	private String USAGE_DAY_WEEK_AND_TIME;		//	운영 및 시간
	
	@JsonProperty("RPRSNTV_MENU")
	private String RPRSNTV_MENU;		//	대표메뉴
	
	@JsonProperty("MAIN_IMG_NORMAL")
	private String MAIN_IMG_NORMAL;		//	이미지URL
	
	@JsonProperty("MAIN_IMG_THUMB")
	private String MAIN_IMG_THUMB;		//	썸네일이미지URL
	
	@JsonProperty("ITEMCNTNTS")
	private String ITEMCNTNTS;			//	상세내용
	
}
