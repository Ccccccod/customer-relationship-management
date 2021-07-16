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
import capstone.dto.response.UserResponse;
import capstone.entity.Role;
import capstone.entity.User;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.RoleRepository;
import capstone.repository.UserRepository;
import capstone.security.service.UserDetailsImpl;

/**
 * User Service
 * @author Tuna
 *
 */
@Service
public class UserService extends AbstractService implements IEntityToResponseService<UserResponse, User, Long>, IDtoToEntityService<UserDto, User, Long> {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

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

	@Override
	public UserResponse entityToResponse(User user) {
		return UserResponse.builder()
				.id(user.getId())
				.username(user.getName())
				.email(user.getEmail())
				.roles(user.getRoles())
				.createdAt(user.getCreatedAt())
				.createdBy(user.getCreatedBy())
				.updatedAt(user.getUpdatedAt())
				.updatedBy(user.getUpdatedBy())
				.build();
	}

	@Override
	public User dtoToEntity(UserDto dto) throws ResourceNotFoundException {
		return User.builder()
				.id(dto.getId())
				.name(dto.getUsername())
				.email(dto.getEmail())
				.password(passwordEncoder.encode(dto.getPassword()))
				.roles(AbstractService.findEntitiesByIds(roleRepository, dto.getRoleIds(), Role.class))
				.build();
	}
	
}
