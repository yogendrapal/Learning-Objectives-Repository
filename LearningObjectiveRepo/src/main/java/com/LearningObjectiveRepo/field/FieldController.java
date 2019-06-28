package com.LearningObjectiveRepo.field;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.LearningObjectiveRepo.ExceptionHandling.ResourceNotFoundException;
import com.LearningObjectiveRepo.UserAccounts.Message;
import com.LearningObjectiveRepo.domain.Domain;

@RestController
@RequestMapping(value="/api/fields")
public class FieldController {

	@Autowired
	private FieldService fService;

	/*
	 * Create field individually
	 */
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Message createField(@RequestBody Field field) {
		Boolean b = fService.createField(field);
		Message m = new Message();

		if (b)
			m.setMessage("Field submitted successfully");

		else
			m.setMessage("Field Name already exists.");
		return m;
	}

	/*
	 * Read field for the given field id
	 */
	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value = "/{fieldId}", method = RequestMethod.GET)
	public Field readFieldByFieldId(@PathVariable("fieldId") String fId) {
		Long fieldId = Long.parseLong(fId);
		Field f = fService.readFieldByFieldId(fieldId);
		if (f == null)
			throw new ResourceNotFoundException("Field id not found - " + fieldId);
		return f;
	}

	/*
	 * Update field for the given field id
	 */
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "/{fieldId}", method = RequestMethod.PUT)
	public Message updateFieldByFieldId(@RequestBody Field field, @PathVariable("fieldId") String fId) {
		Long fieldId = Long.parseLong(fId);
		Boolean b = fService.updateFieldByFieldId(field, fieldId);
		if (b) {
			Message m = new Message();
			m.setMessage("Field updated successfully");
			return m;
		}
		throw new ResourceNotFoundException("Field having name -  " + field.getFieldName() + " already present.");
	}

	/*
	 * Delete field for the given field id
	 */
	@PreAuthorize("hasAnyRole('Admin')")
	@RequestMapping(value = "/{fieldId}", method = RequestMethod.DELETE)
	public Message deleteFieldByFieldId(@PathVariable("fieldId") String fId) {
		Long fieldId = Long.parseLong(fId);
		Boolean b = fService.deleteFieldByFieldId(fieldId);
		if (b) {
			Message m = new Message();
			m.setMessage("Field deleted successfully");
			return m;
		}
		throw new ResourceNotFoundException("Field Id is not valid");
	}

	/*
	 * Create field corresponding to the given domain id
	 */
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "/domains/{domainId}", method = RequestMethod.POST)
	public Message createFieldByDomain(@PathVariable("domainId") String dId, @RequestBody Field field) {
		Long domainId = Long.parseLong(dId);
		Domain d = fService.createFieldByDomain(domainId, field);
		if (d != null) {
			Message m = new Message();
			m.setMessage("Field submitted successfully");
			return m;
		} else
			throw new ResourceNotFoundException("Domain id is not present");
	}

	/*
	 * Read list of fields related to the given domain id
	 */
	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value = "domains/{domainId}", method = RequestMethod.GET)
	public List<Field> getFieldByDomainId(@PathVariable("domainId") String dId) {
		Long domainId = Long.parseLong(dId);
		List<Field> f = fService.getFieldByDomainId(domainId);
		if (f == null) {
			throw new ResourceNotFoundException("Domain id not found - " + domainId);
		} else if (f.isEmpty())
			throw new ResourceNotFoundException("List is Empty");
		return f;
	}

	/*
	 * Update field for the given domain id
	 */
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "/domains/{domainId}", method = RequestMethod.PUT)
	public Message updateFieldByDomainId(@RequestBody Field field, @PathVariable("domainId") String did) {

		Long domainId = Long.parseLong(did);

		Boolean b = fService.updateFieldByDomainId(domainId, field);
		if (b) {
			Message m = new Message();
			m.setMessage("Field for the given domain updated successfully");
			return m;
		} else
			throw new ResourceNotFoundException("Domain id not found - " + domainId);
	}

	/*
	 * Create field corresponding to the given domain id
	 */
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "/{fieldId}/domains/{domainId}", method = RequestMethod.PUT)
	public Message updateFieldByDomainId(@PathVariable("fieldId") String fId, @PathVariable("domainId") String dId) {
		Long fieldId = Long.parseLong(fId);
		Long domainId = Long.parseLong(dId);
		Boolean b = fService.updateFieldByDomainId(fieldId, domainId);
		if (b) {
			Message m = new Message();
			m.setMessage("Field for the given domain updated successfully");
			return m;
		}
		throw new ResourceNotFoundException("Udpdation not possible");
	}

	/*
	 * Delete field corresponding to the given domain id
	 */
	@PreAuthorize("hasAnyRole('Admin')")
	@RequestMapping(value = "domains/{domainId}", method = RequestMethod.DELETE)
	public Message deleteFieldByDomainId(@PathVariable("domainId") String dId) {
		Long domainId = Long.parseLong(dId);
		Boolean b = fService.deleteFieldByDomainId(domainId);
		if (b) {
			Message m = new Message();
			m.setMessage("Field for the given domain deleted successfully");
			return m;
		}
		throw new ResourceNotFoundException("Domain Id is not valid");
	}

}
