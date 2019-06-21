package com.LearningObjectiveRepo.UserAccounts;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users,Long> {

	public Optional<Users> findByUsername(String username);
}
