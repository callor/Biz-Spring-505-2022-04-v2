package com.ballza.lunch.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ballza.lunch.model.AuthorityVO;
import com.ballza.lunch.model.UserVO;
import com.ballza.lunch.persistance.UserDao;
import com.ballza.lunch.service.UserService;

@Service
public class UserServiceImplV1 implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 자동실행꼼수
	// BeanService 클래스로 이동
//	@Bean
//	public void create_table() {
//		userDao.create_auth_table();
//		userDao.create_user_table();
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int role_insert(List<AuthorityVO> auths) {
		// TODO Auto-generated method stub
		return 0;
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
	
	/*
	 * 회원가입
	 * 1. 최초로 회원 가입 하는 username 은 ADMIN, USER 권한을 부여
	 * 2. 이후 가입 회원은 USER 권한 부여
	 * 3. 최초로 회원 가입된 user 의 enabled 칼럼을 true 로
	 * 4. 이후에 가입된 회원의 enabled 칼럼은 false 로 하여
	 *	 인증 후 사용가능 하도록 하는 기능 추가
	 */
	// @Transactional 사용시 bean 오류나서 bean 옮긴것
	@Transactional
	@Override
	public int insert(UserVO vo) {
		
		
		List<UserVO> userList = userDao.selectAll();
		List<AuthorityVO> authList = new ArrayList<>();
		
		// 최초 가입 회원 조건 설정
		if(userList == null || userList.size() < 1) {
			authList.add(AuthorityVO.builder()
										.username(vo.getUsername())
										.authority("ROLE_ADMIN")
										.build());
			authList.add(AuthorityVO.builder()
										.username(vo.getUsername())
										.authority("ROLE_USER")
										.build());
			/*
			 * 최초 회원 가입시 Enabled 를 true
			 * ADMIN, USER 권한을 부여
			 */
			vo.setEnabled(true);
		// 최초 가입 아닌경우
		} else {
			authList.add(AuthorityVO.builder()
					.username(vo.getUsername())
					.authority("ROLE_USER")
					.build());
			
			vo.setEnabled(false);
		}
		// 회원정보 비밀번호 암호화 하기
		String encPassword = passwordEncoder.encode(vo.getPassword());
		// 평문의 비밀번호를 뽑아서 encode에 보내 암호화 시켜 변수에 담고 다시한번 원래의 비밀번호와 교체
		/*
		 * vo 에 담긴 평문 password 를 get 하여
		 * passEncoder 의 encode() method를 사용하여 암호화하고
		 * 다시 vo 의 password 에 setting
		 */
		
		vo.setPassword(encPassword);
		
		int ret = userDao.role_insert(authList);
 		ret += userDao.insert(vo);
 		// 트랜젝션으로 묶어야
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

}
