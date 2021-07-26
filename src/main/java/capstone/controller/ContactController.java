/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.ContactDto;
import capstone.entity.Classification;
import capstone.entity.Contact;
import capstone.entity.Customer;
import capstone.entity.Source;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.ClassificationRepository;
import capstone.repository.ContactRepository;
import capstone.repository.CustomerRepository;
import capstone.repository.SourceRepository;
import capstone.service.AbstractService;

/**
 * ContactController
 * Liên hệ Controller
 * @author Tuna
 *
 */
@RestController
@RequestMapping(value = "/api/contact")
public class ContactController extends AbstractDtoEntityController<ContactDto, Contact, ContactRepository, Long>
		implements IReadNameController<Contact, ContactRepository, Long> {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ClassificationRepository classificationRepository;
	
	@Autowired
	private SourceRepository sourceRepository;

	@Override
	protected Contact dtoToEntity(ContactDto dto) throws ResourceNotFoundException {
		Contact contact = Contact.builder()
				.code(dto.getCode())
				.id(dto.getId())
				.lastName(dto.getLastName())
				.vocative(dto.getVocative())
				.position(dto.getPosition())
				.department(dto.getDepartment())
				.customer(AbstractService.findEntityById(customerRepository, dto.getCustomerId(), Customer.class))
				.classifications(AbstractService.findEntitiesByIds(classificationRepository, dto.getClassificationIds(), Classification.class))
				.phone(dto.getPhone())
				.email(dto.getEmail())
				.source(AbstractService.findEntityById(sourceRepository, dto.getSourceId(), Source.class))
				.address(dto.getAddress())
				.build();
		contact.setName(dto.getName());
		return contact;
	}

}
