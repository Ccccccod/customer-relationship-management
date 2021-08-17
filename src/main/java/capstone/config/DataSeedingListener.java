/**
 * 
 */
package capstone.config;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import capstone.common.enums.OpportunityPhase;
import capstone.entity.BaseEntity;
import capstone.entity.Career;
import capstone.entity.Classification;
import capstone.entity.Contact;
import capstone.entity.Customer;
import capstone.entity.Field;
import capstone.entity.Invoice;
import capstone.entity.NamedEntity;
import capstone.entity.Opportunity;
import capstone.entity.Order;
import capstone.entity.PermissionAction;
import capstone.entity.PermissionFunction;
import capstone.entity.PermissionFunctionAction;
import capstone.entity.Potential;
import capstone.entity.Product;
import capstone.entity.ProductInfo;
import capstone.entity.ProductType;
import capstone.entity.Role;
import capstone.entity.Source;
import capstone.entity.Type;
import capstone.entity.User;
import capstone.model.Coded;
import capstone.repository.CareerRepository;
import capstone.repository.ClassificationRepository;
import capstone.repository.CodedRepository;
import capstone.repository.ContactRepository;
import capstone.repository.CustomerRepository;
import capstone.repository.FieldRepository;
import capstone.repository.InvoiceRepository;
import capstone.repository.NamedJpaRepository;
import capstone.repository.OpportunityRepository;
import capstone.repository.OrderRepository;
import capstone.repository.PermissionActionRepository;
import capstone.repository.PermissionFunctionActionRepository;
import capstone.repository.PermissionFunctionRepository;
import capstone.repository.PotentialRepository;
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
	private PotentialRepository potentialRepository;

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
    
    @Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PermissionActionRepository permissionActionRepository;

    @Autowired
    private PermissionFunctionRepository permissionFunctionRepository;

    @Autowired
    private PermissionFunctionActionRepository permissionFunctionActionRepository;

    /**
     * WARNING: disable when testing
     */
    private static final boolean enable = true;
    
    static final private String PASSWORD = "Minhkien1@";

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		if (!enable) {
			return;
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
        
        // Potential
        Potential potential1 = addNamedRepository(potentialRepository, Potential.builder()
        		.vocative("Ông")
        		.lastName("Nguyễn Quang")
        		.name("Tuấn")
        		.department("Phòng kinh doanh")
        		.position("Giám đốc")
        		.phone("0915367546")
        		.source(source2)
        		.email("quangtuanico@gmail.com")
        		.customer(customer1)
        		.taxCode(null)
        		.address("Số nhà 238, đường Nguyễn Thị Minh Khai, Phường Hoàng Văn Thụ, Thành phố Bắc Giang, Bắc Giang, Việt Nam")
        		.build());

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
        		.vat(10)
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
        		.vat(10)
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
        		.vat(10)
        		.implicitRecord(Boolean.FALSE)
        		.costUnitPrice(360000L)
        		.build();
		addNamedRepository(productRepository, product1);
		addNamedRepository(productRepository, product2);
		
		
		
		// Contact
		Contact contact1 = addNamedRepository(contactRepository, Contact.builder()
				.code("LH00001")
				.lastName("Nguyễn Quang")
				.name("Tuấn")
				.vocative("Ông")
				.position("Trưởng phòng")
				.department("Phòng Nhân sự")
				.classifications(classificationRepository.findAll().stream().collect(Collectors.toSet()))
				.phone("1591591590")
				.email("emailKoDuocTrung@gmail.com")
				.source(source1)
				.address("khu công nghệ cao Hòa Lạc – Km29, ĐCT08, Thạch Hoà, Thạch Thất, Hà Nội 10000")
//				.customer(customers.get(0))
				.build());
		
		
		// Opportunity
		Opportunity opportunity1 = addNamedRepository(opportunityRepository, Opportunity.builder()
				.customer(customer1)
				.contact(contact1)
				.name("Bán hàng cho " + customer1.getName())
				.moneyAmount(24_035_000L)
				.opportunityPhase(OpportunityPhase.NEGOTIATION)
				.successRate(70)
				.expectedEndDate(LocalDate.of(2022, Month.JUNE, 6))
				.expectedTurnOver(24_035_000L * 70 / 100)
				.source(source2)
				.productInfos(new LinkedHashSet<ProductInfo>(Arrays.asList(
						ProductInfo.builder()
								.productCode(product2.getCode())
								.explanation(product2.getExplanation())
								.unit(product2.getUnit())
								.amount(1)
								.price(product2.getSellPrice())
								.discount(0)
								.vat(product2.getVat())
								.build(),
						ProductInfo.builder()
								.productCode(product3.getCode())
								.explanation(product3.getExplanation())
								.unit(product3.getUnit())
								.amount(50)
								.price(product3.getSellPrice())
								.discount(10)
								.vat(product3.getVat())
								.build()
						)))
				.build());
		
		// Order
		Order order1 = addNamedRepository(orderRepository, Order.builder()
				.code("DH00009")
				.name("Đơn hàng bán cho FTech")
				.orderDate(LocalDate.of(2021, Month.APRIL, 26))
				.customer(customer1)
				.contact(contact1)
				.opportunity(opportunity1)
				.orderValue(34_100_000L)
				.liquidationValue(34_100_000L)
				.liquidationDeadline(LocalDate.of(2021, Month.APRIL, 26))
				.deliveryDeadline(LocalDate.of(2021, Month.APRIL, 26))
				.paid(Boolean.TRUE)
				.productInfos(new LinkedHashSet<ProductInfo>(Arrays.asList(
						ProductInfo.builder()
								.productCode(product2.getCode())
								.explanation(product2.getExplanation())
								.unit(product2.getUnit())
								.amount(1)
								.price(product2.getSellPrice())
								.discount(0)
								.vat(product2.getVat())
								.build(),
						ProductInfo.builder()
								.productCode(product3.getCode())
								.explanation(product3.getExplanation())
								.unit(product3.getUnit())
								.amount(50)
								.price(product3.getSellPrice())
								.discount(10)
								.vat(product3.getVat())
								.build()
						)))
				.build());
		
		// Invoice
		Invoice invoice1 = addCodedRepository(invoiceRepository, Invoice.builder()
				.code("DN0000001")
				.customer(customer1)
				.address("Số nhà 38, đường Bình Thới, Phường 12, Quận 10, Hồ Chí Minh, Việt Nam")
				.bankAccount("TNHH Eurodoor")
				.bank("Pro VN Bank")
				.taxCode("0185514943")
				.buyer(contact1)
				.receiverName("Min")
				.receiverEmail("Minn@gmail.com")
				.receiverPhone("120120129")
				.order(order1)
				.build());
		
		// Permissions

		List<PermissionFunction> permissionFunctions = Arrays.asList(
				addNamedRepository(permissionFunctionRepository, new PermissionFunction("POTENTIAL")),
				addNamedRepository(permissionFunctionRepository, new PermissionFunction("CUSTOMER")),
				addNamedRepository(permissionFunctionRepository, new PermissionFunction("OPPORTUNITY")),
				addNamedRepository(permissionFunctionRepository, new PermissionFunction("CONTACT")),
				addNamedRepository(permissionFunctionRepository, new PermissionFunction("ORDER")),
				addNamedRepository(permissionFunctionRepository, new PermissionFunction("INVOICE")),
				addNamedRepository(permissionFunctionRepository, new PermissionFunction("PRODUCT")),
				addNamedRepository(permissionFunctionRepository, new PermissionFunction("PRODUCT_TYPE")),
				addNamedRepository(permissionFunctionRepository, new PermissionFunction("USER")),
				addNamedRepository(permissionFunctionRepository, new PermissionFunction("ROLE")));

		List<PermissionAction> permissionActions = Arrays.asList(
				addNamedRepository(permissionActionRepository, new PermissionAction("CREATE")),
				addNamedRepository(permissionActionRepository, new PermissionAction("READ")),
				addNamedRepository(permissionActionRepository, new PermissionAction("UPDATE")),
				addNamedRepository(permissionActionRepository, new PermissionAction("DELETE")));
		
		List<PermissionFunctionAction> permissionFunctionActions = addPermissionFunctionActions(permissionFunctions, permissionActions);
		
		// Roles
		Role roleAdmin = roleRepository.findFirstByName(Role.ADMIN)
				.orElseGet(() -> roleRepository.save(Role.builder()
						.name(Role.ADMIN)
						.description("Vai trò này sẽ có đầy đủ tất cả các quyền.")
						.permissionFunctionActions(permissionFunctionActions.stream().collect(Collectors.toSet()))
						.build()));
        Role roleModerator = roleRepository.save(roleRepository.findFirstByName(Role.BUSINESS_STAFF)
        		.orElseGet(() -> Role.builder()
						.name(Role.BUSINESS_STAFF)
						.description("Vai trò này sẽ có tất cả các quyền trừ các quyền đặc biệt liên quan tới quản lý.")
						.permissionFunctionActions(permissionFunctionActions.stream().collect(Collectors.toSet()))
						.build()));
        Role roleMember = roleRepository.findFirstByName(Role.MEMBER)
        		.orElseGet(() -> roleRepository.save(Role.builder()
						.name(Role.MEMBER)
						.description("Vai trò này có các quyền cơ bản của thành viên.")
						.permissionFunctionActions(new HashSet<PermissionFunctionAction>())
						.build()));

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

	private List<PermissionFunctionAction> addPermissionFunctionActions(Collection<PermissionFunction> permissionFunctions,
			Collection<PermissionAction> permissionActions) {
		List<PermissionFunctionAction> permissionFunctionActions = new ArrayList<>();
		for (PermissionFunction permissionFunction : permissionFunctions) {
			for (PermissionAction permissionAction : permissionActions) {
				permissionFunctionActions.add(this.savePermissionFunctionAction(savePermissionFunctionAction(
						new PermissionFunctionAction(permissionFunction, permissionAction))));
			}
		}
		return permissionFunctionActions;
	}

	private PermissionFunctionAction savePermissionFunctionAction(PermissionFunctionAction permissionFunctionAction) {
		return permissionFunctionActionRepository
				.findByPermissionFunctionAndPermissionAction(permissionFunctionAction.getPermissionFunction(),
						permissionFunctionAction.getPermissionAction())
				.orElseGet(() -> permissionFunctionActionRepository.save(permissionFunctionAction));
	}
	
	private List<PermissionFunctionAction> addPermissionFunctionAction(PermissionFunctionAction... permissionFunctionAction) {
		return Stream.of(permissionFunctionAction)
				.map(t -> savePermissionFunctionAction(t))
				.collect(Collectors.toList());
	}
	
	private <T extends NamedEntity<ID>, ID extends Serializable> T addNamedRepository(
			NamedJpaRepository<T, ID> repository, T t) {
		return repository.findFirstByName(t.getName()).orElseGet(() -> repository.save(t));
	}

	private <T extends BaseEntity<ID> & Coded, ID extends Serializable, Re extends CodedRepository<T, ID> & JpaRepository<T, ID>> T addCodedRepository(
			Re repository, T t) {
		return repository.findFirstByCode(t.getCode()).orElseGet(() -> repository.save(t));
	}

	private <T extends BaseEntity<ID>, ID extends Serializable> T addRepository(
			JpaRepository<T, ID> repository, T t) {
		return repository.findById(t.getId()).orElseGet(() -> repository.save(t));
	}
	
	@Deprecated
	private <T extends BaseEntity<ID>, ID extends Serializable> List<T> addRepositorys(
			JpaRepository<T, ID> repository, T... ts) {
		return Stream.of(ts)
				.map(t -> addRepository(repository, t))
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
	}
	
}
