/**
 * 
 */
package capstone.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import capstone.entity.User;
import capstone.service.UserService;

/**
 * PersistenceConfig
 * @author Tuna
 */
@Configuration
public class PersistenceConfig {
	
	@Bean
	public AuditorAware<User> auditorProvider() {
		return new AuditorAware<User>() {
			@Override
			public Optional<User> getCurrentAuditor() {
				User user = userService.getCurrentUser();
				return Optional.ofNullable(user);
			}
		};
	}
	
	@Autowired
	private UserService userService;

}
