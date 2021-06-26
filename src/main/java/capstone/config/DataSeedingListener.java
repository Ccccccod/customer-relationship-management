/**
 * 
 */
package capstone.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import capstone.entity.Role;
import capstone.entity.Source;
import capstone.entity.User;
import capstone.repository.RoleRepository;
import capstone.repository.SourceRepository;
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
        Source source = sourceRepository.save(new Source("Source"));
        System.out.println(source);
	}

}
