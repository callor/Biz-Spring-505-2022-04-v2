package com.callor.exp.dao;

import java.util.List;

import com.callor.exp.model.UserVO;

public interface UserDao {
	
	public List<UserVO> selectAll();
	public UserVO findById(String id);
	
	public int insert(UserVO userVO);
	public int update(UserVO userVO);
	public int delete(String id);

}
