package com.LearningObjectiveRepo.UserAccounts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.LearningObjectiveRepo.ExceptionHandling.ResourceNotFoundException;


@RestController
@RequestMapping(value="/api/secured/roles")
public class RoleController {
	
	@Autowired
	private RoleService rService;
	
	@PreAuthorize("hasAnyRole('Admin')")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Message createRole(@RequestBody Role role) {
		
		Message m=rService.createRole(role);
		return m;

	}
	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Role> getRoles() {
		
		List<Role> r=rService.getRoles();
		if(r.isEmpty())
		{
			throw new ResourceNotFoundException("No roles found in the database.");
		}
		return r;

	}
}
