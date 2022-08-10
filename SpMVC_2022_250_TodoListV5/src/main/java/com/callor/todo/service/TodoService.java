package com.callor.todo.service;

import com.callor.todo.persistance.TodoDao;

// Generic 에 선언된 기본 CRUD 메서드와 TodoDao 선언된 findByUsername 메서드를
// 상속받게 된다
public interface TodoService extends TodoDao{

}
