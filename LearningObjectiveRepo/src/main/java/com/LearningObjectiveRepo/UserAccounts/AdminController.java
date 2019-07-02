package com.LearningObjectiveRepo.UserAccounts;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@PreAuthorize("hasAnyRole('Admin')")
@RequestMapping(value = "/api/admin")
public class AdminController {

	@Autowired
	private UserService uService;

	/*
	 * Admin can get the details of all users from the database
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		List<User> users = uService.getAllUsers();
		return users;
	}
	@RequestMapping(value = "/users/{uId}", method = RequestMethod.GET)
	public Optional<User> getUserById(@PathVariable("uId") String id) {
		Long userId = Long.parseLong(id);
		Optional<User> user = uService.getUserById(userId);
		return user;
	}

	public static class Role {
		private String role;

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public Role(String role) {
			super();
			this.role = role;
		}

		public Role() {
			super();
		}

	}

	/*
	 * Admin can update the role of any user (by default the role will be creator)
	 */
	@RequestMapping(value = "/users/{uId}", method = RequestMethod.PUT)
	public Message updateUser(@PathVariable("uId") String id, @RequestBody Role role) {

		String r = role.getRole();
		Long userId = Long.parseLong(id);
		uService.updateUser(r, userId);
		Message m = new Message("User role updated successfully");
		return m;

	}
}
