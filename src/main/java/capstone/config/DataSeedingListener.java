/**
 * 
 */
package capstone.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import capstone.entity.BaseEntity;
import capstone.entity.Career;
import capstone.entity.Classification;
import capstone.entity.Contact;
import capstone.entity.Customer;
import capstone.entity.Field;
import capstone.entity.NamedEntity;
import capstone.entity.Product;
import capstone.entity.ProductType;
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
import capstone.repository.ProductTypeRepository;
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
@SuppressWarnings("unused")
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
    private ProductTypeRepository productTypeRepository;

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
        
//        userRepository.findAll().forEach(System.out::println);
//        roleRepository.findAll().forEach(System.out::println);
        
        
        
        // Source Nguồn gốc
        Source source1 = addNamedRepository(sourceRepository, new Source("Khách hàng hoặc đối tác giới thiệu"));
        Source source2 = addNamedRepository(sourceRepository, new Source("Nhân viên kinh doanh tự tìm kiếm"));
        Source source3 = addNamedRepository(sourceRepository, new Source("Thông qua sự kiện, hội thảo, tập huấn"));
        Source source4 = addNamedRepository(sourceRepository, new Source("Khách hàng tự tìm đến"));
        Source source5 = addNamedRepository(sourceRepository, new Source("Khác"));
        
        // Classification Phân loại
        Classification classification1 = addNamedRepository(classificationRepository, new Classification("Khách hàng bán lẻ"));
        Classification classification2 = addNamedRepository(classificationRepository, new Classification("Khách hàng dự án"));
        
        // Field Lĩnh vực
        Field field1 = addNamedRepository(fieldRepository, new Field("Thương mại"));
        Field field2 = addNamedRepository(fieldRepository, new Field("Dịch vụ"));
        Field field3 = addNamedRepository(fieldRepository, new Field("Sản xuất"));
        Field field4 = addNamedRepository(fieldRepository, new Field("Xây lắp"));
        Field field5 = addNamedRepository(fieldRepository, new Field("Công nghiệp nhẹ"));
        
        // Type Loại hình
        Type type1 = addNamedRepository(typeRepository, new Type("Doanh nghiệp"));
        Type type2 = addNamedRepository(typeRepository, new Type("Hộ cá thể"));
        Type type3 = addNamedRepository(typeRepository, new Type("Hành chính sự nghiệp"));
        Type type4 = addNamedRepository(typeRepository, new Type("Khác"));
        
        // Career Ngành nghề
        Career career1 = addNamedRepository(careerRepository, new Career("Kinh doanh nhóm chính"));
        Career career2 = addNamedRepository(careerRepository, new Career("Kinh doanh thực phẩm"));
        Career career3 = addNamedRepository(careerRepository, new Career("Kinh doanh hóa chất"));
        Career career4 = addNamedRepository(careerRepository, new Career("Kinh doanh mỹ phẩm"));
        Career career5 = addNamedRepository(careerRepository, new Career("Kinh doanh ô tô, xe máy"));
        
		// Customer
		Customer customer1 = addNamedRepository(customerRepository, Customer.builder()
				.code("KH00001")
				.name("Công ty TNHH Eurodoor")
				.shortName("Eurodoor")
				.taxCode("0185514943")
				.phone("0185514943")
				.email("letan@eurodoor.com.vn")
				.source(source1)
				.classifications(Stream.of(classification1, classification2).collect(Collectors.toSet()))
				.fields(Stream.of(field1, field2).collect(Collectors.toSet()))
				.type(type1)
				.careers(Stream.of(career1, career2).collect(Collectors.toSet()))
				.address("Số nhà 38, đường Bình Thới, Phường 12, Quận 10, Hồ Chí Minh, Việt Nam")
				.build());
		List<Customer> customers = customerRepository.findAll();
        

        // Product Type
		List<ProductType> productTypes = addProductType();

        // Product
        Product product1 = Product.builder()
        		.code("HH00001")
        		.name("Thành phẩm 1")
        		.productType(Optional.ofNullable(productTypes.get(0)).orElse(null))
        		.explanation("Thành phẩm 1")
        		.unit("Hộp")
        		.sellPrice(200000L)
        		.sellPrice1(220000L)
        		.sellPrice2(230000L)
        		.permanentPrice(250000L)
        		.buyPrice(150000L)
        		.enterUnitPriorityAfterTax(Boolean.TRUE)
        		.vat("10%")
        		.implicitRecord(Boolean.FALSE)
        		.costUnitPrice(180000L)
        		.build();
        Product product2 = Product.builder()
        		.code("HH00002")
        		.name("Thành phẩm 2")
        		.productType(Optional.ofNullable(productTypes.get(1)).orElse(null))
        		.explanation("Thành phẩm 2")
        		.unit("Bao")
        		.sellPrice(400000L)
        		.sellPrice1(440000L)
        		.sellPrice2(460000L)
        		.permanentPrice(500000L)
        		.buyPrice(300000L)
        		.enterUnitPriorityAfterTax(Boolean.TRUE)
        		.vat("10%")
        		.implicitRecord(Boolean.FALSE)
        		.costUnitPrice(360000L)
        		.build();
        Product product3 = Product.builder()
        		.code("HH00003")
        		.name("T-Shirt")
        		.productType(Optional.ofNullable(productTypes.get(2)).orElse(null))
        		.explanation("T-Shirt")
        		.unit("Hộp")
        		.sellPrice(400000L)
        		.sellPrice1(440000L)
        		.sellPrice2(460000L)
        		.permanentPrice(500000L)
        		.buyPrice(300000L)
        		.enterUnitPriorityAfterTax(Boolean.TRUE)
        		.vat("10%")
        		.implicitRecord(Boolean.FALSE)
        		.costUnitPrice(360000L)
        		.build();
		addNamedRepository(productRepository, product1);
		addNamedRepository(productRepository, product2);
		
		
		
		// Contact
		Contact contact = Contact.builder()
				.code("LH00001")
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
		contact.setName("Tuấn");
		System.out.println(contact);
		if (!customers.isEmpty()) {
			contact.setCustomer(customers.get(0));
		}
		addNamedRepository(contactRepository, contact);
		
		
	}
	
	private List<ProductType> addProductType() {
		List<ProductType> productTypes;
		ProductType productType1 = addNamedRepository(productTypeRepository, ProductType.builder()
				.code("LH00001")
				.name("Clothes")
				.build());
			ProductType productType2 = addNamedRepository(productTypeRepository, ProductType.builder()
					.code("LH00002")
					.name("Shirt")
					.productType(productType1)
					.build());
				ProductType productType3 = addNamedRepository(productTypeRepository, ProductType.builder()
						.code("LH00003")
						.name("T-Shirt")
						.productType(productType2)
						.build());	
				ProductType productType4 = addNamedRepository(productTypeRepository, ProductType.builder()
						.code("LH00004")
						.name("V-neck Shirt")
						.productType(productType2)
						.build());
				ProductType productType5 = addNamedRepository(productTypeRepository, ProductType.builder()
						.code("LH00005")
						.name("Jacket")
						.productType(productType2)
						.build());
			ProductType productType6 = addNamedRepository(productTypeRepository, ProductType.builder()
					.code("LH00006")
					.name("Skirt")
					.productType(productType1)
					.build());
		ProductType productType7 = addNamedRepository(productTypeRepository, ProductType.builder()
				.code("LH00007")
				.name("Food")
				.build());
			ProductType productType8 = addNamedRepository(productTypeRepository, ProductType.builder()
					.code("LH00008")
					.name("Fruit")
					.productType(productType7)
					.build());
				ProductType productType9 = addNamedRepository(productTypeRepository, ProductType.builder()
						.code("LH00009")
						.name("Grape")
						.productType(productType8)
						.build());
					ProductType productType10 = addNamedRepository(productTypeRepository, ProductType.builder()
							.code("LH00010")
							.name("Table Grape")
							.productType(productType9)
							.build());
						ProductType productType11 = addNamedRepository(productTypeRepository, ProductType.builder()
								.code("LH00011")
								.name("Dominga Grape")
								.productType(productType10)
								.build());
					ProductType productType12 = addNamedRepository(productTypeRepository, ProductType.builder()
							.code("LH00012")
							.name("Wine Grape")
							.productType(productType9)
							.build());
						ProductType productType13 = addNamedRepository(productTypeRepository, ProductType.builder()
								.code("LH00013")
								.name("Black Muscat Grape")
								.productType(productType12)
								.build());
		ProductType productType20 = addNamedRepository(productTypeRepository, ProductType.builder()
				.code("LH00020")
				.name("Drink")
				.build());
		ProductType productType21 = addNamedRepository(productTypeRepository, ProductType.builder()
				.code("LH00021")
				.name("Electronic Equipment")
				.build());
			ProductType productType22 = addNamedRepository(productTypeRepository, ProductType.builder()
					.code("LH00022")
					.name("Piano")
					.productType(productType21)
					.build());
		productTypes = Arrays.asList(productType1, productType2, productType3, productType4, productType5, productType6,
				productType7, productType8, productType9, productType10, productType11, productType12, productType13,
				productType20, productType21, productType22);
		return productTypes;
	}

	private <T extends NamedEntity<ID>, ID extends Serializable> T addNamedRepository(
			NamedJpaRepository<T, ID> repository, T t) {
		return repository.findByName(t.getName()).orElseGet(() -> repository.save(t));
	}

	private <T extends BaseEntity<ID>, ID extends Serializable> T addRepository(
			JpaRepository<T, ID> repository, T t) {
		return repository.findById(t.getId()).orElseGet(() -> repository.save(t));
	}

}
