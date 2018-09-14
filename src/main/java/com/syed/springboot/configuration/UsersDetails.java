package com.syed.springboot.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.syed.springboot.model.User;
import com.syed.springboot.repository.UserRepository;

@Service
public class UsersDetails implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {

		User usersOptional = userRepository.findbyemail(email);
		if (usersOptional.getEmail() == null) {
			throw new UsernameNotFoundException(email);
		}
		List<String> privileges = null;
		privileges = getRole(usersOptional.getActive());

		return new ValidateUser(usersOptional, usersOptional.getPassword(), privileges);
	}

	private static List<String> getRole(int active) {
		switch (active) {
		case 1:
			return SUPER_ADMIN_PRIVILEGES;
		case 2:
			return ADMIN_PRIVILEGES;
		case 3:
			return USER_PRIVILEGES;

		default:
			return NO_PRIVILEGES;
		}
	}

	final static List<String> USER_PRIVILEGES = new ArrayList<>();
	final static List<String> ADMIN_PRIVILEGES = new ArrayList<>();
	final static List<String> SUPER_ADMIN_PRIVILEGES = new ArrayList<>();

	final static List<String> NO_PRIVILEGES = new ArrayList<>();

	static {

            USER_PRIVILEGES.add("ROLE_USER");

            ADMIN_PRIVILEGES.add("ROLE_USER");
            ADMIN_PRIVILEGES.add("ROLE_ADMIN");


            SUPER_ADMIN_PRIVILEGES.add("ROLE_USER");
            SUPER_ADMIN_PRIVILEGES.add("ROLE_ADMIN");
            SUPER_ADMIN_PRIVILEGES.add("ROLE_SUPER_ADMIN");

        
        
	}
}
