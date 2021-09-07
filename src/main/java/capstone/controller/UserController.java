/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.UserDto;
import capstone.dto.request.UserUpdateDto;
import capstone.dto.response.UserResponse;
import capstone.entity.Gender;
import capstone.entity.Role;
import capstone.entity.User;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.ContactRepository;
import capstone.repository.CustomerRepository;
import capstone.repository.GenderRepository;
import capstone.repository.InvoiceRepository;
import capstone.repository.OpportunityRepository;
import capstone.repository.OrderRepository;
import capstone.repository.PotentialRepository;
import capstone.repository.ProductInfoRepository;
import capstone.repository.ProductRepository;
import capstone.repository.ProductTypeRepository;
import capstone.repository.RoleRepository;
import capstone.repository.UserRepository;
import capstone.service.AbstractService;

/**
 * User Controller
 * Người dùng Controller
 * @author Tuna
 */
@RestController
@RequestMapping("/api/user")
public class UserController extends AbstractCRUDController<UserDto, UserUpdateDto, UserResponse, User, UserRepository, Long> {
	
	@Autowired
	private PotentialRepository potentialRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private ContactRepository contactRepository;
    
    @Autowired
    private GenderRepository genderRepository;
    
    @Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;
    
    @Autowired
    private ProductInfoRepository productInfoRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected UserResponse entityToResponse(User user) {
		return UserResponse.builder()
				.id(user.getId())
				.username(user.getUsername())
				.email(user.getEmail())
				.roles(user.getRoles())
				.createdAt(user.getCreatedAt())
				.createdBy(user.getCreatedBy())
				.updatedAt(user.getUpdatedAt())
				.updatedBy(user.getUpdatedBy())
				
				.lastName(user.getLastName())
				.name(user.getName())
				.phone(user.getPhone())
				.dateOfBirth(user.getDateOfBirth())
//				.gender(user.getGender())
				.address(user.getAddress())
				.build();
	}

	@Override
	protected User dtoToEntity(UserDto dto, User user) throws ResourceNotFoundException {
		return user.toBuilder()
				.id(dto.getId())
				.username(dto.getUsername())
				.email(dto.getEmail())
				.password(passwordEncoder.encode(dto.getPassword()))
				.roles(AbstractService.findEntitiesByIds(roleRepository, dto.getRoleIds(), Role.class))
				.lastName(dto.getLastName())
				.name(dto.getName())
				.phone(dto.getPhone())
				.dateOfBirth(dto.getDateOfBirth())
				.gender(AbstractService.findEntityById(genderRepository, dto.getGenderId(), Gender.class))
				.address(dto.getAddress())
				.build();
	}

	@Override
	protected User updateEntity(UserUpdateDto updateDto, User entity) throws ResourceNotFoundException {
		return entity.toBuilder() //
				.username(updateDto.getUsername()).email(updateDto.getEmail()) //
				.roles(AbstractService.findEntitiesByIds(roleRepository, updateDto.getRoleIds(), Role.class)) //
				.lastName(updateDto.getLastName()) //
				.name(updateDto.getName()) //
				.phone(updateDto.getPhone()) //
				.dateOfBirth(updateDto.getDateOfBirth()) //
				.gender(AbstractService.findEntityById(genderRepository, updateDto.getGenderId(), Gender.class)) //
				.address(updateDto.getAddress()) //
				.build();
	}

	@Override
	protected Class<User> entityClass() {
		return User.class;
	}
	
//	@Override
//	protected void preDelete(List<User> entities) {
//		entities.forEach(e -> {
//			e.getPotentialsCreated().forEach(i -> i.setCreatedBy(null));
//			e.getPotentialsUpdated().forEach(i -> i.setUpdatedBy(null));
//			potentialRepository.saveAll(e.getPotentialsCreated());
//			potentialRepository.saveAll(e.getPotentialsUpdated());
//			e.getCustomersCreated().forEach(i -> i.setCreatedBy(null));
//			e.getCustomersUpdated().forEach(i -> i.setUpdatedBy(null));
//			customerRepository.saveAll(e.getCustomersCreated());
//			customerRepository.saveAll(e.getCustomersUpdated());
//			e.getContactsCreated().forEach(i -> i.setCreatedBy(null));
//			e.getContactsUpdated().forEach(i -> i.setUpdatedBy(null));
//			contactRepository.saveAll(e.getContactsCreated());
//			contactRepository.saveAll(e.getContactsUpdated());
//			e.getProductTypesCreated().forEach(i -> i.setCreatedBy(null));
//			e.getProductTypesUpdated().forEach(i -> i.setUpdatedBy(null));
//			productTypeRepository.saveAll(e.getProductTypesCreated());
//			productTypeRepository.saveAll(e.getProductTypesUpdated());
//			e.getProductsCreated().forEach(i -> i.setCreatedBy(null));
//			e.getProductsUpdated().forEach(i -> i.setUpdatedBy(null));
//			productRepository.saveAll(e.getProductsCreated());
//			productRepository.saveAll(e.getProductsUpdated());
//			e.getProductInfosCreated().forEach(i -> i.setCreatedBy(null));
//			e.getProductInfosUpdated().forEach(i -> i.setUpdatedBy(null));
//			productInfoRepository.saveAll(e.getProductInfosCreated());
//			productInfoRepository.saveAll(e.getProductInfosUpdated());
//			e.getOpportunitiesCreated().forEach(i -> i.setCreatedBy(null));
//			e.getOpportunitysUpdated().forEach(i -> i.setUpdatedBy(null));
//			opportunityRepository.saveAll(e.getOpportunitiesCreated());
//			opportunityRepository.saveAll(e.getOpportunitysUpdated());
//			e.getOrdersCreated().forEach(i -> i.setCreatedBy(null));
//			e.getOrdersUpdated().forEach(i -> i.setUpdatedBy(null));
//			orderRepository.saveAll(e.getOrdersCreated());
//			orderRepository.saveAll(e.getOrdersUpdated());
//			e.getInvoicesCreated().forEach(i -> i.setCreatedBy(null));
//			e.getInvoicesUpdated().forEach(i -> i.setUpdatedBy(null));
//			invoiceRepository.saveAll(e.getInvoicesCreated());
//			invoiceRepository.saveAll(e.getInvoicesUpdated());
//			e.getUsersCreated().forEach(i -> i.setCreatedBy(null));
//			e.getUsersUpdated().forEach(i -> i.setUpdatedBy(null));
//			userRepository.saveAll(e.getUsersCreated());
//			userRepository.saveAll(e.getUsersUpdated());
//			e.getRolesCreated().forEach(i -> i.setCreatedBy(null));
//			e.getRolesUpdated().forEach(i -> i.setUpdatedBy(null));
//			roleRepository.saveAll(e.getRolesCreated());
//			roleRepository.saveAll(e.getRolesUpdated());
//		});
//	}

}
