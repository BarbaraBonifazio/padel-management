package it.solvingteam.padelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.padelmanagement.model.admin.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{

	Admin findAdminByUser_username(String username);

}
