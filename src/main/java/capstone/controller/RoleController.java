/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.RoleDto;
import capstone.entity.Role;
import capstone.repository.RoleRepository;
import capstone.service.RoleService;

/**
 * Role Controller 
 * Vai trò Controller
 * @author Tuna
 */
@RestController
@RequestMapping("/api/role")
public class RoleController extends CRUDController<RoleDto, RoleDto, Role, Role, RoleRepository, RoleService, Long>
		implements IReadNameController<Role, RoleService, Long> {

//	@Autowired
//	protected PermissionFunctionRepository permissionFunctionRepository;
//
//	@Autowired
//	protected PermissionActionRepository permissionActionRepository;
//
//	@Autowired
//	protected PermissionFunctionActionRepository permissionFunctionActionRepository;
//
//	@GetMapping({ "/{id}/permissions", "/{id}/permissions/" })
//	public ResponseEntity<List<PermissionFunctionResponse>> getPermissions(@PathVariable Long id)
//			throws ResourceNotFoundException {
//		Role role = this.repository.findById(id).orElseThrow(DtoUtils.resourceNotFoundExceptionSupplier(id, Role.class));
//		List<PermissionFunction> permissionFunctions = this.permissionFunctionRepository.findAll();
//		List<PermissionFunctionResponse> response = permissionFunctions.stream().map(pf -> {
//			String name = pf.getName();
//			List<PermissionActionResponse> permissionActionResponses = pf.getPermissionFunctionActions().stream()
//					.map(pfa -> {
//						String name1 = pfa.getPermissionAction().getName();
//						boolean allowed = role.hasPermission(pfa);
//						return new PermissionActionResponse(pfa.getId(), name1, allowed);
//					}).collect(Collectors.toList());
//			return new PermissionFunctionResponse(name, permissionActionResponses);
//		}).collect(Collectors.toList());
//		return ResponseEntity.ok(response);
//	}
//
//	@PutMapping({ "/{id}/permissions", "/{id}/permissions/" })
//	public void updatePermissions(@PathVariable Long id, @RequestBody PermissionUpdateDto permissionUpdateDto)
//			throws ResourceNotFoundException {
//		Role role = this.repository.findById(id).orElseThrow(DtoUtils.resourceNotFoundExceptionSupplier(id, Role.class));
//		role.setPermissionFunctionActions(AbstractService.findEntitiesByIds(this.permissionFunctionActionRepository,
//				permissionUpdateDto.getPermissionFunctionActionIds(), PermissionFunctionAction.class));
//		this.repository.save(role);
//	}

	@Autowired
	private RoleService roleService;

	@Override
	public RoleService getService() {
		return roleService;
	}

}
