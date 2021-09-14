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

import capstone.dto.request.UserDto;
import capstone.dto.request.UserUpdateDto;
import capstone.entity.User;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.UserRepository;
import capstone.security.service.UserDetailsImpl;

/**
 * User Service
 * @author tuna
 */
@Service
public class UserService extends AbstractService<UserDto, UserUpdateDto, User, User, UserRepository, Long> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	protected Class<User> entityClass() {
		return User.class;
	}

	@Override
	protected User entityToResponse(User entity) {
		return entity;
	}

	@Override
	protected User createDtoToEntity(UserDto d, User entity) throws ResourceNotFoundException {
		return entity.toBuilder()
				.id(d.getId())
				.username(d.getUsername())
				.password(passwordEncoder.encode(d.getPassword()))
				.email(d.getEmail())
				.roles(roleService.getEntitiesById(d.getRoleIds()))
				.lastName(d.getLastName())
				.name(d.getName())
				.phone(d.getPhone())
				.dateOfBirth(d.getDateOfBirth())
				.gender(genderService.getEntityById(d.getGenderId()))
				.address(d.getAddress())
				.build();
	}

	@Override
	protected User updateDtoToEntity(UserUpdateDto d, User entity) throws ResourceNotFoundException {
		return entity.toBuilder()
				.id(d.getId())
				.username(d.getUsername())
				.password(d.getPassword() == null ? entity.getPassword() : passwordEncoder.encode(d.getPassword()))
				.email(d.getEmail())
				.roles(roleService.getEntitiesById(d.getRoleIds()))
				.lastName(d.getLastName())
				.name(d.getName())
				.phone(d.getPhone())
				.dateOfBirth(d.getDateOfBirth())
				.gender(genderService.getEntityById(d.getGenderId()))
				.address(d.getAddress())
				.build();
	}
	
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
				return userRepository.findFirstByUsername(userName).orElse(null);
			return null;
		}
		return null;
	}
	
}
