package com.syed.springboot.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.syed.springboot.model.User;

public class ValidateUser implements UserDetails {

	private static final long serialVersionUID = 1L;

	private final User user;
	private final String usersPassword;
	private final List<String> privileges;

	public ValidateUser(User user, String usersPassword, List<String> privileges) {
		super();
		this.user = user;
		this.usersPassword = usersPassword;
		this.privileges = privileges;
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public String getPassword() {
		return usersPassword;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		privileges.forEach(p -> {
            authorities.add(new SimpleGrantedAuthority(p));
        });
		return authorities;
	}

	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
