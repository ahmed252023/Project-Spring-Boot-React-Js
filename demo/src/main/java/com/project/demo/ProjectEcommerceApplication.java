package com.project.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.Repository.UserRepo;
import com.project.demo.entity.Role;
import com.project.demo.entity.User;
@RestController
@SpringBootApplication
public class ProjectEcommerceApplication implements CommandLineRunner  {
//	@RequestMapping("/")
//@ResponseBody
//String home() {
//		return "hello word";
//	}
	
	@Autowired
	private UserRepo userRepository;
	public static void main(String[] args) {
		SpringApplication.run(ProjectEcommerceApplication.class, args);
	}
	
	public void run(String... args) {
		User adminAcount = userRepository.findByRole(Role.ADMIN);
		if(null == adminAcount) {
			User user = new User();
			
			user.setEmail("admin@gmail.com");
			user.setFirstname("admin");
			user.setSecondname("admin");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
			
			
		}
	}

}
