package com.LearningObjectiveRepo.UserAccounts;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {
/*
	 @Autowired
	    private UserRepository userRepository;
	    @Autowired
	    private RoleRepository roleRepository;
	  
	    @Autowired
		private RoleService rService;
	    public Optional<User> findByUsername(String username) {
	        return userRepository.findByUsername(username);
	    }


		public Message userSignup(UserAndRole userRole) {
			
			User user=userRole.getUser();
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
		*/
	
	
	@Autowired
	UserRepository userRepository;

	public User save(User user) {
		return userRepository.saveAndFlush(user);
	}

	public User update(User user) {
		return userRepository.save(user);
	}

	public User find(String userName) {
		return userRepository.findOneByUsername(userName);
	}

	public Optional<User> find(Long id) {
		return userRepository.findById(id);
	}
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public void updateUser(String r, Long userId) {
		Optional<User> user = userRepository.findById(userId);
		user.get().setRole(r);
		userRepository.save(user.get());
	}

}
