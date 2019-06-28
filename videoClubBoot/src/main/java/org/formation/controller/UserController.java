package org.formation.controller;

import org.formation.metier.Roles;
import org.formation.metier.User;
import org.formation.metier.UserRole;
import org.formation.repository.UserRepository;
import org.formation.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

		@GetMapping(value= {"","/"})
		public String formUser(Model model){
			
			model.addAttribute("user", new User());
			return "formUser";
		}
		
		@GetMapping("/save")
		public String save(@ModelAttribute("user") User user) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setEnable(true);
			userRepository.save(user);
			UserRole userRole= new UserRole();
			userRole.setUser(user);
			userRole.setRole(Roles.ROLE_USER);
			userRoleRepository.save(userRole);
			return "redirect:/";
			
		}

	
}
