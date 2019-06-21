package com.LearningObjectiveRepo.UserAccounts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/api/users")
public class UserController {

	@Autowired
	private UserService uService;
	
	
	
	public static class UserAndRole{
		private Users user;
		private List<String> role;
		public Users getUser() {
			return user;
		}
		public void setUser(Users user) {
			this.user = user;
		}
		public List<String> getRole() {
			return role;
		}
		public void setRole(List<String> role) {
			this.role = role;
		}
	}
	
	@RequestMapping(value="/signup")
	public Message userSignup(@RequestBody UserAndRole userRole)
	{
		
		Message m=uService.userSignup(userRole);
		return m;
		}
	
}
