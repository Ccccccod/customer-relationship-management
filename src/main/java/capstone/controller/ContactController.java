/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.ContactDto;
import capstone.entity.Contact;
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
	
}
