package com.callor.food.service;

import com.callor.food.model.UserVO;
import com.callor.food.persistance.UserDao;

public interface UserService extends UserDao{

	public UserVO login(UserVO userVO);
	public int join(UserVO userVO);
	
}
