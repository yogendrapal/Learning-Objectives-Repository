package com.LearningObjectiveRepo.UserAccounts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/*
public interface UserRepository extends CrudRepository<User,Long> {

//	public Optional<User> findByUsername(String username);
	public User findOneByUsername(String username);
	}

*/

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

public User findOneByUsername(String username);

public Optional<User> findById(Long id);

}