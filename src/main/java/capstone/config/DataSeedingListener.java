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
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import capstone.entity.BaseEntity;
import capstone.entity.BusinessType;
import capstone.entity.Career;
import capstone.entity.Classification;
import capstone.entity.Contact;
import capstone.entity.Customer;
import capstone.entity.Department;
import capstone.entity.Field;
import capstone.entity.Income;
import capstone.entity.Invoice;
import capstone.entity.MaritalStatus;
import capstone.entity.Opportunity;
import capstone.entity.OpportunityPhase;
import capstone.entity.Order;
import capstone.entity.PermissionAction;
import capstone.entity.PermissionFunction;
import capstone.entity.PermissionFunctionAction;
import capstone.entity.PhoneAreaCode;
import capstone.entity.Position;
import capstone.entity.Potential;
import capstone.entity.Product;
import capstone.entity.ProductInfo;
import capstone.entity.ProductType;
import capstone.entity.Role;
import capstone.entity.Source;
import capstone.entity.Type;
import capstone.entity.Unit;
import capstone.entity.User;
import capstone.entity.Vocative;
import capstone.model.Coded;
import capstone.model.Named;
import capstone.repository.BusinessTypeRepository;
import capstone.repository.CareerRepository;
import capstone.repository.ClassificationRepository;
import capstone.repository.CodedRepository;
import capstone.repository.ContactRepository;
import capstone.repository.CustomerRepository;
import capstone.repository.DepartmentRepository;
import capstone.repository.FieldRepository;
import capstone.repository.GenderRepository;
import capstone.repository.IncomeRepository;
import capstone.repository.InvoiceRepository;
import capstone.repository.MaritalStatusRepository;
import capstone.repository.NamedJpaRepository;
import capstone.repository.OpportunityPhaseRepository;
import capstone.repository.OpportunityRepository;
import capstone.repository.OrderRepository;
import capstone.repository.PermissionActionRepository;
import capstone.repository.PermissionFunctionActionRepository;
import capstone.repository.PermissionFunctionRepository;
import capstone.repository.PhoneAreaCodeRepository;
import capstone.repository.PositionRepository;
import capstone.repository.PotentialRepository;
import capstone.repository.ProductRepository;
import capstone.repository.ProductTypeRepository;
import capstone.repository.RoleRepository;
import capstone.repository.SourceRepository;
import capstone.repository.TypeRepository;
import capstone.repository.UnitRepository;
import capstone.repository.UserRepository;
import capstone.repository.VocativeRepository;
import capstone.utils.EncryptedPasswordUtils;

/**
 * Data Seeding Listener
 * @author Tuna
 */
@Component
@SuppressWarnings("unused")
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private VocativeRepository vocativeRepository;

	@Autowired
	private BusinessTypeRepository businessTypeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private GenderRepository genderRepository;

	@Autowired
	private IncomeRepository incomeRepository;

	@Autowired
	private MaritalStatusRepository maritalStatusRepository;

	@Autowired
	private OpportunityPhaseRepository opportunityPhaseRepository;

	@Autowired
	private PhoneAreaCodeRepository phoneAreaCodeRepository;
	
	@Autowired
	private PositionRepository positionRepository;
	
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
    
    @Autowired
    private UnitRepository unitRepository;

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
		
		// Vocative
		Vocative vocativeAnh = addNamedRepository(vocativeRepository, new Vocative("Anh"));
		Vocative vocativeChi = addNamedRepository(vocativeRepository, new Vocative("Chị"));
		Vocative vocativeOng = addNamedRepository(vocativeRepository, new Vocative("Ông"));
		Vocative vocativeBa = addNamedRepository(vocativeRepository, new Vocative("Bà"));
        
		//Department
		Department department1 = addNamedRepository(departmentRepository, new Department("Ban Giám đốc")) ;
		Department department2 = addNamedRepository(departmentRepository, new Department("Phòng Tài chính")) ;
		Department department3 = addNamedRepository(departmentRepository, new Department("Phòng Nhân sự")) ;
		Department department4 = addNamedRepository(departmentRepository, new Department("Phòng Marketing")) ;
		Department department5 = addNamedRepository(departmentRepository, new Department("Phòng CSKH")) ;
		Department department6 = addNamedRepository(departmentRepository, new Department("Phòng Hành chính tổng hợp")) ;
		Department department7 = addNamedRepository(departmentRepository, new Department("Phòng Kinh doanh")) ;

		// Position
		Position position1 = addNamedRepository(positionRepository, new Position("Chủ tịch"));
		Position position2 = addNamedRepository(positionRepository, new Position("Phó Chủ tịch"));
		Position position3 = addNamedRepository(positionRepository, new Position("Tổng Giám đốc"));
		Position position4 = addNamedRepository(positionRepository, new Position("Phó TGĐ"));
		Position position5 = addNamedRepository(positionRepository, new Position("Giám đốc"));
		Position position6 = addNamedRepository(positionRepository, new Position("Kế toán trưởng"));
		Position position7 = addNamedRepository(positionRepository, new Position("Trưởng phòng"));
		Position position8 = addNamedRepository(positionRepository, new Position("Trợ lý"));
		Position position9 = addNamedRepository(positionRepository, new Position("Nhân viên"));

		//Income
		
		Income income1 = addNamedRepository(incomeRepository, new Income("Dưới 3 tỷ "));
		Income income2 = addNamedRepository(incomeRepository, new Income("Từ 3 tỷ đồng đến 10 tỷ đồng "));
		Income income3 = addNamedRepository(incomeRepository, new Income("Từ 10 tỷ đồng đến 100 tỷ đồng"));
		Income income4 = addNamedRepository(incomeRepository, new Income("Trên 100 tỷ đồng"));

		// Oppotunity

		OpportunityPhase opportunityPhase1 = addNamedRepository(opportunityPhaseRepository,
				new OpportunityPhase("Mở đầu", 10));
		OpportunityPhase opportunityPhase2 = addNamedRepository(opportunityPhaseRepository,
				new OpportunityPhase("Khách hàng quan tâm", 30));
		OpportunityPhase opportunityPhase3 = addNamedRepository(opportunityPhaseRepository,
				new OpportunityPhase("Demo / Giới thiệu", 50));
		OpportunityPhase opportunityPhase4 = addNamedRepository(opportunityPhaseRepository,
				new OpportunityPhase("Đàm phán thương lượng", 70));
		OpportunityPhase opportunityPhase5 = addNamedRepository(opportunityPhaseRepository,
				new OpportunityPhase("Kết thúc thành công", 100));
		OpportunityPhase opportunityPhase6 = addNamedRepository(opportunityPhaseRepository,
				new OpportunityPhase("Kết thúc thất bại", 0));

		// MaritalStatus
		
		MaritalStatus maritalStatus1 = addNamedRepository(maritalStatusRepository, new MaritalStatus("Chưa kết hôn"));
		MaritalStatus maritalStatus2 = addNamedRepository(maritalStatusRepository, new MaritalStatus("Đã kết hôn"));
		MaritalStatus maritalStatus3 = addNamedRepository(maritalStatusRepository, new MaritalStatus("Đã ly hôn"));
		MaritalStatus maritalStatus4 = addNamedRepository(maritalStatusRepository, new MaritalStatus("Khác"));
		
        // Source Nguồn gốc
        Source sourceCustomerOrPartnerRefer = addNamedRepository(sourceRepository, new Source("Khách hàng hoặc đối tác giới thiệu"));
        Source sourceSelfSeekingSaleStaff = addNamedRepository(sourceRepository, new Source("Nhân viên kinh doanh tự tìm kiếm"));
        Source sourceThroughEventsAndSeminalAndTraining = addNamedRepository(sourceRepository, new Source("Thông qua sự kiện, hội thảo, tập huấn"));
        Source sourceThroughSeminalsAndTraining = addNamedRepository(sourceRepository, new Source("Thông qua hội thảo, tập huấn"));
        Source sourceCustomerCome = addNamedRepository(sourceRepository, new Source("Khách hàng tự tìm đến"));
        Source sourceOther = addNamedRepository(sourceRepository, new Source("Khác"));
        
        // Classification Phân loại
        Classification classificationRetail = addNamedRepository(classificationRepository, new Classification("Khách hàng bán lẻ"));
        Classification classificationProject = addNamedRepository(classificationRepository, new Classification("Khách hàng dự án"));
        
        // Field Lĩnh vực
        Field fieldCommerce = addNamedRepository(fieldRepository, new Field("Thương mại"));
        Field fieldService = addNamedRepository(fieldRepository, new Field("Dịch vụ"));
        Field fieldProduction = addNamedRepository(fieldRepository, new Field("Sản xuất"));
        Field fieldBuilding = addNamedRepository(fieldRepository, new Field("Xây lắp"));
        Field fieldLightIndustry = addNamedRepository(fieldRepository, new Field("Công nghiệp nhẹ"));
        
        // Loại hình doanh nghiệp
        BusinessType businessType01 = addNamedRepository(businessTypeRepository, new BusinessType("Công ty TNHH"));
        BusinessType businessType02 = addNamedRepository(businessTypeRepository, new BusinessType("Công ty cổ phần"));
        BusinessType businessType03 = addNamedRepository(businessTypeRepository, new BusinessType("Công ty có vốn đầu tư nước ngoài"));
        BusinessType businessType04 = addNamedRepository(businessTypeRepository, new BusinessType("Doanh nghiệp tư nhân"));
        BusinessType businessType05 = addNamedRepository(businessTypeRepository, new BusinessType("Tổ chức phi chính phủ"));
        BusinessType businessType06 = addNamedRepository(businessTypeRepository, new BusinessType("Cửa hàng, trung tâm"));
        BusinessType businessType07 = addNamedRepository(businessTypeRepository, new BusinessType("Hợp tác xã"));
        BusinessType businessType08 = addNamedRepository(businessTypeRepository, new BusinessType("Công ty hợp danh"));
        BusinessType businessType09 = addNamedRepository(businessTypeRepository, new BusinessType("Đơn vị HCSN cấp trung ương"));
        BusinessType businessType10 = addNamedRepository(businessTypeRepository, new BusinessType("Công ty HCSN cấp Tỉnh/Thành Phố"));
        BusinessType businessType11 = addNamedRepository(businessTypeRepository, new BusinessType("Công ty HCSN cấp Quận/Huyện"));
        
        // Type Loại hình
        Type typeBusiness = addNamedRepository(typeRepository, new Type("Doanh nghiệp"));
        Type typeIndividualHousehold = addNamedRepository(typeRepository, new Type("Hộ cá thể"));
        Type typeAdministrativeCareer = addNamedRepository(typeRepository, new Type("Hành chính sự nghiệp"));
        Type typeOther = addNamedRepository(typeRepository, new Type("Khác"));
        
        // Career Ngành nghề
        Career career01 = addNamedRepository(careerRepository, new Career(fieldCommerce, "Kinh doanh nhôm, kính"));
        Career career02 = addNamedRepository(careerRepository, new Career(fieldCommerce, "Kinh doanh thực phẩm"));
        Career career03 = addNamedRepository(careerRepository, new Career(fieldCommerce, "Kinh doanh hóa chất"));
        Career career04 = addNamedRepository(careerRepository, new Career(fieldCommerce, "Kinh doanh hóa mỹ phẩm"));
        Career career05 = addNamedRepository(careerRepository, new Career(fieldCommerce, "Kinh doanh ô tô, xe máy"));
        Career career06 = addNamedRepository(careerRepository, new Career(fieldCommerce, "Kinh doanh thiết bị máy tính văn phòng"));
        Career career07 = addNamedRepository(careerRepository, new Career(fieldCommerce, "Kinh doanh thiết bị y tế"));
        Career career08 = addNamedRepository(careerRepository, new Career(fieldCommerce, "Kinh doanh điện tử điện lạnh"));
        Career career09 = addNamedRepository(careerRepository, new Career(fieldCommerce, "Kinh doanh máy móc, thiết bị công nghiệp"));
        Career career10 = addNamedRepository(careerRepository, new Career(fieldCommerce, "Kinh doanh gỗ, thiết bị nội thất"));
        Career career11 = addNamedRepository(careerRepository, new Career(fieldCommerce, "Kinh doanh vật liệu xây dựng"));
        Career career12 = addNamedRepository(careerRepository, new Career(fieldCommerce, "Kinh doanh văn phòng phẩn, sách"));
        Career career13 = addNamedRepository(careerRepository, new Career(fieldCommerce, "Kinh doanh gas"));
        Career career14 = addNamedRepository(careerRepository, new Career(fieldCommerce, "Kinh doanh bất động sản"));
        Career career15 = addNamedRepository(careerRepository, new Career(fieldCommerce, "Kinh doanh xăng dầu"));
        Career career16 = addNamedRepository(careerRepository, new Career(fieldCommerce, "Kinh doanh hàng gia dụng"));
        Career career17 = addNamedRepository(careerRepository, new Career(fieldCommerce, "Kinh doanh nông lâm sản"));
        Career career18 = addNamedRepository(careerRepository, new Career(fieldCommerce, "Kinh doanh vật tư nông nghiệp"));
        Career career19 = addNamedRepository(careerRepository, new Career(fieldCommerce, "Kinh doanh vàng bạc, đá quý"));
        Career career20 = addNamedRepository(careerRepository, new Career(fieldCommerce, "Kinh doanh dược phẩm"));
        Career career21 = addNamedRepository(careerRepository, new Career(fieldCommerce, "Kinh doanh mặt hàng đồ uống (rượu, bia, nước giải khát, nước tinh khiết, nước đóng chai,…)"));
        Career career22 = addNamedRepository(careerRepository, new Career(fieldCommerce, "Kinh doanh sắt, thếp"));
        Career career23 = addNamedRepository(careerRepository, new Career(fieldCommerce, "Kinh doanh mặt hàng giày da, may mặc"));
        Career career24 = addNamedRepository(careerRepository, new Career(fieldCommerce, "Nhà phân phối"));
        Career career25 = addNamedRepository(careerRepository, new Career(fieldCommerce, "Kinh doanh thương mại khác"));
        
        Career career26 = addNamedRepository(careerRepository, new Career(fieldService, "Dịch vụ du lịch"));
        Career career27 = addNamedRepository(careerRepository, new Career(fieldService, "Dịch vụ vận tải"));
        Career career28 = addNamedRepository(careerRepository, new Career(fieldService, "Dịch vụ truyền thông, quảng cáo, tổ chức sự kiện"));
        Career career29 = addNamedRepository(careerRepository, new Career(fieldService, "Dịch vụ nhà hàng, khách sạn"));
        Career career30 = addNamedRepository(careerRepository, new Career(fieldService, "Dịch vụ tư vấn thiết kế xây dựng"));
        Career career31 = addNamedRepository(careerRepository, new Career(fieldService, "Dịch vụ vệ sinh môi trường đô thị"));
        Career career32 = addNamedRepository(careerRepository, new Career(fieldService, "Dịch vụ cho thuê bảo vệ, vệ sĩ"));
        Career career33 = addNamedRepository(careerRepository, new Career(fieldService, "Dịch vụ cung ứng lao động và việc làm"));
        Career career34 = addNamedRepository(careerRepository, new Career(fieldService, "Dịch vụ giáo dục và đào tạo"));
        Career career35 = addNamedRepository(careerRepository, new Career(fieldService, "Dịch vụ cung ứng phần mềm"));
        Career career36 = addNamedRepository(careerRepository, new Career(fieldService, "Dịch vụ tư vấn tài chính"));
        Career career37 = addNamedRepository(careerRepository, new Career(fieldService, "Văn phòng luật sư"));
        Career career38 = addNamedRepository(careerRepository, new Career(fieldService, "Dịch vụ cho thuê kho bãi"));
        Career career39 = addNamedRepository(careerRepository, new Career(fieldService, "Logistics"));
        Career career40 = addNamedRepository(careerRepository, new Career(fieldService, "Đại lý vé máy bay"));
        Career career41 = addNamedRepository(careerRepository, new Career(fieldService, "Dịch vụ chăm sóc sắc đẹp"));
        Career career42 = addNamedRepository(careerRepository, new Career(fieldService, "Dịch vụ hiểu hỉ"));
        Career career43 = addNamedRepository(careerRepository, new Career(fieldService, "Dịch vụ sửa chữa, bảo dưỡng và lắp đặt máy móc thiết bị"));
        Career career44 = addNamedRepository(careerRepository, new Career(fieldService, "Dịch vụ y tế"));
        Career career45 = addNamedRepository(careerRepository, new Career(fieldService, "Dịch vụ môi giới, đấu giá"));
        Career career46 = addNamedRepository(careerRepository, new Career(fieldService, "Dịch vụ khác"));

        Career career47 = addNamedRepository(careerRepository, new Career(fieldProduction, "Sản xuất chế biến thực phẩm"));
        Career career48 = addNamedRepository(careerRepository, new Career(fieldProduction, "Sản xuất hóa mỹ phẩm, dược phẩm"));
        Career career49 = addNamedRepository(careerRepository, new Career(fieldProduction, "Khai thác, chế biến than đá"));
        Career career50 = addNamedRepository(careerRepository, new Career(fieldProduction, "Sản xuất giày da, hàng may mặc, gia dụng"));
        Career career51 = addNamedRepository(careerRepository, new Career(fieldProduction, "Chế biến gỗ và sản xuất các sản phẩm từ gỗ, tre, nứa,..."));
        Career career52 = addNamedRepository(careerRepository, new Career(fieldProduction, "In ấn, xuất bản"));
        Career career53 = addNamedRepository(careerRepository, new Career(fieldProduction, "Sản xuất các sản phẩm từ nhựa, cao su, giấy"));
        Career career54 = addNamedRepository(careerRepository, new Career(fieldProduction, "Sản xuất sản phẩm kim loại và sản phẩm từ kim loại đúc sẵn"));
        Career career55 = addNamedRepository(careerRepository, new Career(fieldProduction, "Sản xuất sản phẩm kim khí điện máy"));
        Career career56 = addNamedRepository(careerRepository, new Career(fieldProduction, "Sản xuất chế biến thủy hải sản"));
        Career career57 = addNamedRepository(careerRepository, new Career(fieldProduction, "Sản xuất đồ uống đóng chai (rượu, bia, nước giải khát, nước tinh khiết)"));
        Career career58 = addNamedRepository(careerRepository, new Career(fieldProduction, "Sản xuất bao bì"));
        Career career59 = addNamedRepository(careerRepository, new Career(fieldProduction, "Khai thác, nuôi trồng thuỷ sản"));
        Career career60 = addNamedRepository(careerRepository, new Career(fieldProduction, "Khai khoáng"));
        Career career61 = addNamedRepository(careerRepository, new Career(fieldProduction, "Sản xuất thuốc lá"));
        Career career62 = addNamedRepository(careerRepository, new Career(fieldProduction, "Sản xuất máy móc thiết bị"));
        Career career63 = addNamedRepository(careerRepository, new Career(fieldProduction, "Sản xuất phim ảnh, băng đĩa nhạc"));
        Career career64 = addNamedRepository(careerRepository, new Career(fieldProduction, "Sản xuất, gia công phần mềm"));
        Career career65 = addNamedRepository(careerRepository, new Career(fieldProduction, "Trồng rừng"));
        Career career66 = addNamedRepository(careerRepository, new Career(fieldProduction, "Sản xuất gạch, xi măng"));
        Career career67 = addNamedRepository(careerRepository, new Career(fieldProduction, "Sản xuất khác"));
        
        /**
         * PhoneAreaCode
         */
        PhoneAreaCode phoneAreaCode01 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("296"));
        PhoneAreaCode phoneAreaCode02 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("254"));
        PhoneAreaCode phoneAreaCode03 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("209"));
        PhoneAreaCode phoneAreaCode04 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("204"));
        PhoneAreaCode phoneAreaCode05 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("291"));
        PhoneAreaCode phoneAreaCode06 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("222"));
        PhoneAreaCode phoneAreaCode07 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("275"));
        PhoneAreaCode phoneAreaCode08 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("256"));
        PhoneAreaCode phoneAreaCode09 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("274"));
        PhoneAreaCode phoneAreaCode10 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("271"));
        PhoneAreaCode phoneAreaCode11 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("252"));
        PhoneAreaCode phoneAreaCode12 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("290"));
        PhoneAreaCode phoneAreaCode13 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("292"));
        PhoneAreaCode phoneAreaCode14 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("206"));
        PhoneAreaCode phoneAreaCode15 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("236"));
        PhoneAreaCode phoneAreaCode16 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("262"));
        PhoneAreaCode phoneAreaCode17 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("261"));
        PhoneAreaCode phoneAreaCode18 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("215"));
        PhoneAreaCode phoneAreaCode19 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("251"));
        PhoneAreaCode phoneAreaCode20 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("277"));
        PhoneAreaCode phoneAreaCode21 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("269"));
        PhoneAreaCode phoneAreaCode22 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("219"));
        PhoneAreaCode phoneAreaCode23 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("226"));
        PhoneAreaCode phoneAreaCode24 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("24"));
        PhoneAreaCode phoneAreaCode25 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("239"));
        PhoneAreaCode phoneAreaCode26 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("220"));
        PhoneAreaCode phoneAreaCode27 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("225"));
        PhoneAreaCode phoneAreaCode28 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("293"));
        PhoneAreaCode phoneAreaCode29 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("28"));
        PhoneAreaCode phoneAreaCode30 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("218"));
        PhoneAreaCode phoneAreaCode31 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("221"));
        PhoneAreaCode phoneAreaCode32 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("258"));
        PhoneAreaCode phoneAreaCode33 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("297"));
        PhoneAreaCode phoneAreaCode34 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("260"));
        PhoneAreaCode phoneAreaCode35 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("213"));
        PhoneAreaCode phoneAreaCode36 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("263"));
        PhoneAreaCode phoneAreaCode37 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("205"));
        PhoneAreaCode phoneAreaCode38 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("214"));
        PhoneAreaCode phoneAreaCode39 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("272"));
        PhoneAreaCode phoneAreaCode40 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("228"));
        PhoneAreaCode phoneAreaCode41 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("238"));
        PhoneAreaCode phoneAreaCode42 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("229"));
        PhoneAreaCode phoneAreaCode43 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("259"));
        PhoneAreaCode phoneAreaCode44 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("210"));
        PhoneAreaCode phoneAreaCode45 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("257"));
        PhoneAreaCode phoneAreaCode46 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("232"));
        PhoneAreaCode phoneAreaCode47 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("235"));
        PhoneAreaCode phoneAreaCode48 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("255"));
        PhoneAreaCode phoneAreaCode49 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("203"));
        PhoneAreaCode phoneAreaCode50 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("233"));
        PhoneAreaCode phoneAreaCode51 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("299"));
        PhoneAreaCode phoneAreaCode52 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("212"));
        PhoneAreaCode phoneAreaCode53 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("276"));
        PhoneAreaCode phoneAreaCode54 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("227"));
        PhoneAreaCode phoneAreaCode55 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("208"));
        PhoneAreaCode phoneAreaCode56 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("237"));
        PhoneAreaCode phoneAreaCode57 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("234"));
        PhoneAreaCode phoneAreaCode58 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("273"));
        PhoneAreaCode phoneAreaCode59 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("294"));
        PhoneAreaCode phoneAreaCode60 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("207"));
        PhoneAreaCode phoneAreaCode61 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("270"));
        PhoneAreaCode phoneAreaCode62 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("211"));
        PhoneAreaCode phoneAreaCode63 = addNamedRepository(phoneAreaCodeRepository, new PhoneAreaCode("216"));
        
        // Unit
        Unit unit01 = addNamedRepository(unitRepository, new Unit("Bao"));
        Unit unit02 = addNamedRepository(unitRepository, new Unit("Bình"));
        Unit unit03 = addNamedRepository(unitRepository, new Unit("Bộ"));
        Unit unit04 = addNamedRepository(unitRepository, new Unit("Cái"));
        Unit unit05 = addNamedRepository(unitRepository, new Unit("Cây"));
        Unit unit06 = addNamedRepository(unitRepository, new Unit("Chai"));
        Unit unit07 = addNamedRepository(unitRepository, new Unit("Chiếc"));
        Unit unit08 = addNamedRepository(unitRepository, new Unit("Cuốn"));
        Unit unit09 = addNamedRepository(unitRepository, new Unit("Cuộn"));
        Unit unit10 = addNamedRepository(unitRepository, new Unit("Điểu"));
        Unit unit11 = addNamedRepository(unitRepository, new Unit("Gói"));
        Unit unit12 = addNamedRepository(unitRepository, new Unit("Hộp"));
        Unit unit13 = addNamedRepository(unitRepository, new Unit("Két"));
        Unit unit14 = addNamedRepository(unitRepository, new Unit("Kg"));
        Unit unit15 = addNamedRepository(unitRepository, new Unit("Khối"));
        Unit unit16 = addNamedRepository(unitRepository, new Unit("Lần"));
        Unit unit17 = addNamedRepository(unitRepository, new Unit("Lít"));
        Unit unit18 = addNamedRepository(unitRepository, new Unit("Lọ"));
        Unit unit19 = addNamedRepository(unitRepository, new Unit("m2"));
        Unit unit20 = addNamedRepository(unitRepository, new Unit("m3"));
        Unit unit21 = addNamedRepository(unitRepository, new Unit("ml"));
        Unit unit22 = addNamedRepository(unitRepository, new Unit("Thùng"));
        Unit unit23 = addNamedRepository(unitRepository, new Unit("Túi"));
        Unit unit24 = addNamedRepository(unitRepository, new Unit("Tuýp"));
        Unit unit25 = addNamedRepository(unitRepository, new Unit("Tấn"));
        Unit unit26 = addNamedRepository(unitRepository, new Unit("Tạ"));
        Unit unit27 = addNamedRepository(unitRepository, new Unit("Vỉ"));
        Unit unit28 = addNamedRepository(unitRepository, new Unit("Viên"));
        Unit unit29 = addNamedRepository(unitRepository, new Unit("Yến"));
       
		// Customer
		Customer customer4Eurodoor = addNamedRepository(customerRepository, Customer.builder()
				.code("KH00004")
				.name("Công ty TNHH Eurodoor")
				.shortName("Eurodoor")
				.taxCode("0185514943")
				.phone("0185514943")
				.email("letan@eurodoor.com.vn")
				.source(sourceCustomerOrPartnerRefer)
				.classifications(newSet(classificationRetail))
				.fields(newSet(fieldCommerce))
				.type(null)
				.careers(newSet(career01))
				.address("Số nhà 38, đường Bình Thới, Phường 12, Quận 10, Hồ Chí Minh, Việt Nam")
				.build());
		Customer customer5SGDHN = addNamedRepository(customerRepository, Customer.builder()
				.code("KH00005")
				.name("SỞ GIÁO DỤC VÀ ĐÀO TẠO HÀ NỘI")
				.shortName("SGD HN")
				.taxCode("0104128452")
				.phone("02483024554")
				.email("vanphong@hanoi.edu.vn")
				.classifications(newSet(classificationProject))
				.address("Số nhà 23, đường Quang Trung, Phường Phan Chu Trinh, Quận Hoàn Kiếm, Hà Nội, Việt Nam")
				.build());
		Customer customer6HoangGia = addNamedRepository(customerRepository, Customer.builder()
				.code("KH00006")
				.name("Công ty TNHH Hoàng Gia")
				.shortName("Hoàng Gia")
				.taxCode("0125546683")
				.phone("0234625478")
				.email("hoanggiahotel@gmail.com")
				.source(sourceThroughSeminalsAndTraining)
				.classifications(newSet(classificationRetail))
				.fields(newSet(fieldService))
				.type(null)
				.careers(null)
				.address("Số nhà 58, đường Nguyễn Sinh Cung, Phường Vĩ Dạ, Thành phố Huế, Thừa Thiên - Huế, Việt Nam")
				.build());
		Customer customer7AnhDuong = addNamedRepository(customerRepository, Customer.builder()
				.code("KH00007")
				.name("Công ty cổ phần Ánh Dương")
				.shortName("Anh Duong")
				.taxCode("0151284610")
				.phone("0236521456")
				.email("vanphong@sunshine.com.vn")
				.source(sourceCustomerCome)
				.classifications(newSet(classificationProject))
				.fields(newSet(fieldProduction))
				.type(null)
				.careers(newSet(career49))
				.address("Số nhà 15, đường Phạm Hữu Nhật, Phường Mỹ An, Quận Ngũ Hành Sơn, Đà Nẵng, Việt Nam")
				.build());
		Customer customer8VHT = addNamedRepository(customerRepository, Customer.builder()
				.code("KH00008")
				.name("Công ty cổ phần VHT Việt Nam")
				.shortName("VHT")
				.taxCode("1068480428")
				.phone("0245298913")
				.email("dulich@vht.com")
				.source(sourceCustomerOrPartnerRefer)
				.classifications(newSet(classificationRetail))
				.fields(newSet(fieldService))
				.type(null)
				.careers(newSet(career26))
				.address("Số nhà 605 Đường Võ Văn Kiệt, Phường 04, Quận 5, Hồ Chí Minh, Việt Nam")
				.build());
		Customer customer9FTech = addNamedRepository(customerRepository, Customer.builder()
				.code("KH00009")
				.name("Công ty cổ phần FTech Việt Nam")
				.taxCode("1068480428")
				.shortName("FTech")
				.phone("02253468154")
				.email("tintuc@ftech.com.vn")
				.source(sourceSelfSeekingSaleStaff)
				.classifications(newSet(classificationProject))
				.fields(newSet(fieldService))
				.type(null)
				.careers(newSet(career35))
				.address("13 Đường Bà Huyện Thanh Quan, Phường 13, Quận Bình Thạnh, Hồ Chí Minh, Việt Nam")
				.build());
		Customer customer10PhanAnh = addNamedRepository(customerRepository, Customer.builder()
				.code("KH00010")
				.name("Công ty TNHH xuất nhập khẩu Phan Anh")
				.taxCode("1348965143")
				.shortName("Phan Anh")
				.phone("0245292653")
				.email("recruit@phananh.com")
				.source(sourceSelfSeekingSaleStaff)
				.classifications(newSet(classificationProject))
				.fields(newSet(fieldService))
				.type(null)
				.careers(newSet(career27))
				.address("Số nhà 10 đường Hoàng Hoa Thám , Phường Cống Vị, Quận Ba Đình, Hà Nội, Việt Nam")
				.build());
		Customer customer1NGS = addNamedRepository(customerRepository, Customer.builder()
				.code("KH00001")
				.name("Công ty cổ phần TM&ĐT NGS")
				.taxCode("1356789465")
				.shortName("NGS")
				.phone("0243981234")
				.email("lienhe@ngs.com.vn")
				.source(sourceCustomerCome)
				.classifications(newSet(classificationProject))
				.fields(newSet(fieldCommerce))
				.type(null)
				.careers(newSet(career04))
				.address("Số nhà 51, phố Lê Đại Hành, Phường Lê Đại Hành, Quận Hai Bà Trưng, Hà Nội, Việt Nam")
				.build());
		Customer customer2VOLTRANS = addNamedRepository(customerRepository, Customer.builder()
				.code("KH00002")
				.name("Công ty cổ phần Voltrans")
				.taxCode("2555123456")
				.shortName("VOLTRANS")
				.phone("0245221234")
				.email("thongtin@voltrans.com")
				.source(sourceCustomerOrPartnerRefer)
				.classifications(newSet(classificationRetail))
				.fields(newSet(fieldProduction))
				.type(null)
				.careers(newSet(career47))
				.address("Số nhà 3, ngõ 78, phố Duy Tân, Phường Dịch Vọng Hậu, Quận Cầu Giấy, Hà Nội, Việt Nam")
				.build());
		Customer customer3ICOVN = addNamedRepository(customerRepository, Customer.builder()
				.code("KH00003")
				.name("Công ty cổ phẩn ICO Việt Nam")
				.taxCode("0195314848")
				.shortName("ICO VN")
				.phone("02043245823")
				.email("hanhchinh@icovn.com.vn")
				.source(sourceSelfSeekingSaleStaff)
				.classifications(newSet(classificationProject))
				.fields(newSet(fieldService))
				.type(null)
				.careers(newSet(career34))
				.address("Số nhà 238, đường Nguyễn Thị Minh Khai, Phường Hoàng Văn Thụ, Thành phố Bắc Giang, Bắc Giang, Việt Nam")
				.build());
		List<Customer> customers = customerRepository.findAll();
        
        // Potential
        Potential potential6 = addNamedRepository(potentialRepository, Potential.builder()
				.code("TN00001")
        		.vocative(vocativeChi)
        		.lastName("Tôn Nữ Lạc").name("Huyền")
        		.department(department1)
        		.position(position1)
        		.phone("0399542127").officePhone("0234625478")
        		.source(sourceOther)
        		.officeEmail("lachuyen.hoanggia@gmail.com").email("tonnulachuyen@gmail.com")
        		.customer(customer10PhanAnh.getName())
        		.taxCode("0125546683")
        		.address("Số nhà 58, đường Nguyễn Sinh Cung, Phường Vĩ Dạ, Thành phố Huế, Thừa Thiên - Huế, Việt Nam")
        		.build());
        Potential potential7 = addNamedRepository(potentialRepository, Potential.builder()
				.code("TN00002")
        		.vocative(vocativeAnh)
        		.lastName("Trần Nhật").name("Vũ")
        		.department(department2)
        		.position(position2)
        		.phone("0354265794").officePhone("0236521456")
        		.source(sourceCustomerCome)
        		.officeEmail("vunt2@sunshine.com.vn").email("nhatvu94@gmail.com")
        		.customer(customer7AnhDuong.getName())
        		.taxCode("0151284610")
        		.address("Số nhà 15, đường Phạm Hữu Nhật, Phường Mỹ An, Quận Ngũ Hành Sơn, Đà Nẵng, Việt Nam")
        		.build());
        Potential potential8 = addNamedRepository(potentialRepository, Potential.builder()
				.code("TN00003")
        		.vocative(vocativeBa)
        		.lastName("Võ Thị Hoàng").name("Anh")
        		.department(department3)
        		.position(position3)
        		.phone("0912901685").officePhone("0245298913")
        		.source(sourceCustomerCome)
        		.officeEmail("vthanh@vht.com.vn").email("vthanh@gmail.com")
        		.customer(customer8VHT.getName())
        		.taxCode(null)
        		.address("Số nhà 605 Đường Võ Văn Kiệt, Phường 04, Quận 5, Hồ Chí Minh, Việt Nam")
        		.build());
        Potential potential9 = addNamedRepository(potentialRepository, Potential.builder()
				.code("TN00004")
        		.vocative(vocativeOng)
        		.lastName("Phạm Tiến").name("Hoàng")
        		.department(department4)
        		.position(position4)
        		.phone("0915145846").officePhone(null)
        		.source(sourceCustomerCome)
        		.officeEmail("pthoang@ftech.com").email("pthoang@gmail.com")
        		.customer(customer9FTech.getName())
        		.taxCode(null)
        		.address("13 Đường Bà Huyện Thanh Quan, Phường 13, Quận Bình Thạnh, Hồ Chí Minh, Việt Nam")
        		.build());
        Potential potential10 = addNamedRepository(potentialRepository, Potential.builder()
				.code("TN00005")
        		.vocative(vocativeBa)
        		.lastName("Trần Thị").name("Dung")
        		.department(department5)
        		.position(position5)
        		.phone("0914601685").officePhone(null)
        		.source(sourceCustomerCome)
        		.officeEmail("ttdung@phananh.com.vn").email("ttdung@gmail.com")
        		.customer(customer10PhanAnh.getName())
        		.taxCode(null)
        		.address("Số nhà 10 đường Hoàng Hoa Thám , Phường Cống Vị, Quận Ba Đình, Hà Nội, Việt Nam")
        		.build());
        Potential potential11 = addNamedRepository(potentialRepository, Potential.builder()
				.code("TN00006")
        		.vocative(vocativeOng)
        		.lastName("Nguyễn Quang").name("Tuấn")
        		.department(department6)
        		.position(position6)
        		.phone("0915367546").officePhone("02043245823")
        		.source(sourceSelfSeekingSaleStaff)
        		.officeEmail("tuannq@ico.com.vn").email("quangtuanico@gmail.com")
        		.customer(customer3ICOVN.getName())
        		.taxCode(null)
        		.address("Số nhà 238, đường Nguyễn Thị Minh Khai, Phường Hoàng Văn Thụ, Thành phố Bắc Giang, Bắc Giang, Việt Nam")
        		.build());
        Potential potential2 = addNamedRepository(potentialRepository, Potential.builder()
				.code("TN00007")
        		.vocative(vocativeOng)
        		.lastName("Nguyễn Văn").name("Nam")
        		.department(department7)
        		.position(position7)
        		.phone("0988123456").officePhone("0243981234")
        		.source(sourceSelfSeekingSaleStaff)
        		.officeEmail("nvnam@ngs.com.vn").email("nvnam@gmail.com")
        		.customer(customer1NGS.getName())
        		.taxCode(null)
        		.address("Số nhà 51, phố Lê Đại Hành, Phường Lê Đại Hành, Quận Hai Bà Trưng, Hà Nội, Việt Nam")
        		.build());
        Potential potential3 = addNamedRepository(potentialRepository, Potential.builder()
				.code("TN00008")
        		.vocative(vocativeBa)
        		.lastName("Phạm Thị Hà").name("Phương")
        		.department(department6)
        		.position(position6)
        		.phone("0975123456").officePhone("0245221234")
        		.source(sourceCustomerCome)
        		.officeEmail("pthphuong@voltrans.com").email("pthphuong@gmail.com")
        		.customer(customer2VOLTRANS.getName())
        		.taxCode(null)
        		.address("Số nhà 3, ngõ 78, phố Duy Tân, Phường Dịch Vọng Hậu, Quận Cầu Giấy, Hà Nội, Việt Nam")
        		.build());
        Potential potential4 = addNamedRepository(potentialRepository, Potential.builder()
				.code("TN00009")
        		.vocative(vocativeChi)
        		.lastName("Trịnh Thị").name("Vinh")
        		.department(department1)
        		.position(position1)
        		.phone("0399958428").officePhone("0283268542")
        		.source(sourceCustomerOrPartnerRefer)
        		.officeEmail("vinhtt1@eurodoor.com.vn").email("trinhvinh22292@gmail.com")
        		.customer(customer4Eurodoor.getName())
        		.taxCode("0185514943")
        		.address("Số nhà 38, đường Bình Thới, Phường 12, Quận 10, Hồ Chí Minh, Việt Nam")
        		.build());
        Potential potential5 = addNamedRepository(potentialRepository, Potential.builder()
				.code("TN00010")
        		.vocative(vocativeAnh)
        		.lastName("Nguyễn Anh").name("Tuấn")
        		.department(department2)
        		.position(position2)
        		.phone("0942354785").officePhone("02483024554")
        		.source(sourceThroughSeminalsAndTraining)
        		.officeEmail("natuan@hanoi.edu.vn").email("anhtuan180991@gmail.com")
        		.customer(customer5SGDHN.getName())
        		.taxCode("0104128452")
        		.address("Số nhà 23, đường Quang Trung, Phường Phan Chu Trinh, Quận Hoàn Kiếm, Hà Nội, Việt Nam")
        		.build());

        // Product Type
		List<ProductType> productTypes = addProductType();

        // Product
        Product product1 = addNamedRepository(productRepository, Product.builder()
        		.code("HH00001")
        		.name("Thành phẩm 1")
        		.productType(null)
        		.explanation("Thành phẩm 1")
        		.unit(unit12)
        		.buyPrice(150000L)
        		.sellPrice(200000L).sellPrice1(220000L).sellPrice2(230000L)
        		.permanentPrice(250000L)
        		.vat(10)
        		.costUnitPrice(180000L)
        		.enterUnitPriorityAfterTax(Boolean.FALSE)
        		.implicitRecord(Boolean.FALSE)
        		.build());
        Product product2 = addNamedRepository(productRepository, Product.builder()
        		.code("HH00002")
        		.name("Thành phẩm 2")
        		.productType(null)
        		.explanation("Thành phẩm 2")
        		.unit(unit04)
        		.buyPrice(600_000L)
        		.sellPrice(800_000L).sellPrice1(900_000L).sellPrice2(1_000_000L)
        		.permanentPrice(900_000L)
        		.vat(10)
        		.costUnitPrice(750_000L)
        		.enterUnitPriorityAfterTax(Boolean.TRUE)
        		.implicitRecord(Boolean.TRUE)
        		.build());
        Product product3 = addNamedRepository(productRepository, Product.builder()
        		.code("HH00003")
        		.name("Thành phẩm 3")
        		.productType(null)
        		.explanation("Thành phẩm 3")
        		.unit(unit03)
        		.buyPrice(450_000L)
        		.sellPrice(460_000L).sellPrice1(470_000L).sellPrice2(420_000L)
        		.permanentPrice(500_000L)
        		.buyPrice(380_000L)
        		.vat(10)
        		.costUnitPrice(380_000L)
        		.enterUnitPriorityAfterTax(Boolean.FALSE)
        		.implicitRecord(Boolean.FALSE)
        		.build());
        Product product4 = addNamedRepository(productRepository, Product.builder()
        		.code("HH00004")
        		.name("Thành phẩm 4")
        		.productType(null)
        		.explanation("Thành phẩm 4")
        		.unit(unit07)
        		.buyPrice(200_000L)
        		.sellPrice(210_000L).sellPrice1(220_000L).sellPrice2(230_000L)
        		.permanentPrice(240_000L)
        		.vat(10)
        		.costUnitPrice(150_000L)
        		.enterUnitPriorityAfterTax(Boolean.TRUE)
        		.implicitRecord(Boolean.TRUE)
        		.build());
        Product product5 = addNamedRepository(productRepository, Product.builder()
        		.code("HH00005")
        		.name("Thành phẩm 5")
        		.productType(null)
        		.explanation("Thành phẩm 5")
        		.unit(unit25)
        		.buyPrice(80_000L)
        		.sellPrice(85_000L).sellPrice1(90_000L).sellPrice2(78_000L)
        		.permanentPrice(92_000L)
        		.vat(10)
        		.costUnitPrice(70_000L)
        		.enterUnitPriorityAfterTax(Boolean.TRUE)
        		.implicitRecord(Boolean.TRUE)
        		.build());
        Product product6 = addNamedRepository(productRepository, Product.builder()
        		.code("HH00006")
        		.name("Thành phẩm 6")
        		.productType(null)
        		.explanation("Thành phẩm 6")
        		.unit(unit02)
        		.buyPrice(300_000L)
        		.sellPrice(210_000L).sellPrice1(320_000L).sellPrice2(340_000L)
        		.permanentPrice(350_000L)
        		.vat(10)
        		.costUnitPrice(150_000L)
        		.enterUnitPriorityAfterTax(Boolean.TRUE)
        		.implicitRecord(Boolean.TRUE)
        		.build());
        Product product7 = addNamedRepository(productRepository, Product.builder()
        		.code("HH00007")
        		.name("Thành phẩm 7")
        		.productType(null)
        		.explanation("Thành phẩm 7")
        		.unit(unit03)
        		.buyPrice(1_500_000L)
        		.sellPrice(1_550_000L).sellPrice1(1_650_000L).sellPrice2(1_700_000L)
        		.permanentPrice(1_800_000L)
        		.vat(10)
        		.costUnitPrice(750_000L)
        		.enterUnitPriorityAfterTax(Boolean.TRUE)
        		.implicitRecord(Boolean.TRUE)
        		.build());
        Product product8 = addNamedRepository(productRepository, Product.builder()
        		.code("HH00008")
        		.name("Dịch Vụ 1")
        		.productType(null)
        		.explanation("Dịch Vụ 1")
        		.unit(unit18)
        		.buyPrice(10_000_000L)
        		.sellPrice(15_000_000L).sellPrice1(13_000_000L).sellPrice2(14_000_000L)
        		.permanentPrice(12_000_000L)
        		.vat(10)
        		.costUnitPrice(0L)
        		.enterUnitPriorityAfterTax(Boolean.FALSE)
        		.implicitRecord(Boolean.FALSE)
        		.build());
        Product product9 = addNamedRepository(productRepository, Product.builder()
        		.code("HH00009")
        		.name("Dịch Vụ 2")
        		.productType(null)
        		.explanation("Dịch Vụ 2")
        		.unit(unit19)
        		.buyPrice(10_500_000L)
        		.sellPrice(15_500_000L).sellPrice1(13_500_000L).sellPrice2(14_500_000L)
        		.permanentPrice(12_500_000L)
        		.vat(10)
        		.costUnitPrice(0L)
        		.enterUnitPriorityAfterTax(Boolean.FALSE)
        		.implicitRecord(Boolean.FALSE)
        		.build());
        Product product10 = addNamedRepository(productRepository, Product.builder()
        		.code("HH00010")
        		.name("Dịch Vụ 3")
        		.productType(null)
        		.explanation("Dịch Vụ 3")
        		.unit(unit20)
        		.buyPrice(6_500_000L)
        		.sellPrice(11_500_000L).sellPrice1(9_500_000L).sellPrice2(10_500_000L)
        		.permanentPrice(8_500_000L)
        		.vat(10)
        		.costUnitPrice(0L)
        		.enterUnitPriorityAfterTax(Boolean.FALSE)
        		.implicitRecord(Boolean.FALSE)
        		.build());
		
		// Contact
		Contact contact1 = addNamedRepository(contactRepository, Contact.builder()
				.code("LH00001")
				.vocative(vocativeAnh)
				.lastName("Nguyễn Văn").name("Nam")
				.position(position1)
				.department(department1)
				.phone("0988123456").officePhone("0243981234")
				.officeEmail("nvnam@ngs.com.vn").email("nvnam@gmail.com")
				.customer(customer1NGS)
				.source(sourceSelfSeekingSaleStaff)
				.classifications(newSet(classificationProject))
				.address("Số nhà 51, phố Lê Đại Hành, Phường Lê Đại Hành, Quận Hai Bà Trưng, Hà Nội, Việt Nam")
				.build());
		Contact contact2 = addNamedRepository(contactRepository, Contact.builder()
				.code("LH00002")
				.vocative(vocativeBa)
				.lastName("Phạm Thị Hà").name("Phương")
				.position(position2).department(department2)
				.phone("0975123456").officePhone("0245221234")
				.officeEmail("pthphuong@voltrans.com").email("pthphuong@gmail.com")
				.customer(customer2VOLTRANS)
				.source(sourceCustomerCome)
				.classifications(newSet(classificationProject))
				.address("Số nhà 3, ngõ 78, phố Duy Tân, Phường Dịch Vọng Hậu, Quận Cầu Giấy, Hà Nội, Việt Nam")
				.build());
		Contact contact3 = addNamedRepository(contactRepository, Contact.builder()
				.code("LH00003")
				.vocative(vocativeBa)
				.lastName("Nguyễn Quang").name("Tuấn")
				.position(position3).department(department3)
				.phone("0915367546").officePhone("0245221234")
				.officeEmail("pthphuong@voltrans.com").email("quangtuanico@gmail.com")
				.customer(customer3ICOVN)
				.source(sourceSelfSeekingSaleStaff)
				.classifications(newSet(classificationProject))
				.address("khu công nghệ cao Hòa Lạc – Km29, ĐCT08, Thạch Hoà, Thạch Thất, Hà Nội 10000")
				.address("Số nhà 238, đường Nguyễn Thị Minh Khai, Phường Hoàng Văn Thụ, Thành phố Bắc Giang, Bắc Giang, Việt Nam")
				.build());
		Contact contact4 = addNamedRepository(contactRepository, Contact.builder()
				.code("LH00004")
				.vocative(vocativeChi)
				.lastName("Trịnh Thị").name("Vinh")
				.position(position3).department(department3)
				.phone("0399958428").officePhone("0245221234")
				.officeEmail("vinhtt1@eurodoor.com.vn").email("trinhvinh22292@gmail.com")
				.customer(customer4Eurodoor)
				.source(sourceSelfSeekingSaleStaff)
				.classifications(newSet(classificationProject))
				.address("Số nhà 38, đường Bình Thới, Phường 12, Quận 10, Hồ Chí Minh, Việt Nam")
				.build());
		Contact contact5 = addNamedRepository(contactRepository, Contact.builder()
				.code("LH00005")
				.vocative(vocativeChi)
				.lastName("Nguyễn Anh").name("Tuấn")
				.position(position4).department(department4)
				.phone("0942354785").officePhone("02483024554")
				.officeEmail("natuan@hanoi.edu.vn").email("anhtuan180991@gmail.com")
				.customer(customer5SGDHN)
				.source(sourceThroughSeminalsAndTraining)
				.classifications(newSet(classificationProject))
				.address("Số nhà 23, đường Quang Trung, Phường Phan Chu Trinh, Quận Hoàn Kiếm, Hà Nội, Việt Nam")
				.build());
		Contact contact6 = addNamedRepository(contactRepository, Contact.builder()
				.code("LH00006")
				.vocative(vocativeChi)
				.lastName("Tôn Nữ Lạc").name("Huyền")
				.position(position5).department(department5)
				.phone("0399542127").officePhone("0234625478")
				.officeEmail("lachuyen.hoanggia@gmail.com").email("tonnulachuyen@gmail.com")
				.customer(customer6HoangGia)
				.source(sourceOther)
				.classifications(newSet(classificationRetail))
				.address("Số nhà 58, đường Nguyễn Sinh Cung, Phường Vĩ Dạ, Thành phố Huế, Thừa Thiên - Huế, Việt Nam")
				.build());
		Contact contact7 = addNamedRepository(contactRepository, Contact.builder()
				.code("LH00007")
				.vocative(vocativeAnh)
				.lastName("Trần Nhật").name("Vũ")
				.position(position6).department(department6)
				.phone("0354265794").officePhone("0236521456")
				.officeEmail("vunt2@sunshine.com.vn").email("nhatvu94@gmail.com")
				.customer(customer7AnhDuong)
				.source(sourceCustomerCome)
				.classifications(newSet(classificationProject))
				.address("Số nhà 15, đường Phạm Hữu Nhật, Phường Mỹ An, Quận Ngũ Hành Sơn, Đà Nẵng, Việt Nam")
				.build());
		Contact contact8 = addNamedRepository(contactRepository, Contact.builder()
				.code("LH00008")
				.vocative(vocativeBa)
				.lastName("Võ Thị Hoàng").name("Anh")
				.position(position7).department(department7)
				.phone("0912901685").officePhone("0245298913")
				.officeEmail("vthanh@vht.com.vn").email("vthanh@gmail.com")
				.customer(customer8VHT)
				.source(sourceCustomerCome)
				.classifications(newSet(classificationProject))
				.address("Số nhà 605 Đường Võ Văn Kiệt, Phường 04, Quận 5, Hồ Chí Minh, Việt Nam")
				.build());
		Contact contact9 = addNamedRepository(contactRepository, Contact.builder()
				.code("LH00009")
				.vocative(vocativeOng)
				.lastName("Phạm Tiến").name("Hoàng")
				.position(position2).department(department5)
				.phone("0915145846").officePhone("02253468154")
				.officeEmail("pthoang@ftech.com").email("pthoang@gmail.com")
				.customer(customer9FTech)
				.source(sourceSelfSeekingSaleStaff)
				.classifications(newSet(classificationProject))
				.address("13 Đường Bà Huyện Thanh Quan, Phường 13, Quận Bình Thạnh, Hồ Chí Minh, Việt Nam")
				.build());
		Contact contact10 = addNamedRepository(contactRepository, Contact.builder()
				.code("LH00010")
				.vocative(vocativeBa)
				.lastName("Trần Thị").name("Dung")
				.position(position5).department(department1)
				.phone("0245292653").officePhone("0245292653")
				.officeEmail("ttdung@phananh.com.vn").email("ttdung@gmail.com")
				.customer(customer10PhanAnh)
				.source(sourceSelfSeekingSaleStaff)
				.classifications(newSet(classificationProject))
				.address("Số nhà 10 đường Hoàng Hoa Thám , Phường Cống Vị, Quận Ba Đình, Hà Nội, Việt Nam")
				.build());
		
		// Opportunity
		Opportunity opportunity1 = Opportunity.builder()
				.code("CH00001")
				.customer(customer1NGS)
				.contact(contact1)
				.name("Bán hàng cho " + customer1NGS.getName())
				.opportunityPhase(opportunityPhase1)
				.successRate(10)
				.expectedEndDate(LocalDate.of(2021, Month.JUNE, 8))
				.source(sourceSelfSeekingSaleStaff)
				.build();
		opportunity1.addToProductInfo(newSet( //
				ProductInfo.builder()
						.product(product1)
						.productCode(product1.getCode())
						.explanation(product1.getExplanation())
						.unit(product1.getUnit())
						.amount(10)
						.price(product1.getSellPrice())
						.discount(10)
						.vat(product1.getVat())
						.build()
		));
		opportunity1 = addNamedRepository(opportunityRepository, opportunity1);
		Opportunity opportunity2 = Opportunity.builder()
				.code("CH00002")
				.customer(customer2VOLTRANS)
				.contact(contact2)
				.name("Bán hàng cho " + customer2VOLTRANS.getName())
				.opportunityPhase(opportunityPhase2)
				.successRate(70)
				.expectedEndDate(LocalDate.of(2021, Month.JUNE, 8))
				.source(sourceCustomerCome)
				.build();
		opportunity2.addToProductInfo(newSet( //
				ProductInfo.builder()
						.product(product2)
						.productCode(product2.getCode())
						.explanation(product2.getExplanation())
						.unit(product2.getUnit())
						.amount(15)
						.price(product2.getSellPrice())
						.discount(2)
						.vat(product2.getVat())
						.build()
		));
		opportunity2 = addNamedRepository(opportunityRepository, opportunity2);
		Opportunity opportunity3 = Opportunity.builder()
				.code("CH00003")
				.customer(customer3ICOVN)
				.contact(contact3)
				.name("Bán hàng cho " + customer3ICOVN.getName())
				.opportunityPhase(opportunityPhase3)
				.successRate(70)
				.expectedEndDate(LocalDate.of(2021, Month.JUNE, 8))
				.source(sourceSelfSeekingSaleStaff)
				.build();
		opportunity3.addToProductInfo(newSet( //
				ProductInfo.builder()
						.product(product3)
						.productCode(product3.getCode())
						.explanation(product3.getExplanation())
						.unit(product3.getUnit())
						.amount(50)
						.price(product3.getSellPrice())
						.discount(5)
						.vat(product3.getVat())
						.build()));
		opportunity3 = addNamedRepository(opportunityRepository, opportunity3);
		Opportunity opportunity4 = Opportunity.builder()
				.code("CH00004")
				.customer(customer4Eurodoor)
				.contact(contact4)
				.name("Bán hàng cho " + customer4Eurodoor.getName())
				.opportunityPhase(opportunityPhase4)
				.successRate(100)
				.expectedEndDate(LocalDate.of(2021, Month.JUNE, 8))
				.source(sourceCustomerOrPartnerRefer)
				.build();
		opportunity4.addToProductInfo(newSet( //
				ProductInfo.builder()
						.product(product4)
						.productCode(product4.getCode())
						.explanation(product4.getExplanation())
						.unit(product4.getUnit())
						.amount(100)
						.price(product4.getSellPrice())
						.discount(10)
						.vat(product4.getVat())
						.build()));
		opportunity4 = addNamedRepository(opportunityRepository, opportunity4);
		Opportunity opportunity5 = Opportunity.builder()
				.code("CH00005")
				.customer(customer5SGDHN)
				.contact(contact5)
				.name("Bán hàng cho " + customer5SGDHN.getName())
				.opportunityPhase(opportunityPhase6)
				.successRate(50)
				.expectedEndDate(LocalDate.of(2021, Month.JUNE, 8))
				.source(sourceThroughSeminalsAndTraining)
				.build();
		opportunity5.addToProductInfo(newSet( //
				ProductInfo.builder()
						.product(product5)
						.productCode(product5.getCode())
						.explanation(product5.getExplanation())
						.unit(product5.getUnit())
						.amount(300)
						.price(product5.getSellPrice())
						.discount(10)
						.vat(product5.getVat())
						.build()));
		opportunity5 = addNamedRepository(opportunityRepository, opportunity5);
		Opportunity opportunity6 = Opportunity.builder()
				.code("CH00006")
				.customer(customer6HoangGia)
				.contact(contact6)
				.name("Bán hàng cho " + customer6HoangGia.getName())
				.opportunityPhase(opportunityPhase5)
				.successRate(10)
				.expectedEndDate(LocalDate.of(2021, Month.JUNE, 8))
				.source(sourceThroughSeminalsAndTraining)
				.build();
		opportunity6.addToProductInfo(newSet( //
				ProductInfo.builder()
						.product(product6)
						.productCode(product6.getCode())
						.explanation(product6.getExplanation())
						.unit(product6.getUnit())
						.amount(1000)
						.price(product6.getSellPrice())
						.discount(10)
						.vat(product6.getVat())
						.build()));
		opportunity6 = addNamedRepository(opportunityRepository, opportunity6);
		Opportunity opportunity7 = Opportunity.builder()
				.code("CH00007")
				.customer(customer7AnhDuong)
				.contact(contact7)
				.name("Bán hàng cho " + customer7AnhDuong.getName())
				.opportunityPhase(opportunityPhase4)
				.successRate(50)
				.expectedEndDate(LocalDate.of(2021, Month.JUNE, 8))
				.source(sourceCustomerCome)
				.build();
		opportunity7.addToProductInfo(newSet( //
				ProductInfo.builder()
						.product(product7)
						.productCode(product7.getCode())
						.explanation(product7.getExplanation())
						.unit(product7.getUnit())
						.amount(20)
						.price(product7.getSellPrice())
						.discount(10)
						.vat(product7.getVat())
						.build()));
		opportunity7 = addNamedRepository(opportunityRepository, opportunity7);
		Opportunity opportunity8 = Opportunity.builder()
				.code("CH00008")
				.customer(customer8VHT)
				.contact(contact8)
				.name("Bán hàng cho " + customer8VHT.getName())
				.opportunityPhase(opportunityPhase4)
				.successRate(70)
				.expectedEndDate(LocalDate.of(2021, Month.JUNE, 8))
				.source(sourceCustomerCome)
				.build();
		opportunity8.addToProductInfo(newSet( //
				ProductInfo.builder()
						.product(product8)
						.productCode(product8.getCode())
						.explanation(product8.getExplanation())
						.unit(product8.getUnit())
						.amount(20)
						.price(product8.getSellPrice())
						.discount(10)
						.vat(product8.getVat())
						.build()));
		opportunity8 = addNamedRepository(opportunityRepository, opportunity8);
		Opportunity opportunity9 = Opportunity.builder()
				.code("CH00009")
				.customer(customer9FTech)
				.contact(contact9)
				.name("Bán hàng cho " + customer9FTech.getName())
				.opportunityPhase(opportunityPhase4)
				.successRate(70)
				.expectedEndDate(LocalDate.of(2021, Month.JULY, 9))
				.source(sourceCustomerCome)
				.build();
		opportunity9.addToProductInfo(newSet( //
				ProductInfo.builder()
						.product(product9)
						.productCode(product9.getCode())
						.explanation(product9.getExplanation())
						.unit(product9.getUnit())
						.amount(2)
						.price(product9.getSellPrice())
						.discount(0)
						.vat(product9.getVat())
						.build()));
		opportunity9 = addNamedRepository(opportunityRepository, opportunity9);
		Opportunity opportunity10 = Opportunity.builder()
				.code("CH00010")
				.customer(customer10PhanAnh)
				.contact(contact10)
				.name("Bán hàng cho " + customer10PhanAnh.getName())
				.opportunityPhase(opportunityPhase3)
				.successRate(70)
				.expectedEndDate(LocalDate.of(2021, Month.JUNE, 8))
				.source(sourceCustomerCome)
				.build();
		opportunity10.addToProductInfo(newSet( //
				ProductInfo.builder()
						.product(product10)
						.productCode(product10.getCode())
						.explanation(product10.getExplanation())
						.unit(product10.getUnit())
						.amount(1)
						.price(product10.getSellPrice())
						.discount(5)
						.vat(product10.getVat())
						.build()));
		opportunity10 = addNamedRepository(opportunityRepository, opportunity10);
		
		// Order
		Order order1 = Order.builder()
				.code("DH00009")
				.explanation("Đơn hàng bán cho " + customer4Eurodoor.getName())
				.orderDate(LocalDate.of(2021, Month.APRIL, 26))
				.customer(customer4Eurodoor)
				.contact(contact3)
				.opportunity(opportunity3)
				.liquidationDeadline(LocalDate.of(2021, Month.APRIL, 26))
				.deliveryDeadline(LocalDate.of(2021, Month.APRIL, 26))
				.receivedMoney(1L)
				.build();
		order1.addToProductInfo(newSet(
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
				));
		order1 = addCodedRepository(orderRepository, order1);
		
		// Invoice
		Invoice invoice1 = Invoice.builder()
				.code("DN0000001")
				.customer(customer4Eurodoor)
				.address("Số nhà 38, đường Bình Thới, Phường 12, Quận 10, Hồ Chí Minh, Việt Nam")
				.bankAccount("TNHH Eurodoor")
				.bank("Pro VN Bank")
				.taxCode("0185514943")
				.buyer(contact3)
				.receiverName("Min")
				.receiverEmail("Minn@gmail.com")
				.receiverPhone("120120129")
				.order(order1)
				.build();
		invoice1.addToProductInfo(newSet(
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
				));
		invoice1 = addCodedRepository(invoiceRepository, invoice1);
		
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
            admin.setUsername("admin123");
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
            member.setUsername("member1");
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
				.name("Quần áo")
				.build());
			ProductType productType2 = addNamedRepository(productTypeRepository, ProductType.builder()
					.code("LH00002")
					.name("Áo")
					.productType(productType1)
					.build());
				ProductType productType3 = addNamedRepository(productTypeRepository, ProductType.builder()
						.code("LH00003")
						.name("Áo thun")
						.productType(productType2)
						.build());	
				ProductType productType4 = addNamedRepository(productTypeRepository, ProductType.builder()
						.code("LH00004")
						.name("Áo sơ mi cổ chữ V")
						.productType(productType2)
						.build());
				ProductType productType5 = addNamedRepository(productTypeRepository, ProductType.builder()
						.code("LH00005")
						.name("Áo khoác")
						.productType(productType2)
						.build());
			ProductType productType6 = addNamedRepository(productTypeRepository, ProductType.builder()
					.code("LH00006")
					.name("Váy")
					.productType(productType1)
					.build());
		ProductType productType7 = addNamedRepository(productTypeRepository, ProductType.builder()
				.code("LH00007")
				.name("Thức ăn")
				.build());
			ProductType productType8 = addNamedRepository(productTypeRepository, ProductType.builder()
					.code("LH00008")
					.name("Hoa quả")
					.productType(productType7)
					.build());
				ProductType productType9 = addNamedRepository(productTypeRepository, ProductType.builder()
						.code("LH00009")
						.name("Quả nho")
						.productType(productType8)
						.build());
					ProductType productType10 = addNamedRepository(productTypeRepository, ProductType.builder()
							.code("LH00010")
							.name("Nho bảng")
							.productType(productType9)
							.build());
						ProductType productType11 = addNamedRepository(productTypeRepository, ProductType.builder()
								.code("LH00011")
								.name("Nho Dominga")
								.productType(productType10)
								.build());
					ProductType productType12 = addNamedRepository(productTypeRepository, ProductType.builder()
							.code("LH00012")
							.name("Nho rượu")
							.productType(productType9)
							.build());
						ProductType productType13 = addNamedRepository(productTypeRepository, ProductType.builder()
								.code("LH00013")
								.name("Nho đen Muscat")
								.productType(productType12)
								.build());
		ProductType productType20 = addNamedRepository(productTypeRepository, ProductType.builder()
				.code("LH00020")
				.name("Đồ uống")
				.build());
		ProductType productType21 = addNamedRepository(productTypeRepository, ProductType.builder()
				.code("LH00021")
				.name("Thiết bị điện tử")
				.build());
			ProductType productType22 = addNamedRepository(productTypeRepository, ProductType.builder()
					.code("LH00022")
					.name("Dương cầm")
					.productType(productType21)
					.build());
			ProductType productType23 = addNamedRepository(productTypeRepository, ProductType.builder()
					.code("LH00023")
					.name("Máy tính bỏ túi")
					.productType(productType21)
					.build());
			ProductType productType24 = addNamedRepository(productTypeRepository, ProductType.builder()
					.code("LH00024")
					.name("Điện thoại thông minh")
					.productType(productType21)
					.build());
		productTypes = Arrays.asList(productType1, productType2, productType3, productType4, productType5, productType6,
				productType7, productType8, productType9, productType10, productType11, productType12, productType13,
				productType20, productType21, productType22, productType23, productType24);
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
	
	private <T extends BaseEntity<ID> & Named, ID extends Serializable> T addNamedRepository(
			NamedJpaRepository<T, ID> repository, T t) {
		return repository.findFirstByName(t.getName()).orElseGet(() -> repository.save(t));
	}

	private <T extends BaseEntity<ID> & Coded, ID extends Serializable> T addCodedRepository(
			CodedRepository<T, ID> repository, T t) {
		return repository.findFirstByCode(t.getCode()).orElseGet(() -> repository.save(t));
	}

	private <T extends BaseEntity<ID>, ID extends Serializable> T addRepository(
			JpaRepository<T, ID> repository, T t) {
		return repository.findById(t.getId()).orElseGet(() -> repository.save(t));
	}
	
	@SafeVarargs
	@Deprecated
	private final <T extends BaseEntity<ID>, ID extends Serializable> List<T> addRepositorys(
			JpaRepository<T, ID> repository, T... ts) {
		return Stream.of(ts)
				.map(t -> addRepository(repository, t))
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
	}

	private <T> T orNull(Supplier<T> t) {
		try {
			return t.get();
		} catch (Exception e) {
			return null;
		}
	}
	
	@SafeVarargs
	private final <T> Set<T> newSet(T... t) {
		return Stream.of(t).collect(Collectors.toSet());
	}
	
}
