package org.formation.repository;

import java.util.Optional;

import org.formation.metier.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String> {
	@Query("select distinct u from User u left join fetch u.roles where u.username=:username")
	Optional<User> findByIdWithRoles(@Param("username")String username);

}
