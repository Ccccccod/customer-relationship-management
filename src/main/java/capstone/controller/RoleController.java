/**
 * 
 */
package capstone.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.PermissionUpdateDto;
import capstone.dto.request.RoleDto;
import capstone.dto.response.PermissionActionResponse;
import capstone.dto.response.PermissionFunctionResponse;
import capstone.entity.PermissionFunction;
import capstone.entity.PermissionFunctionAction;
import capstone.entity.Role;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.PermissionActionRepository;
import capstone.repository.PermissionFunctionActionRepository;
import capstone.repository.PermissionFunctionRepository;
import capstone.repository.RoleRepository;
import capstone.service.AbstractService;
import capstone.utils.DtoUtils;

/**
 * Role Controller
 * Vai trò Controller
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/role")
public class RoleController extends AbstractDtoEntityController<RoleDto, Role, RoleRepository, Long>
		implements IReadNameController<Role, RoleRepository, Long> {
	
	@Autowired
	protected PermissionFunctionRepository permissionFunctionRepository;

	@Autowired
	protected PermissionActionRepository permissionActionRepository;

	@Autowired
	protected PermissionFunctionActionRepository permissionFunctionActionRepository;

	@Override
	protected Role dtoToEntity(RoleDto dto, Role role) throws ResourceNotFoundException {
		return role.toBuilder()
				.name(dto.getName())
				.description(dto.getDescription())
				.permissionFunctionActions(AbstractService.findEntitiesByIds(permissionFunctionActionRepository,
						dto.getPermissionFunctionActionIds(), PermissionFunctionAction.class))
				.build();
	}

	@GetMapping({ "/{id}/permissions", "/{id}/permissions/" })
	public ResponseEntity<List<PermissionFunctionResponse>> getPermissions(@PathVariable Long id)
			throws ResourceNotFoundException {
		Role role = this.repository.findById(id).orElseThrow(DtoUtils.resourceNotFoundExceptionSupplier(id));
		List<PermissionFunction> permissionFunctions = this.permissionFunctionRepository.findAll();
		List<PermissionFunctionResponse> response = permissionFunctions.stream().map(pf -> {
			String name = pf.getName();
			List<PermissionActionResponse> permissionActionResponses = pf.getPermissionFunctionActions().stream()
					.map(pfa -> {
						String name1 = pfa.getPermissionAction().getName();
						boolean allowed = role.hasPermission(pfa);
						return new PermissionActionResponse(pfa.getId(), name1, allowed);
					}).collect(Collectors.toList());
			return new PermissionFunctionResponse(name, permissionActionResponses);
		}).collect(Collectors.toList());
		return ResponseEntity.ok(response);
	}

	@PutMapping({ "/{id}/permissions", "/{id}/permissions/" })
	public void updatePermissions(@PathVariable Long id, @RequestBody PermissionUpdateDto permissionUpdateDto)
			throws ResourceNotFoundException {
		Role role = this.repository.findById(id).orElseThrow(DtoUtils.resourceNotFoundExceptionSupplier(id));
		role.setPermissionFunctionActions(AbstractService.findEntitiesByIds(this.permissionFunctionActionRepository,
				permissionUpdateDto.getPermissionFunctionActionIds(), PermissionFunctionAction.class));
		this.repository.save(role);
	}

	@Override
	protected Class<Role> entityClass() {
		return Role.class;
	}

}
