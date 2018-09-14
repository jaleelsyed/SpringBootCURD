package com.syed.springboot.services;

import java.util.List;

import com.syed.springboot.dto.QueryBean;
import com.syed.springboot.dto.bean;
import com.syed.springboot.model.User;

public interface UserService {

	List<bean> postparamsquery(QueryBean bean);

	List<bean> getparamsquery(String query, String param1, String param2);

	void saveClient(User userbean);

	User findUserByEmail(String email);

	List<User> findAllUsers();

	void updateUser(User user);

	 
}
