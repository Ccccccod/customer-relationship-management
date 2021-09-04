/**
 * 
 */
package capstone.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import capstone.entity.User;
import capstone.repository.UserRepository;
import capstone.security.service.UserDetailsImpl;

/**
 * User Service
 * @author tuna
 */
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	/**
	 * Get current logged in user
	 * @return
	 */
	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (Objects.isNull(authentication))
			return null;
		Object principal = authentication.getPrincipal();
		if (principal instanceof UserDetailsImpl) {
			UserDetailsImpl userDetails = (UserDetailsImpl) principal;
			Long id = userDetails.getId();
			if (Objects.isNull(id))
				return null;
			return userRepository.findById(id).orElse(null);
		} else if (principal instanceof org.springframework.security.core.userdetails.User) {
			UserDetails userDetails = (org.springframework.security.core.userdetails.User) principal;
			String userName = userDetails.getUsername();
			if (Objects.isNull(userName))
				return userRepository.findFirstByName(userName).orElse(null);
			return null;
		}
		return null;
	}
	
}
