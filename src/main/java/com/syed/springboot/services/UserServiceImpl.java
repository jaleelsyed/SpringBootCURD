package com.syed.springboot.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.syed.springboot.dto.QueryBean;
import com.syed.springboot.dto.bean;
import com.syed.springboot.model.Role;
import com.syed.springboot.model.User;
import com.syed.springboot.repository.RoleRepository;
import com.syed.springboot.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;

	private final NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	UserServiceImpl(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

 
	

	@Override
	public List<bean> postparamsquery(QueryBean bean) {
		
		Map<String, Object> queryParams = new HashMap<>();

		queryParams.put("query", bean.getQuery());
		queryParams.put("param1", bean.getParam1());
		queryParams.put("param2", bean.getParam2());
		String sql=bean.getQuery() +" where "+bean.getParam1() +" and "+bean.getParam2()+";";
		System.out.println("query"+sql);
		List<bean> result = jdbcTemplate.query(sql,
		queryParams, new BeanPropertyRowMapper<>(bean.class));
 
 		return result;
	}




	@Override
	public List<bean> getparamsquery(String query, String param1, String param2) {
		Map<String, Object> queryParams = new HashMap<>();

		queryParams.put("query", query);
		queryParams.put("param1", param1);
		queryParams.put("param2", param2);
		String sql=query +" where "+param1 +" and "+param2+";";
		System.out.println("query"+sql);
		List<bean> result = jdbcTemplate.query(sql,
		queryParams, new BeanPropertyRowMapper<>(bean.class));
		return result;
	}




	@Override
	public void saveClient(User userbean) {
		// TODO Auto-generated method stub
		Role userRole = roleRepository.findByRole("ADMIN");
	//	userbean.set(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(userbean);
	}

	

	

}
