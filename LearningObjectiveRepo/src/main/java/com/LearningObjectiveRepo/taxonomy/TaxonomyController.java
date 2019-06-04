package com.LearningObjectiveRepo.taxonomy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/taxonomies")
public class TaxonomyController {
	
	@Autowired
	private TaxonomyService taxoService;
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public String createTaxo()

}
