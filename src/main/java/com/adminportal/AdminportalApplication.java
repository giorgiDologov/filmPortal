package com.adminportal;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.adminportal.domain.User;
import com.adminportal.domain.security.Role;
import com.adminportal.domain.security.UserRole;
import com.adminportal.service.UserService;
import com.adminportal.utility.SecurityUtility;

@SpringBootApplication
public class AdminportalApplication implements CommandLineRunner {
	
	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(AdminportalApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		User user1 = new User();
		user1.setFirstName("Robi");
		user1.setLastName("Raj");
		user1.setUsername("robi.raj");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
		user1.setEmail("rajrobi@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1= new Role();
		role1.setRoleId(0);
		role1.setName("ROLE_ADMIN");
		userRoles.add(new UserRole(user1, role1));
		
		userService.createUser(user1, userRoles);
		
	}
}
