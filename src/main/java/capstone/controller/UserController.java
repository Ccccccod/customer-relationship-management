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
import capstone.repository.UserRepository;
import capstone.service.AbstractService;

/**
 * User Controller
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/user")
public class UserController extends AbstractCRUDController<UserDto, UserUpdateDto, UserResponse, User, UserRepository, Long> {
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected UserResponse entityToResponse(User user) {
		return UserResponse.builder()
				.id(user.getId())
				.username(user.getUsername())
				.email(user.getEmail())
				.roles(user.getRoles())
				.createdAt(user.getCreatedAt())
				.createdBy(user.getCreatedBy())
				.updatedAt(user.getUpdatedAt())
				.updatedBy(user.getUpdatedBy())
				
				.lastName(user.getLastName())
				.name(user.getName())
				.phone(user.getPhone())
				.dateOfBirth(user.getDateOfBirth())
				.gender(user.getGender())
				.address(user.getAddress())
				.build();
	}

	@Override
	protected User dtoToEntity(UserDto dto, User user) throws ResourceNotFoundException {
		return user.toBuilder()
				.id(dto.getId())
				.username(dto.getUsername())
				.email(dto.getEmail())
				.password(passwordEncoder.encode(dto.getPassword()))
				.roles(AbstractService.findEntitiesByIds(roleRepository, dto.getRoleIds(), Role.class))
				.lastName(dto.getLastName())
				.name(dto.getName())
				.phone(dto.getPhone())
				.dateOfBirth(dto.getDateOfBirth())
				.gender(dto.getGender())
				.address(dto.getAddress())
				.build();
	}

	@Override
	protected User updateEntity(UserUpdateDto updateDto, User entity) throws ResourceNotFoundException {
		return entity.toBuilder() //
				.username(updateDto.getUsername()).email(updateDto.getEmail()) //
				.roles(AbstractService.findEntitiesByIds(roleRepository, updateDto.getRoleIds(), Role.class)) //
				.lastName(updateDto.getLastName()) //
				.name(updateDto.getName()) //
				.phone(updateDto.getPhone()) //
				.dateOfBirth(updateDto.getDateOfBirth()) //
				.gender(updateDto.getGender()) //
				.address(updateDto.getAddress()) //
				.build();
	}

	@Override
	protected Class<User> entityClass() {
		return User.class;
	}

}
