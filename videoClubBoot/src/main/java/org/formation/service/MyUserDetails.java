package org.formation.service;

import java.util.*;

import org.formation.metier.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetails implements UserDetails {

	private User user;

	public MyUserDetails() {

	}

	public MyUserDetails(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> autorities = new ArrayList<GrantedAuthority>();
		for(UserRole userRoles :user.getRoles()) {
			autorities.add(	new SimpleGrantedAuthority(user.getRoles().toString()));
		}
		
		return autorities;
	}

	public String getPassword() {

		return user.getPassword();
	}

	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
