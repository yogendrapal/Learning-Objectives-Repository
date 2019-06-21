package com.LearningObjectiveRepo.UserAccounts;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

	public List<Role> findAll();
	
	public Role findByRoleName(String roleName);
}
