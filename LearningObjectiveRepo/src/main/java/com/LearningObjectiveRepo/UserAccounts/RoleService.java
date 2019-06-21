package com.LearningObjectiveRepo.UserAccounts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

	@Autowired 
	private RoleRepository rRepository;
	
	public Message createRole(Role role) {
		Role r=rRepository.findByRoleName(role.getRoleName());
		Message m=new Message();
		if(r==null)
			{ rRepository.save(role);
			  m.setMessage("Role added successfully");
			  return m;
			}
		else {
			m.setMessage("Role is already present in databse");
			return m;
		}
			
		    
		
	}

	public List<Role> getRoles() {
		return rRepository.findAll();
		
	}

}
