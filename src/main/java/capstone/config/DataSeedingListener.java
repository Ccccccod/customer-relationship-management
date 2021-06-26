/**
 * 
 */
package capstone.config;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import capstone.entity.Career;
import capstone.entity.Classification;
import capstone.entity.Field;
import capstone.entity.NamedEntity;
import capstone.entity.Role;
import capstone.entity.Source;
import capstone.entity.Type;
import capstone.entity.User;
import capstone.repository.CareerRepository;
import capstone.repository.ClassificationRepository;
import capstone.repository.FieldRepository;
import capstone.repository.NamedRepository;
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
    
    static final private String PASSWORD = "12345678"; 

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		
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
        
        // Source
        addNamedRepository(sourceRepository, new Source("Khách hàng hoặc đối tác giới thiệu"));
        addNamedRepository(sourceRepository, new Source("Nhân viên kinh doanh tự tìm kiếm"));
        addNamedRepository(sourceRepository, new Source("Thông qua sự kiện, hội thảo, tập huấn"));
        addNamedRepository(sourceRepository, new Source("Khách hàng tự tìm đến"));
        addNamedRepository(sourceRepository, new Source("Khác"));
        
        // Classification
        addNamedRepository(classificationRepository, new Classification("Khách hàng bán lẻ"));
        addNamedRepository(classificationRepository, new Classification("Khách hàng dự án"));
        
        // Field
        addNamedRepository(fieldRepository, new Field("Thương mại"));
        addNamedRepository(fieldRepository, new Field("Dịch vụ"));
        addNamedRepository(fieldRepository, new Field("Sản xuất"));
        addNamedRepository(fieldRepository, new Field("Xây lắp"));
        addNamedRepository(fieldRepository, new Field("Công nghiệp nhẹ"));
        
        // Type
        addNamedRepository(typeRepository, new Type("Doanh nghiệp"));
        addNamedRepository(typeRepository, new Type("Hộ cá thể"));
        addNamedRepository(typeRepository, new Type("Hành chính sự nghiệp"));
        addNamedRepository(typeRepository, new Type("Khác"));
        
        // Career
        addNamedRepository(careerRepository, new Career("Kinh doanh nhóm chính"));
        addNamedRepository(careerRepository, new Career("Kinh doanh thực phẩm"));
        addNamedRepository(careerRepository, new Career("Kinh doanh hóa chất"));
        addNamedRepository(careerRepository, new Career("Kinh doanh mỹ phẩm"));
        addNamedRepository(careerRepository, new Career("Kinh doanh ô tô, xe máy"));
        
	}
	
	private <T extends NamedEntity<ID>, ID extends Serializable> void addNamedRepository(NamedRepository<T, ID> repository, T t) {
        if (!repository.existsByName(t.getName())) {
        	repository.save(t);
        }
	}

}
