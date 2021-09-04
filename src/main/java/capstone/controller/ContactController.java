/**
 * 
 */
package capstone.controller;

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
		extends CRUDController<ContactDto, ContactDto, Contact, Contact, ContactRepository, ContactService, Long> {
	
}
