package com.LearningObjectiveRepo.UserAccounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

/*	@Autowired
    private UserRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String username) {
    	 Optional<User> optionalUsers = usersRepository.findByUsername(username);

         optionalUsers
                 .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
         return optionalUsers
                 .map(CustomUserDetails::new).get();
    }
    */
	
	
	@Autowired
	 private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.find(username);
		return  user;

	}
}
