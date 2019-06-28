package com.LearningObjectiveRepo.category;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.LearningObjectiveRepo.UserAccounts.Message;

@RestController
@RequestMapping(value = "/api/categories")
public class CategoryController {

	@Autowired
	public CategoryService cService;

	/*
	 * Internal class created to bind learning objective and category's composite
	 * primary key in one object
	 */
	public static class LoCategory {
		private String lo;
		private String domainId;
		private String fieldId;
		private String subjectId;
		private String topicId;

		public String getLo() {
			return lo;
		}

		public void setLo(String lo) {
			this.lo = lo;
		}

		public String getDomainId() {
			return domainId;
		}

		public void setDomainId(String domainId) {
			this.domainId = domainId;
		}

		public String getFieldId() {
			return fieldId;
		}

		public void setFieldId(String fieldId) {
			this.fieldId = fieldId;
		}

		public String getSubjectId() {
			return subjectId;
		}

		public void setSubjectId(String subjectId) {
			this.subjectId = subjectId;
		}

		public String getTopicId() {
			return topicId;
		}

		public void setTopicId(String topicId) {
			this.topicId = topicId;
		}

	}

	/*
	 * To create category
	 */
	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Message createCategory(@RequestBody LoCategory loCategory) {
		Message m = cService.createCategory(loCategory);
		return m;
	}

	/*
	 * To get category
	 */
	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value = "/los/{loId}", method = RequestMethod.GET)
	public Set<Category> readCategoryByLo(@PathVariable("loId") String lId) {
		Long loId = Long.parseLong(lId);
		Set<Category> c = cService.readCategoryByLo(loId);
		return c;
	}

}
