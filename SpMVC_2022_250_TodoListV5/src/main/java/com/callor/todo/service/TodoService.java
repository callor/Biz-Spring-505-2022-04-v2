package com.callor.todo.service;

import com.callor.todo.persistance.TodoDao;

// Generic 에 선언된 기본 CRUD 메서드와 TodoDao 선언된 findByUsername 메서드를
// 상속받게 된다
public interface TodoService extends TodoDao{
	
	// todo 항목을 완료하는 method 가 프로젝트 진행중에 필요하게 되었다
	// interface 의 설계가 변경될텐데
	//		이 인터페이스를 상속받은 모든 클래스에 영향이 미친다
	
	public int todoComp(String seq);
	

}
