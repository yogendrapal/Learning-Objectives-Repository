package com.LearningObjectiveRepo.UserAccounts;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    private UserRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String username) {
    	 Optional<Users> optionalUsers = usersRepository.findByUsername(username);

         optionalUsers
                 .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
         return optionalUsers
                 .map(CustomUserDetails::new).get();
    }
}


