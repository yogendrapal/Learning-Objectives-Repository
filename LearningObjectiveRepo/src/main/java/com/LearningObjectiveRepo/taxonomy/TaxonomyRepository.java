package com.LearningObjectiveRepo.taxonomy;

import org.springframework.data.repository.CrudRepository;

public interface TaxonomyRepository extends CrudRepository<Taxonomy,String>{

	public Taxonomy findByTaxoName(String taxo);
	public Taxonomy findByTaxoId(Long taxoId);
}
