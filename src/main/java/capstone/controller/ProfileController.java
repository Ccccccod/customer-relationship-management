/**
 * 
 */
package capstone.controller;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.UserUpdateDto;
import capstone.dto.response.UserResponse;
import capstone.entity.Role;
import capstone.entity.User;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.RoleRepository;
import capstone.repository.UserRepository;
import capstone.service.AbstractService;
import capstone.service.UserService;

/**
 * ProfileController
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/profile")
public class ProfileController {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<UserResponse> getProfile() throws ResourceNotFoundException {
		User currentUser = userService.getCurrentUser();
		if (Objects.isNull(currentUser))
			throw new ResourceNotFoundException("Can not find current user!");

		UserResponse response = entityToResponse(currentUser);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping
	public ResponseEntity<UserResponse> updateProfile(@RequestBody UserUpdateDto dto) throws ResourceNotFoundException {
		User currentUser = userService.getCurrentUser();
		if (Objects.isNull(currentUser))
			throw new ResourceNotFoundException("Can not find current user!");
		
		Long id = currentUser.getId();
		logger.debug("update() of id#{} with body {}", id, dto);
		
		currentUser = updateEntity(dto, currentUser);
		userRepository.save(currentUser);
		logger.debug("updated enitity: {}", currentUser);
		
		UserResponse response = entityToResponse(currentUser);
		return ResponseEntity.ok(response);
	}

	protected User updateEntity(UserUpdateDto updateDto, User entity) throws ResourceNotFoundException {
		return entity.toBuilder() //
				.username(updateDto.getUsername()) //
				.roles(AbstractService.findEntitiesByIds(roleRepository, updateDto.getRoleIds(), Role.class)) //
				.lastName(updateDto.getLastName()) //
				.name(updateDto.getName()) //
				.phone(updateDto.getPhone()) //
				.dateOfBirth(updateDto.getDateOfBirth()) //
				.gender(updateDto.getGender()) //
				.address(updateDto.getAddress()) //
				.build();
	}
	
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
//				.gender(user.getGender())
				.address(user.getAddress())
				.build();
	}

}
