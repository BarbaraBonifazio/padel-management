package it.solvingteam.padelmanagement.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import it.solvingteam.padelmanagement.model.user.Role;
import it.solvingteam.padelmanagement.model.user.User;
import it.solvingteam.padelmanagement.service.JwtUserDetailsService;
import it.solvingteam.padelmanagement.util.JwtTokenUtil;

@Component
public class Interceptor implements HandlerInterceptor {
	
	@Autowired
	JwtUserDetailsService userService;
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Override
	public boolean preHandle (HttpServletRequest request, HttpServletResponse response, 
		Object handler) throws Exception {
		String relativePath=request.getRequestURI();
		if (relativePath.startsWith("/auth")) {
			return true;
		}
		String username=jwtTokenUtil.getUsernameFromToken(request.getHeader("Authorization").substring(7));
		User user=userService.findUserByUsername(username).orElse(null);
		Boolean authorized = true;
		if (relativePath.startsWith("/api/superAdmin")) {
			authorized = user.getRole().equals(Role.SUPER_ADMIN) ? authorized : !authorized;
			if(!authorized) {
				response.setStatus(403);
				return false;                                                               
			}
		}
		
		if (relativePath.startsWith("/api/admin") || relativePath.startsWith("/api/user/**")) {
			authorized = user.getRole().equals(Role.ADMIN) ? authorized : !authorized;
			if(!authorized) {
				response.setStatus(403);
				return false;  
			}
		}
		
		if (relativePath.startsWith("/api/player") || relativePath.startsWith("/api/user/**")) {
			authorized = user.getRole() == Role.PLAYER ? authorized : !authorized;
			if(!authorized) {
				response.setStatus(403);
				return false;    
			}
		}

		if (relativePath.startsWith("/api/guest")) {
			authorized = user.getRole() == Role.GUEST ? authorized : !authorized;
			if(!authorized) {
				response.setStatus(403);
				return user.getRole().equals(Role.GUEST);  
			}
		}

		return true;
	}

}
