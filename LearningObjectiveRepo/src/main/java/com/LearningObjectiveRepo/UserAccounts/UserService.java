package com.LearningObjectiveRepo.UserAccounts;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.LearningObjectiveRepo.UserAccounts.UserController.UserAndRole;



@Service
public class UserService {

	 @Autowired
	    private UserRepository userRepository;
	    @Autowired
	    private RoleRepository roleRepository;
	  
	    public Optional<Users> findByUsername(String username) {
	        return userRepository.findByUsername(username);
	    }


		public Message userSignup(UserAndRole userRole) {
			
			Users user=userRole.getUser();
			Message msg=new Message();
			if (user.getUserName().length() < 6 || user.getUserName().length() > 32) {
	           msg.setMessage("User name length should be in between 6 to 32 ");
	           return msg;
	        }
	      if (userRepository.findByUsername(user.getUserName()).isPresent()) {
	        	msg.setMessage("username is not unique");
	            return msg;
	        }

	        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
	        	msg.setMessage("password size should be in between 8 and 32");
	            return msg;
	        }
			List<String> role=userRole.getRole();
			for (String r : role)
			{
				Role ro=roleRepository.findByRoleName(r);
				user.getRoles().add(ro);
			}
			userRepository.save(user);
			msg.setMessage("User registered successfully");
			return msg;
		}


		

		
	}

