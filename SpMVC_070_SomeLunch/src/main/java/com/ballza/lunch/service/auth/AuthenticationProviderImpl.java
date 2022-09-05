package com.ballza.lunch.service.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ballza.lunch.model.AuthorityVO;
import com.ballza.lunch.model.UserVO;
import com.ballza.lunch.persistance.UserDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("authenticationProvider")
public class AuthenticationProviderImpl  implements AuthenticationProvider{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		// 로그인을 수행한 사용자의 ID 와 비번을 추출
		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		
		UserVO userVO = userDao.findById(username);
		
		log.debug("로그인 완료 사용자 {}", userVO);
		
		if(userVO == null) {
			throw new UsernameNotFoundException(username + "이 없음");
		}
		
		// DB 에 저장된 암호화 비번 getter
		String encPassword = userVO.getPassword();
		
		if( !passwordEncoder.matches(password,encPassword)) {
			throw new BadCredentialsException("비밀번호오류");
		}
		
		List<AuthorityVO> authList = userDao.select_auths(username);
		List<GrantedAuthority> grantList = new ArrayList<>();
		
		// tbl_authorities 에서 select 한 문자열로 되어있는 authority 정보를
		// GrantedAuthority 타입으로 변환하기
		// 이 정보는 Spring Security hasRole() method 에서 참조
		
		for(AuthorityVO vo : authList) {
			grantList.add(new SimpleGrantedAuthority(vo.getAuthority()));
		}
		
		/* UserVO 객체와 권한 리스트로 Token 발행하기
		 * 
		 * Token 에 UserVO 가 담기게 되고
		 * Security 에서 제공하는 user 정보 이외의 항목
		 * (UserVO 에 임의로 설정한 항목들 : realname, nickname, email 등등)
		 * 들을 JSP 에서 자유롭게 principal 객체를 통해 접근 할 수 있다
		 */
		return new UsernamePasswordAuthenticationToken(userVO, null, grantList);
		
		/*
		 * 사용자ID(username) 비번(password) 권한 리스트 만으로 Token 발행하기
		 * 
		 * Token 을 발행하는 최소한의 요건만 충족해 Token 을 발행하면
		 * 사용할 수 있는 항목이 제한적이다
		 * JSP 에서 principal.username, principal.password 항목은 참조 할 수 있으나 
		 * principal.email, principle.realname 등의 항목은 참조할 수 없다
		 * return new UsernamePasswordAuthenticationToken(username, password, grantList);
		 */
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
