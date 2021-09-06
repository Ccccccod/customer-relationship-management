/**
 * 
 */
package capstone.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import capstone.entity.PermissionFunctionAction;
import capstone.exception.ResourceNotFoundException;
import capstone.model.IdAndName;
import capstone.repository.PermissionFunctionActionRepository;
import capstone.service.iservice.IReadNameService;

/**
 * PermissionFunctionActionService
 * @author Tuna
 */
@Service
public class PermissionFunctionActionService extends
		AbstractService<PermissionFunctionAction, PermissionFunctionAction, PermissionFunctionAction, PermissionFunctionAction, PermissionFunctionActionRepository, Long>
		implements IReadNameService{

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

	@Override
	public List<?> getAllName() throws ResourceNotFoundException {
		List<IdAndName<Long>> list = getAllEntities().stream().map(e -> new IdAndName<Long>() {
			@Override
			public Long getId() {
				return e.getId();
			}
			@Override
			public void setId(Long id) {
			}
			@Override
			public String getName() {
				return e.getViName();
			}
		}).collect(Collectors.toList());
		return list;
	}

}
