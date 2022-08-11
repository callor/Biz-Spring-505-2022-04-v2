package com.callor.todo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoVO {

	private long t_seq;
	private String t_username;
	private String t_sdate;
	private String t_stime;
	private String t_content;
	private String t_edate;
	private String t_etime;
	
	// boolean type 의 기본은 false 라는 것을 확인
	private boolean t_complete;
	
}
