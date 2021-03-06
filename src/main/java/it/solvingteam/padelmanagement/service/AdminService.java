package it.solvingteam.padelmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.model.admin.Admin;
import it.solvingteam.padelmanagement.model.user.Role;
import it.solvingteam.padelmanagement.model.user.User;
import it.solvingteam.padelmanagement.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private UserService userService;
	
	public Admin insert(User user) {
		user.setRole(Role.ADMIN);
		user = userService.updateUserRole(user);
		Admin admin = new Admin();
		admin.setUser(user);
		return this.adminRepository.save(admin);
	}

	public Admin findById(Long id) {
		return this.adminRepository.findById(id).get();
	}

	public Admin findByUsername(String username) {
		return adminRepository.findAdminByUser_username(username);
	}
	
}
