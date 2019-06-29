package com.LearningObjectiveRepo.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.LearningObjectiveRepo.UserAccounts.Message;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(value="/api/types")
public class TypeController {
	
	@Autowired
	private TypeService tService;
	
	public static class LoType{
		private String lo;
		private String taxoId;
		private String levelId;
		private String verbId;
		public String getLo() {
			return lo;
		}
		public void setLo(String lo) {
			this.lo = lo;
		}
		public String getTaxoId() {
			return taxoId;
		}
		public void setTaxoId(String taxoId) {
			this.taxoId = taxoId;
		}
		public String getLevelId() {
			return levelId;
		}
		public void setLevelId(String levelId) {
			this.levelId = levelId;
		}
		public String getVerbId() {
			return verbId;
		}
		public void setVerbId(String verbId) {
			this.verbId = verbId;
		}
		
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value="",method=RequestMethod.POST)
	public Message createType(@RequestBody LoType loType)
	{
		Message m=tService.createType(loType);
		return m;
	}

}
