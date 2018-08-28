package com.syed.springboot.model;

import java.beans.Transient;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@Getter
//@Setter

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id", nullable = false, length = 11)
	private int id;

	@Column(name = "username", nullable = false, length = 255)
	private String userName;

	@Column(name = "status", nullable = false, length = 255)
	private String Status;

	@Column(name = "password", nullable = false, length = 255)
	@javax.persistence.Transient
	private String Password;

	@Column(name = "role", nullable = false, length = 255)
	private String Role;

	@Column(name = "email", nullable = true, length = 255)
	private String email;

	@Column(name = "mobile", nullable = true, length = 255)
	private String Mobile;

	@Column(name = "address", nullable = true, length = 255)
	private String Address;

	@Column(name = "dob", nullable = true, length = 255)
	private String Dob;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@Column(name = "registration_date", nullable = true, length = 255)
	private String registrationDate;

}
