package it.solvingteam.padelmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.padelmanagement.model.user.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String username);

	User findByUsernameAndPassword(String username, String password);

}
