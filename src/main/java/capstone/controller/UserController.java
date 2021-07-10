/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.UserDto;
import capstone.dto.request.UserUpdateDto;
import capstone.dto.response.UserResponse;
import capstone.entity.Role;
import capstone.entity.User;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.RoleRepository;
import capstone.service.AbstractService;

/**
 * User Controller
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/user")
public class UserController extends AbstractCRUDController<UserDto, UserUpdateDto, UserResponse, User, Long> {
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected UserResponse entityToResponse(User user) {
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
	protected User dtoToEntity(UserDto dto) throws ResourceNotFoundException {
		return User.builder()
				.id(dto.getId())
				.name(dto.getUsername())
				.email(dto.getEmail())
				.password(passwordEncoder.encode(dto.getPassword()))
				.roles(AbstractService.findEntitiesByIds(roleRepository, dto.getRoleIds(), Role.class))
				.build();
	}

	@Override
	protected void updateEntity(UserUpdateDto updateDto, User entity) throws ResourceNotFoundException {
		entity.setName(updateDto.getUsername());
		entity.setEmail(updateDto.getEmail());
		entity.setRoles(AbstractService.findEntitiesByIds(roleRepository, updateDto.getRoleIds(), Role.class));
	}

}
