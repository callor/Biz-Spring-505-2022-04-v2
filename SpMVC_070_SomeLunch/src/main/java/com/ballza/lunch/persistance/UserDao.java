package com.ballza.lunch.persistance;

import java.util.List;

import com.ballza.lunch.model.UserVO;
import com.ballza.lunch.model.AuthorityVO;

public interface UserDao extends GenericDao<UserVO, String> {

	public void create_user_table();
	public void create_auth_table();
	public List<AuthorityVO> select_auths(String username);
	public int role_insert(List<AuthorityVO> auths);
	
}
