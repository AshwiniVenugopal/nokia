package com.nokia.ee.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nokia.ee.demo.Model.Users;
import com.nokia.ee.demo.config.MyUserDetails;
import com.nokia.ee.demo.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = repo.findByUsername(username);
		return new MyUserDetails(users);
	}

}
