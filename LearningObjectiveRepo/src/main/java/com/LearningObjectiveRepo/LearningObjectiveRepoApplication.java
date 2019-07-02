package com.LearningObjectiveRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.LearningObjectiveRepo.UserAccounts.User;
import com.LearningObjectiveRepo.UserAccounts.UserRepository;
import com.LearningObjectiveRepo.UserAccounts.UserService;

/*
 * Main function, starting point of the application
 */
@SpringBootApplication
public class LearningObjectiveRepoApplication implements CommandLineRunner {
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(LearningObjectiveRepoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		createAdmin();

	}

	private void createAdmin() {
		User u = userRepository.findOneByUsername("admin");
		if (u == null) {
			User admin = new User("admin", "VTG5DFZWI28J", "yogendra pal");
			admin.setRole("Admin");
			userService.save(admin);
		}
	}

}
