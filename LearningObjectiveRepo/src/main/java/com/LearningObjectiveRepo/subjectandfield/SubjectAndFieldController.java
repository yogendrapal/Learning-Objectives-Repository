package com.LearningObjectiveRepo.subjectandfield;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.LearningObjectiveRepo.ExceptionHandling.ResourceNotFoundException;
import com.LearningObjectiveRepo.UserAccounts.Message;
import com.LearningObjectiveRepo.field.Field;
import com.LearningObjectiveRepo.subject.Subject;

/*
 * Controller to create api's for the mapping between subject and field
 */
@RestController
@RequestMapping(value = "/api")
public class SubjectAndFieldController {

	@Autowired
	SubjectAndFieldService subFieldService;

	/*
	 * Internal class to embed field and subject in one json object
	 */
	public static class FieldAndSub {
		private String field;
		private String sub;

		public FieldAndSub() {

		}

		public String getField() {
			return field;
		}

		public String getSub() {
			return sub;
		}

		public void setSub(String sub) {
			this.sub = sub;
		}

		public void setField(String field) {
			this.field = field;
		}

	}

	/*
	 * Create field and subject and also the mapping between them
	 */
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "/fields/subjects", method = RequestMethod.POST)
	public Message createField_Subject(@RequestBody FieldAndSub fs) {
		String field = fs.getField();
		String sub = fs.getSub();
		subFieldService.createFieldSub(field, sub);
		Message m = new Message("Field and it's corresponding Subject submitted successfully");
		return m;
	}

	/*
	 * Create mapping between given field id and subject id
	 */
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "/fields/{fieldid}/subjects/{subid}", method = RequestMethod.POST)
	public Message createFieldSubById(@PathVariable("fieldid") String fieldId, @PathVariable("subid") String subId) {
		Long fId = Long.parseLong(fieldId);
		Long sId = Long.parseLong(subId);
		Boolean b = subFieldService.createFieldSubById(fId, sId);
		if (b) {
			Message m = new Message("Field and it's corresponding Subject submitted successfully");
			return m;
		}
		throw new ResourceNotFoundException("Ids are not present");
	}

	/*
	 * Get list of fields related to the given subject id
	 */
	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value = "/fields/subjects/{subid}", method = RequestMethod.GET)
	public @ResponseBody List<Field> readFieldBySubjectId(@PathVariable("subid") String subId) {
		Long sId = Long.parseLong(subId);
		List<Field> field = subFieldService.readFieldBySubjectId(sId);
		if (field == null)
			throw new ResourceNotFoundException("Subject id not found - " + subId);
		return field;
	}

	/*
	 * Get list of subjects related to a particular field id
	 */
	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value = "/subjects/fields/{fieldid}", method = RequestMethod.GET)
	public @ResponseBody List<Subject> readSubjectByFieldId(@PathVariable("fieldid") String fieldId) {
		Long fId = Long.parseLong(fieldId);
		List<Subject> sub = subFieldService.readSubjectByFieldId(fId);
		if (sub == null)
			throw new ResourceNotFoundException("Field id not found - " + fieldId);
		return sub;
	}

	/*
	 * Update subjects for a particular field id
	 */
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "/subjects/fields/fieldid", method = RequestMethod.PUT)
	public Message updateFieldBySubject(@RequestBody Subject sub, @PathVariable("fieldid") String fieldId) {
		Long fId = Long.parseLong(fieldId);
		Boolean b = subFieldService.updateFieldBySubject(sub, fId);
		if (b) {
			Message m = new Message("Updated successfully");
			return m;
		}
		throw new ResourceNotFoundException("Updation not possible");
	}

	/*
	 * Update field for a particular subject id
	 */
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "/fields/subjects/{subid}", method = RequestMethod.PUT)
	public Message updateSubjectByField(@RequestBody Field field, @PathVariable("subid") String subId) {
		Long sId = Long.parseLong(subId);
		Boolean b = subFieldService.updateSubjectByField(field, sId);
		if (b) {
			Message m = new Message("Updated successfully ");
			return m;
		}
		throw new ResourceNotFoundException("Updation not possible");
	}

	/*
	 * Delete subjects for a particular field id
	 */
	@PreAuthorize("hasAnyRole('Admin')")
	@RequestMapping(value = "/subjects/fields/{fieldid}", method = RequestMethod.DELETE)
	public Message deleteSubjectByFieldId(@PathVariable("fieldid") String fieldId) {
		Long fId = Long.parseLong(fieldId);
		Boolean b = subFieldService.deleteSubjectByFieldId(fId);
		if (b) {
			Message m = new Message("Deleted successfully");
			return m;
		}
		throw new ResourceNotFoundException("Deletion not possible");
	}


	/*
	 * Delete field for a particular subject id
	 */
	@PreAuthorize("hasAnyRole('Admin')")
	@RequestMapping(value = "/fields/subjects/{subid}", method = RequestMethod.DELETE)
	public Message deleteFieldBySubjectId(@PathVariable("subid") String subId) {
		Long sId = Long.parseLong(subId);
		Boolean b = subFieldService.deleteFieldBySubjectId(sId);
		if (b) {
			Message m = new Message("Deleted successfully");
			return m;
		}
		throw new ResourceNotFoundException("Deletion not possible");
	}
	/*
	 * Delete the mapping between subject id and topic id
	 */
	@PreAuthorize("hasAnyRole('Admin')")
	@RequestMapping(value = "/subjects/{subid}/fields/{fieldid}", method = RequestMethod.DELETE)
	public Message deleteFieldSubjectById(@PathVariable("subid") String subId,
			@PathVariable("fieldid") String fieldId) {
		Long sId = Long.parseLong(subId);
		Long fId = Long.parseLong(fieldId);
		Boolean b = subFieldService.deleteFieldSubjectById(sId, fId);
		if (b) {
			Message m = new Message("Deleted successfully");
			return m;
		}
		throw new ResourceNotFoundException("Deletion not possible");
	}

}
