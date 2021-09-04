/**
 * 
 */
package capstone.service;

import org.springframework.stereotype.Service;

import capstone.dto.request.RoleDto;
import capstone.entity.Role;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.RoleRepository;

/**
 * RoleService
 * @author DELL
 * @author tuna
 */
@Service
public class RoleService extends AbstractService<RoleDto, RoleDto, Role, Role, RoleRepository, Long> {

	@Override
	protected Class<Role> entityClass() {
		return Role.class;
	}

	@Override
	protected Role entityToResponse(Role entity) {
		return entity;
	}

	@Override
	protected Role createDtoToEntity(RoleDto dto, Role entity) throws ResourceNotFoundException {
		return entity.toBuilder()
		.name(dto.getName())
		.description(dto.getDescription())
		.permissionFunctionActions(permissionFunctionActionService.getEntitiesById(dto.getPermissionFunctionActionIds()))
		.build();
	}

	@Override
	protected Role updateDtoToEntity(RoleDto updateDto, Role entity) throws ResourceNotFoundException {
		return this.createDtoToEntity(updateDto, entity);
	}

}
