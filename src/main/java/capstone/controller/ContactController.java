/**
 * 
 */
package capstone.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.ContactDto;
import capstone.entity.Contact;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.ContactRepository;
import capstone.service.ContactService;

/**
 * ContactController
 * Liên hệ Controller
 * @author Tuna
 */
@RestController
@RequestMapping(value = "/api/contact")
public class ContactController
		extends CRUDController<ContactDto, ContactDto, Contact, Contact, ContactRepository, ContactService, Long> 
		implements IReadNameController<Contact, ContactService, Long> {
	
	@Autowired
	private ContactService contactService;

	@Override
	public ContactService getService() {
		return contactService;
	}

	@GetMapping("/search")
	public ResponseEntity<List<Contact>> search(@RequestParam(name = "customerId", required = false) Long customerId)
			throws ResourceNotFoundException {
		if (customerId == null)
			return ResponseEntity.ok(Collections.emptyList());
		List<Contact> contacts = service.findByCustomerId(customerId);
		return ResponseEntity.ok(contacts);
	}
	
}
