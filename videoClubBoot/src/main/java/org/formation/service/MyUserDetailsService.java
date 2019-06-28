package org.formation.service;

import java.util.Optional;

import org.formation.metier.*;
import org.formation.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> opt = userRepository.findByIdWithRoles(username);
		if(opt.isPresent()) {
			return new MyUserDetails(opt.get());
		}
		throw new UsernameNotFoundException("utilisateur inconnu");
	}

}
