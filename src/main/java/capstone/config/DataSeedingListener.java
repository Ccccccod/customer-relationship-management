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

import capstone.dao.RoleRepository;
import capstone.dao.UserRepository;
import capstone.entity.Role;
import capstone.entity.User;
import capstone.utils.EncryptedPasswordUtils;

/**
 * @author Tuna
 *
 */
@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

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
        if (!userRepository.findByEmail("admin@gmail.com").isPresent()) {
            User admin = new User();
            admin.setName("admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(EncryptedPasswordUtils.encrytePassword("123456"));
            
            Set<Role> roles = new HashSet<Role>();
            roles.add(roleModerator);
            roles.add(roleAdmin);
            roles.add(roleMember);
            admin.setRoles(roles);
            
            userRepository.save(admin);
        }

        // Member account
        if (!userRepository.findByEmail("member@gmail.com").isPresent()) {
            User member = new User();
            member.setName("member");
            member.setEmail("member@gmail.com");
            member.setPassword(EncryptedPasswordUtils.encrytePassword("123456"));

            Set<Role> roles = new HashSet<Role>();
            roles.add(roleMember);
            member.setRoles(roles);
            
            userRepository.save(member);
        }
        
        userRepository.findAll().forEach(System.out::println);
        roleRepository.findAll().forEach(System.out::println);
	}

}
