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
	
	/*
	 * Project 가 시작된후
	 *  'userServiceimplV1': ... circular reference? 문제가 생겼을때
	 *  
	 *  현시점에서 UserDao 가 bean 으로 생성되지 않은상태
	 *  (
	 *  mybatis-context.xml 을 만들지 않았기 때문에서
	 *  어디에서 UserDao 롤 Bean 으로 생성하는 코드가 없기 때문에
	 *  )
	 *  
	 *  이 상황이 되면 Spring UserDao 를 상속받은 UserService 를 찾고
	 *  UserSerivce 를 impl 한 userServiceImplV1 을 Inject 하려고 시도한다
	 *  그런데 문제는
	 *  userSerivcerImplV1 에서 userServiceImplV1 을 Inject(주입)받으려는
	 *  상황이 발생한다
	 *  
	 *  그래서 무한 반복 순환 코드가 실행되다가 메모리 Full 이 발생한다
	 *  stack OvererFlower 메시지가 나타 나기도 한다
	 *  
	 *  
	 *  
	 */
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
		userDao.insert(userVO);
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
