package com.callor.todo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.callor.todo.model.AuthorityVO;
import com.callor.todo.model.UserVO;
import com.callor.todo.persistance.UserDao;
import com.callor.todo.service.UserService;

@Service("userServiceV1")
public class UserServiceImplV1 implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passEncoder;
	
	// 자동실행 하기 (꼼수)
	// BeanService 클래스로 이동
	//	@Bean
	//	public void create_table() {
	//		userDao.create_user_table();
	//		userDao.create_auth_table();
	//	}
	
	@Override
	public void create_user_table() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create_auth_table() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AuthorityVO> select_auths(String username) {
		return null;
	}

	@Override
	public List<UserVO> selectAll() {
		return null;
	}

	@Override
	public UserVO findById(String id) {
		return null;
	}

	/*
	 * 회원가입 하기
	 * 1. 최초로 회원 가입 되는 username  은 ADMIN, USER 권한을 부여한다
	 * 2. 이후에 가입된 회원에게는 USER 권한을 부여한다
	 * 3. 최초로 회원 가입된 user의  enabled 칼럼을 true 로
	 * 4. 이후에 가입된 회원의 enabled 칼럼은 false 로 하여 
	 * 		이후 인증 후 사용가능 하도록 하는 기능을 추가 할수 있도록 한다
	 */
	@Transactional
	@Override
	public int insert(UserVO vo) {
		
		List<UserVO> userList = userDao.selectAll();
		List<AuthorityVO> authList = new ArrayList<>();
		
		// 최초에 가입하는 회원
		if(userList == null || userList.size() < 1) {
			authList.add(AuthorityVO.builder()
							.username(vo.getUsername())
							.authority("ROLE_ADMIN")
							.build()
					);
			authList.add(AuthorityVO.builder()
					.username(vo.getUsername())
					.authority("ROLE_USER")
					.build()
			);
			vo.setEnabled(true);
		} else {
			authList.add(AuthorityVO.builder()
					.username(vo.getUsername())
					.authority("ROLE_USER")
					.build()
			);
			vo.setEnabled(false);
		}
		
		// 회원정보의 비밀번호를 암호화 하기
		/*
		 * vo 에 담긴 평문 비번을 get 하여
		 * passEncoder 의 encode() method 를 사용하여 암호화하고
		 * 다시 vo 의 password 에 setting
		 */
		String encPassword = passEncoder.encode(vo.getPassword());
		vo.setPassword(encPassword);
		
		int ret = userDao.role_insert(authList);
		ret += userDao.insert(vo);
		return ret;
		
	}

	@Override
	public int update(UserVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int role_insert(List<AuthorityVO> auths) {
		// TODO Auto-generated method stub
		return 0;
	}

}
