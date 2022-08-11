package com.callor.todo.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.callor.todo.model.TodoVO;
import com.callor.todo.persistance.TodoDao;
import com.callor.todo.service.TodoService;

@Service
public class TodoServiceImplV1 implements TodoService{

	@Autowired
	private TodoDao todoDao;
	
	// username 사용자의 todoList Dao 로부터 SELECT 하여
	// 즉시 return
	@Override
	public List<TodoVO> findByUsername(String username) {
		// TODO Auto-generated method stub
		return todoDao.findByUsername(username);
	}

	@Override
	public List<TodoVO> selectAll() {
		// TODO Auto-generated method stub
			return todoDao.selectAll();
	}

	@Override
	public TodoVO findById(Long id) {
		// TODO Auto-generated method stub
		return todoDao.findById(id);
	}

	@Override
	public int insert(TodoVO vo) {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeForm = new SimpleDateFormat("HH:mm:SS");
		vo.setT_sdate(dateForm.format(date));
		vo.setT_stime(timeForm.format(date));
		return todoDao.insert(vo);
	}

	@Override
	public int update(TodoVO vo) {
		return todoDao.update(vo);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void create_todo_table() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int todoComp(String t_seq) {

		long seq = 0L; 
		try {
			seq = Long.valueOf(t_seq);
		} catch (Exception e) {
			return -1;
		}
		TodoVO todoVO = todoDao.findById(seq);
		if(todoVO == null) {
			return -1;
		}
		
		String edate = todoVO.getT_edate();
		if(edate == null || edate.isEmpty()) {
			// Java 1.8 이상에서 사용하는 새로은 날짜시간 클래스
			LocalDateTime dateTime = LocalDateTime.now();
			DateTimeFormatter dateFormat 
				= DateTimeFormatter.ofPattern("yyyy-MM-dd");
			DateTimeFormatter timeFormat
				= DateTimeFormatter.ofPattern("HH:mm:ss");
			
			todoVO.setT_edate(dateTime.format(dateFormat));
			todoVO.setT_etime(dateTime.format(timeFormat));
		} else {
			todoVO.setT_edate("");
			todoVO.setT_etime("");
		}
		
		/*
		 * VO 의 변수가 boolean type 일 경우
		 * set method 는 일반적인 setter method pattern을 따르는데
		 * get method 는 is변수명() 형태의 pattern으로 변경된다
		 */
		todoVO.setT_complete( !todoVO.isT_complete() );
		int ret = todoDao.update(todoVO);
		
		return ret;
	}

}
