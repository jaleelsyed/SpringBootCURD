package com.syed.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.syed.springboot.model.User;

@Repository("userRegistrationRepository")
public interface UserRepository  extends JpaRepository<User, Integer>{

	 @Query("select user from User as user where  user.email =:email")
	 @Transactional(readOnly = true)
	 User findbyemail(@Param("email")String email);
	
	

}
