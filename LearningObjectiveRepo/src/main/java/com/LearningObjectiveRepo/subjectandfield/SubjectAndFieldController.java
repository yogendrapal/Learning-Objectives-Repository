package com.LearningObjectiveRepo.subjectandfield;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.LearningObjectiveRepo.ExceptionHandling.ResourceNotFoundException;
import com.LearningObjectiveRepo.field.Field;
import com.LearningObjectiveRepo.subject.Subject;


@RestController
@RequestMapping(value = "/api")
public class SubjectAndFieldController {
@Autowired
SubjectAndFieldService subFieldService;
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
@RequestMapping(value = "/fields/subjects", method = RequestMethod.POST)
public String createField_Subject(@RequestBody FieldAndSub fs) {
	String field = fs.getField();
	String sub = fs.getSub();
	subFieldService.createFieldSub(field,sub);
	return "Field and it's corresponding Subject submitted successfully";
}
@RequestMapping(value = "/fields/{fieldid}/subjects/{subid}", method = RequestMethod.POST)
public String createFieldSubById(@PathVariable("fieldid") String fieldId,@PathVariable("subid") String subId) {
	Long fId = Long.parseLong(fieldId);
	Long sId = Long.parseLong(subId);
	Boolean b = subFieldService.createFieldSubById(fId,sId);
	if(b)
	return "Field and it's corresponding Subject submitted successfully";
	throw new ResourceNotFoundException("Ids are not present");
}
@RequestMapping(value = "/fields/subjects/{subid}", method = RequestMethod.GET)
public @ResponseBody List<Field> readFieldBySubjectId(@PathVariable("subid") String subId) {
	Long sId = Long.parseLong(subId);
	List<Field> field = subFieldService.readFieldBySubjecteId(sId);
	if (field == null)
		throw new ResourceNotFoundException("Subject id not found - " + subId);
	return field;
}
@RequestMapping(value = "/subjects/fields/{fieldid}", method = RequestMethod.GET)
public @ResponseBody List<Subject> readSubjectByFieldId(@PathVariable("fieldid") String fieldId) {
	Long fId = Long.parseLong(fieldId);
	List<Subject> sub = subFieldService.readSubjectByFieldId(fId);
	if (sub == null)
		throw new ResourceNotFoundException("Field id not found - " + fieldId);
	return sub;
}
@RequestMapping(value = "/fields/{fieldid}/subjects", method = RequestMethod.PUT)
public String updateFieldBySubject(@RequestBody Subject sub,@PathVariable("fieldid") String fieldId) {
	Long fId = Long.parseLong(fieldId);
	Boolean b = subFieldService.updateFieldBySubject(sub,fId);
	if(b)
	return "Updated successfully";
	throw new ResourceNotFoundException("Updation not possible");
}  
@RequestMapping(value = "/subjects/{subid}/fields", method = RequestMethod.PUT)
public String updateSubjectByField(@RequestBody Field field,@PathVariable("subid") String subId) {
	Long sId = Long.parseLong(subId);
	Boolean b = subFieldService.updateSubjectByField(field,sId);
	if(b)
	return "Updated successfully";
	throw new ResourceNotFoundException("Updation not possible");
}
@RequestMapping(value = "/fields/{fieldid}/subjects", method = RequestMethod.DELETE)
public String deleteSubjectByFieldId(@PathVariable("fieldid") String fieldId) {
	Long fId = Long.parseLong(fieldId);
	Boolean b = subFieldService.deleteSubjectByFieldId(fId);
	if(b)
	return "Deleted successfully";
	throw new ResourceNotFoundException("Deletion not possible");
}
@RequestMapping(value = "/subjects/{subid}/fields", method = RequestMethod.DELETE)
public String deleteFieldBySubjectId(@PathVariable("subid") String subId) {
	Long sId = Long.parseLong(subId);
	Boolean b = subFieldService.deleteFieldBySubjectId(sId);
	if(b)
	return "Deleted successfully";
	throw new ResourceNotFoundException("Deletion not possible");
}
@RequestMapping(value = "/subjects/{subid}/fields/{fieldid}", method = RequestMethod.DELETE)
public String deleteFieldSubjectById(@PathVariable("subid") String subId,@PathVariable("fieldid") String fieldId) {
	Long sId = Long.parseLong(subId);
	Long fId = Long.parseLong(fieldId);
	Boolean b = subFieldService.deleteFieldSubjectById(sId,fId);
	if(b)
	return "Deleted successfully";
	throw new ResourceNotFoundException("Deletion not possible");
}


}
