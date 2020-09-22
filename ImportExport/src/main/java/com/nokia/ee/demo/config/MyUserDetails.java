package com.nokia.ee.demo.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nokia.ee.demo.Model.Users;

public class MyUserDetails implements UserDetails {
	/**
	 *  Default Serial VersionID
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private List<GrantedAuthority> authorities;

	public MyUserDetails(Users users) {
		this.username = users.getUsername();
		this.password = users.getPassword();
		this.authorities = Arrays.stream(users.getRoles().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
