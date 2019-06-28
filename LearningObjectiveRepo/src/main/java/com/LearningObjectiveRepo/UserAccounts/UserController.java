package com.LearningObjectiveRepo.UserAccounts;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(value="/api/users")
public class UserController {

/*	@Autowired
	private UserService uService;
	public static class UserAndRole{
		private User user;
		private List<String> role;
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		public List<String> getRole() {
			return role;
		}
		public void setRole(List<String> role) {
			this.role = role;
=======
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/users")
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	// request method to create a new account by a guest
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User newUser) {
		if (userService.find(newUser.getUsername()) != null) {
			logger.error("username Already exist " + newUser.getUsername());
			return new ResponseEntity(
					new CustomErrorType("user with username " + newUser.getUsername() + "already exist "),
					HttpStatus.CONFLICT);
>>>>>>> 756f28cf90ae719f8557fed4cc62a98a88dcbe0b
		}
		newUser.setRole("Creator");

		return new ResponseEntity<User>(userService.save(newUser), HttpStatus.CREATED);
	}
<<<<<<< HEAD
	
	 @RequestMapping("/user")
	  public Principal user(Principal user) {
		 System.out.println(user);
	    return user;
	 }
	@RequestMapping(value="/signup",method = RequestMethod.POST)
	public Message userSignup(@RequestBody  UserAndRole userRole)
	{
		
		Message m=uService.userSignup(userRole);
		
		return m;
		}


	@GetMapping(produces = "application/json")
	@RequestMapping({ "/validateLogin" })
	public User validateLogin() {
		return new User("User successfully authenticated");
=======

	// this is the login api/service
	@CrossOrigin
	@RequestMapping("/login")
	public Principal user(Principal principal) {
		logger.info("user logged " + principal);
		return principal;
>>>>>>> 756f28cf90ae719f8557fed4cc62a98a88dcbe0b
	}


	*/
	
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	// request method to create a new account by a guest
	@CrossOrigin
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User newUser) {
		if (userService.find(newUser.getUsername()) != null) {
			logger.error("username Already exist " + newUser.getUsername());
			return new ResponseEntity(
					new CustomErrorType("user with username " + newUser.getUsername() + "already exist "),
					HttpStatus.CONFLICT);
		}
		newUser.setRole("Creator");
		
		return new ResponseEntity<User>(userService.save(newUser), HttpStatus.CREATED);
	}

	// this is the login api/service
	@CrossOrigin
	@RequestMapping("/login")
	public Principal user(Principal principal) {
		logger.info("user logged "+principal);
		return principal;
	}
	/*
	@GetMapping("/")
	String uid(HttpSession session) {
		System.out.println(session.getId());
		return session.getId();
	} */
	/*
	 @RequestMapping("/token")
	  public Map<String,String> token(HttpSession session) {
		 System.out.println(session.getId());
	    return Collections.singletonMap("token", session.getId());
	  }

	 @RequestMapping(value="/logout", method=RequestMethod.GET)
	 @ResponseStatus(HttpStatus.NO_CONTENT)
	 public void logout(HttpSession session)
	 {
		 session.invalidate();
		
	 }
	 */

}
