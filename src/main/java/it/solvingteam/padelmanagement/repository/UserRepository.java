package it.solvingteam.padelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.padelmanagement.model.user.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
