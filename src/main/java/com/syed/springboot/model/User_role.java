package com.syed.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/*@Entity
@Table(name = "user_role")*/
public class User_role {
	
	
	@Column(name="user_id",nullable=false,length=11)
	private int user_id;
	
	
	@Column(name="role_id",nullable=false,length=11)
	private int role_id;

}
