/**
 * 
 */
package capstone.controller;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.CustomerDto;
import capstone.entity.Career;
import capstone.entity.Classification;
import capstone.entity.Customer;
import capstone.entity.Field;
import capstone.entity.Source;
import capstone.entity.Type;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.CareerRepository;
import capstone.repository.ClassificationRepository;
import capstone.repository.ContactRepository;
import capstone.repository.CustomerRepository;
import capstone.repository.FieldRepository;
import capstone.repository.InvoiceRepository;
import capstone.repository.OpportunityRepository;
import capstone.repository.OrderRepository;
import capstone.repository.PotentialRepository;
import capstone.repository.SourceRepository;
import capstone.repository.TypeRepository;
import capstone.service.AbstractService;

/**
 * CustomerController
 * @author Tuna
 *
 */
@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController extends AbstractDtoEntityController<CustomerDto, Customer, CustomerRepository, Long> implements IReadNameController<Customer, CustomerRepository, Long> {
	
	@Autowired
	public CustomerRepository customerRepository;

	@Autowired
	private SourceRepository sourceRepository;
	
	@Autowired
	private ClassificationRepository classificationRepository;
	
	@Autowired 
	private FieldRepository fieldRepository;
	
	@Autowired
	private TypeRepository typeRepository;
	
	@Autowired
	private CareerRepository careerRepository;
	
	@Autowired
	private PotentialRepository potentialRepository;
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private OpportunityRepository opportunityRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private InvoiceRepository invoiceRepository;

	@Override
	protected Customer dtoToEntity(CustomerDto dto, Customer customer) throws ResourceNotFoundException {
		Set<Field> fields = Optional
				.ofNullable(AbstractService.findEntitiesByIds(fieldRepository, dto.getFieldIds(), Field.class))
				.orElse(new LinkedHashSet<Field>());
		Set<Career> careers = AbstractService.findEntitiesByIds(careerRepository, dto.getCareerIds(), Career.class)
				/*.stream().filter(career -> fields.contains(career.getField())).collect(Collectors.toSet())*/;
		return customer.toBuilder()
				.id(dto.getId())
				.code(dto.getCode())
				.name(dto.getName())
				.shortName(dto.getShortName())
				.taxCode(dto.getTaxCode())
				.phone(dto.getPhone())
				.email(dto.getEmail())
				.address(dto.getAddress())
				.source(AbstractService.findEntityById(sourceRepository, dto.getSourceId(), Source.class))
				.classifications(AbstractService.findEntitiesByIds(classificationRepository, dto.getClassificationIds(), Classification.class))
				.fields(fields)
				.type(AbstractService.findEntityById(typeRepository, dto.getTypeId(), Type.class))
				.careers(careers)
				.build();
	}

	@Override
	protected Class<Customer> entityClass() {
		return Customer.class;
	}
	
	@Override
	protected void preDelete(List<Customer> entities) {
		entities.forEach(e -> {
			e.getPotentials().forEach(i -> i.setCustomer(null));
			potentialRepository.saveAll(e.getPotentials());
			e.getContacts().forEach(i -> i.setCustomer(null));
			contactRepository.saveAll(e.getContacts());
			e.getOpportunities().forEach(i -> i.setCustomer(null));
			opportunityRepository.saveAll(e.getOpportunities());
			e.getOrders().forEach(i -> i.setCustomer(null));
			orderRepository.saveAll(e.getOrders());
			e.getInvoices().forEach(i -> i.setCustomer(null));
			invoiceRepository.saveAll(e.getInvoices());
		});
	}

}
