package com.LearningObjectiveRepo.domain;

import org.springframework.data.repository.CrudRepository;

public interface DomainRepository extends CrudRepository<Domain,Long> {

	public Domain findByDomainId(Long domainId);
	public Domain findByDomainName(String domainName);
}
