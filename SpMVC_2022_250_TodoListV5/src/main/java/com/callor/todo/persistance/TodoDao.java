package com.callor.todo.persistance;

import java.util.List;

import com.callor.todo.model.TodoVO;

public interface TodoDao extends GenericDao<TodoVO, Long>{
	
	// username 을 매개변수로 받아
	// username 사용자의 todoList 를 return
	public List<TodoVO> findByUsername(String username);
	
	public void create_todo_table();
	
}
