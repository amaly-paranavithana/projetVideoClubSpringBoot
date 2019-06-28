package org.formation.repository;

import org.formation.metier.UserRole;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.jpa.repository.JpaRepository;

@Controller
@RequestMapping("/user")
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {


}
