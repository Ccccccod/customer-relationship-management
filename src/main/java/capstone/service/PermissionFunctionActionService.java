/**
 * 
 */
package capstone.service;

import org.springframework.stereotype.Service;

import capstone.entity.PermissionFunctionAction;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.PermissionFunctionActionRepository;

/**
 * PermissionFunctionActionService
 * @author Tuna
 */
@Service
public class PermissionFunctionActionService extends
		AbstractService<PermissionFunctionAction, PermissionFunctionAction, PermissionFunctionAction, PermissionFunctionAction, PermissionFunctionActionRepository, Long> {

	@Override
	protected Class<PermissionFunctionAction> entityClass() {
		return PermissionFunctionAction.class;
	}

	@Override
	protected PermissionFunctionAction entityToResponse(PermissionFunctionAction entity) {
		return entity;
	}

	@Override
	protected PermissionFunctionAction createDtoToEntity(PermissionFunctionAction createDto,
			PermissionFunctionAction entity) throws ResourceNotFoundException {
		throw new UnsupportedOperationException("Permissions can't not be created / updated");
	}

	@Override
	protected PermissionFunctionAction updateDtoToEntity(PermissionFunctionAction updateDto,
			PermissionFunctionAction entity) throws ResourceNotFoundException {
		throw new UnsupportedOperationException("Permissions can't not be created / updated");
	}

}
