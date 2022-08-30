package com.callor.exp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.callor.exp.dao.UserDao;
import com.callor.exp.model.UserVO;
import com.callor.exp.service.UserService;

/*
 * Annotaion 을 부착하여 Bean 으로 생성 요청
 * @Controller, @Service
 * @Component @Repository @Config  @Dao
 * 
 *  이 Annotation 은 이름만 다를뿐 하는 일이 거의 같다
 *  @Component 는 모든 Annotion 의 Parent 이고
 *  특별하게 붙일 항목이 없으면 사용하는데
 *  일부에서는 @Component 를 사용하면 Bean 을 생성하는데
 *  비용이 많이 소요된다고 한다
 *  
 *  
 *  
 * 
 */
@Service
public class UserServiceimplV1 implements UserService{
	
	private final UserDao userDao;
	public UserServiceimplV1(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<UserVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserVO findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(UserVO userVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(UserVO userVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}


}
