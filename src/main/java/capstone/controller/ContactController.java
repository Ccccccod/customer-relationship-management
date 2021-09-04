/**
 * 
 */
package capstone.controller;

import java.util.List;

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
import capstone.repository.InvoiceRepository;
import capstone.repository.OpportunityRepository;
import capstone.repository.OrderRepository;
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
	
	@Autowired
	private OpportunityRepository opportunityRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Override
	protected Contact dtoToEntity(ContactDto dto, Contact contact) throws ResourceNotFoundException {
		return contact.toBuilder()
				.name(dto.getName())
				.code(dto.getCode())
				.id(dto.getId())
				.lastName(dto.getLastName())
				.vocative(dto.getVocative())
				.position(dto.getPosition())
				.department(dto.getDepartment())
				.customer(AbstractService.findEntityById(customerRepository, dto.getCustomerId(), Customer.class))
				.classifications(AbstractService.findEntitiesByIds(classificationRepository, dto.getClassificationIds(), Classification.class))
				.phone(dto.getPhone())
				.officePhone(dto.getOfficePhone())
				.otherPhone(dto.getOtherPhone())
				.email(dto.getEmail())
				.officeEmail(dto.getOfficeEmail())
				.source(AbstractService.findEntityById(sourceRepository, dto.getSourceId(), Source.class))
				.address(dto.getAddress())
				// Other Information
				.dateOfBirth(dto.getDateOfBirth())
				.gender(dto.getGender())
				.maritalStatus(dto.getMaritalStatus())
				.facebook(dto.getFacebook())
				.build();
	}

	@Override
	protected Class<Contact> entityClass() {
		return Contact.class;
	}

	@Override
	protected void preDelete(List<Contact> entities) {
		entities.forEach(e -> {
			e.getOpportunities().forEach(i -> i.setContact(null));
			opportunityRepository.saveAll(e.getOpportunities());
			e.getOrders().forEach(i -> i.setContact(null));
			orderRepository.saveAll(e.getOrders());
			e.getInvoices().forEach(i -> i.setBuyer(null));
			invoiceRepository.saveAll(e.getInvoices());
		});
	}
	
}
