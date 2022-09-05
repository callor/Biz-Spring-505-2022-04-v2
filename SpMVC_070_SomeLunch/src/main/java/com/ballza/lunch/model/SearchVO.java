package com.ballza.lunch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchVO {
	
	private String s_ofcode; // 교육청 코드
	
	private String s_sccode; // 학교 코드
	private String s_scname; // 학교명
	
	private String s_sdate; // 시작일자
	private String s_edate; // 종료일자

	
}
