/**
 * 
 */
package capstone.config;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import capstone.entity.Career;
import capstone.entity.Classification;
import capstone.entity.Contact;
import capstone.entity.Customer;
import capstone.entity.Field;
import capstone.entity.NamedEntity;
import capstone.entity.Product;
import capstone.entity.Role;
import capstone.entity.Source;
import capstone.entity.Type;
import capstone.entity.User;
import capstone.repository.CareerRepository;
import capstone.repository.ClassificationRepository;
import capstone.repository.ContactRepository;
import capstone.repository.CustomerRepository;
import capstone.repository.FieldRepository;
import capstone.repository.NamedJpaRepository;
import capstone.repository.ProductRepository;
import capstone.repository.RoleRepository;
import capstone.repository.SourceRepository;
import capstone.repository.TypeRepository;
import capstone.repository.UserRepository;
import capstone.utils.EncryptedPasswordUtils;

/**
 * Data Seeding Listener
 * @author Tuna
 *
 */
@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
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
    private ProductRepository productRepository;

    @Autowired
    private ContactRepository contactRepository;
    
    static final private String PASSWORD = "12345678"; 

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
//		sourceRepository.deleteAll();
//		classificationRepository.deleteAll();
//		fieldRepository.deleteAll();
//		typeRepository.deleteAll();
//		careerRepository.deleteAll();
//		productRepository.deleteAll();
		
		// Roles
		Role roleAdmin = roleRepository.findByName(Role.ADMIN)
				.orElseGet(() -> roleRepository.save(new Role(Role.ADMIN)));
        Role roleModerator = roleRepository.findByName(Role.MODERATOR)
        		.orElseGet(() -> roleRepository.save(new Role(Role.MODERATOR)));
        Role roleMember = roleRepository.findByName(Role.MEMBER)
        		.orElseGet(() -> roleRepository.save(new Role(Role.MEMBER)));

        // Admin account
        if (!userRepository.findByEmail("admin1@gmail.com").isPresent()) {
            User admin = new User();
            admin.setName("admin1");
            admin.setEmail("admin1@gmail.com");
            admin.setPassword(EncryptedPasswordUtils.encrytePassword(PASSWORD));
            
            Set<Role> roles = new HashSet<Role>();
            roles.add(roleModerator);
            roles.add(roleAdmin);
            roles.add(roleMember);
            admin.setRoles(roles);
            
            userRepository.save(admin);
        }

        // Member account
        if (!userRepository.findByEmail("member1@gmail.com").isPresent()) {
            User member = new User();
            member.setName("member1");
            member.setEmail("member1@gmail.com");
            member.setPassword(EncryptedPasswordUtils.encrytePassword(PASSWORD));

            Set<Role> roles = new HashSet<Role>();
            roles.add(roleMember);
            member.setRoles(roles);
            
            userRepository.save(member);
        }
        
        userRepository.findAll().forEach(System.out::println);
        roleRepository.findAll().forEach(System.out::println);
        
        
        
        // Customer
		List<Customer> customers = customerRepository.findAll();
        
        // Source Nguồn gốc
        Source source1 = new Source("Khách hàng hoặc đối tác giới thiệu");
        addNamedRepository(sourceRepository, source1);
        addNamedRepository(sourceRepository, new Source("Nhân viên kinh doanh tự tìm kiếm"));
        addNamedRepository(sourceRepository, new Source("Thông qua sự kiện, hội thảo, tập huấn"));
        addNamedRepository(sourceRepository, new Source("Khách hàng tự tìm đến"));
        addNamedRepository(sourceRepository, new Source("Khác"));
        
        // Classification Phân loại
        addNamedRepository(classificationRepository, new Classification("Khách hàng bán lẻ"));
        addNamedRepository(classificationRepository, new Classification("Khách hàng dự án"));
        
        // Field Lĩnh vực
        addNamedRepository(fieldRepository, new Field("Thương mại"));
        addNamedRepository(fieldRepository, new Field("Dịch vụ"));
        addNamedRepository(fieldRepository, new Field("Sản xuất"));
        addNamedRepository(fieldRepository, new Field("Xây lắp"));
        addNamedRepository(fieldRepository, new Field("Công nghiệp nhẹ"));
        
        // Type Loại hình
        addNamedRepository(typeRepository, new Type("Doanh nghiệp"));
        addNamedRepository(typeRepository, new Type("Hộ cá thể"));
        addNamedRepository(typeRepository, new Type("Hành chính sự nghiệp"));
        addNamedRepository(typeRepository, new Type("Khác"));
        
        // Career Ngành nghề
        addNamedRepository(careerRepository, new Career("Kinh doanh nhóm chính"));
        addNamedRepository(careerRepository, new Career("Kinh doanh thực phẩm"));
        addNamedRepository(careerRepository, new Career("Kinh doanh hóa chất"));
        addNamedRepository(careerRepository, new Career("Kinh doanh mỹ phẩm"));
        addNamedRepository(careerRepository, new Career("Kinh doanh ô tô, xe máy"));
        

        
        
        // Product
        Product product1 = new Product("Product type 1", "Thành phẩm 1", "Hộp", 200000L, 220000L,230000L,
				250000L, 150000L, Boolean.TRUE, "10%", Boolean.FALSE, 180000L);
		Product product2 = new Product("Product type 2", "Thành phẩm 2", "Bao", 400000L, 440000L,460000L,
				500000L, 300000L, Boolean.TRUE, "10%", Boolean.FALSE, 360000L);
        product1.setName("Thành phẩm 1");
        product2.setName("Thành phẩm 2");
		addNamedRepository(productRepository, product1);
		addNamedRepository(productRepository, product2);
		
		
		
		// Contact
		Contact contact = Contact.builder()
				.name("Tuấn")
				.lastName("Nguyễn Quang")
				.vocative("Ông")
				.position("Trưởng phòng")
				.department("Phòng Nhân sự")
				.classifications(classificationRepository.findAll().stream().collect(Collectors.toSet()))
				.phone("1591591590")
				.email("emailKoDuocTrung@gmail.com")
				.source(source1)
				.address("khu công nghệ cao Hòa Lạc – Km29, ĐCT08, Thạch Hoà, Thạch Thất, Hà Nội 10000")
				.build();
		addNamedRepository(contactRepository, contact);
		if (!customers.isEmpty()) {
			contact.setCustomer(customers.get(0));
		}
	}
	
	private <T extends NamedEntity<ID>, ID extends Serializable> void addNamedRepository(NamedJpaRepository<T, ID> repository, T t) {
        if (!repository.existsByName(t.getName())) {
        	repository.save(t);
        }
	}

}
