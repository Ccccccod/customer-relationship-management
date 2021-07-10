/**
 * 
 */
package capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.dto.request.ContactDto;
import capstone.entity.Classification;
import capstone.entity.Contact;
import capstone.entity.Customer;
import capstone.entity.Source;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.ClassificationRepository;
import capstone.repository.CustomerRepository;
import capstone.repository.SourceRepository;

/**
 * @author Tuna
 *
 */
@Service
public class ContactService extends AbstractService implements IDtoToEntityService<ContactDto, Contact, Long> {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ClassificationRepository classificationRepository;
	
	@Autowired
	private SourceRepository sourceRepository;

	@Override
	public Contact dtoToEntity(ContactDto dto) throws ResourceNotFoundException {
		Contact contact = Contact.builder()
				.id(dto.getId())
				.lastName(dto.getLastName())
				.vocative(dto.getVocative())
				.position(dto.getPosition())
				.department(dto.getDepartment())
				.customer(this.findEntityById(customerRepository, dto.getCustomerId(), Customer.class))
				.classifications(this.findEntitiesByIds(classificationRepository, dto.getClassificationIds(), Classification.class))
				.phone(dto.getPhone())
				.email(dto.getEmail())
				.source(this.findEntityById(sourceRepository, dto.getSourceId(), Source.class))
				.address(dto.getAddress())
				.build();
		contact.setName(dto.getName());
		return contact;
	}

}
