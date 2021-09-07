/**
 * 
 */
package capstone.service;

import org.springframework.stereotype.Service;

import capstone.entity.Ward;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.WardRepository;
import capstone.service.iservice.INamedService;

/**
 * WardService
 * @author Tuna
 */
@Service
public class WardService extends AbstractService<Ward, Ward, Ward, Ward, WardRepository, Long>
		implements INamedService<Ward, WardRepository, Long> {

	@Override
	protected Class<Ward> entityClass() {
		return Ward.class;
	}

	@Override
	protected Ward entityToResponse(Ward entity) {
		return entity;
	}

	@Override
	protected Ward createDtoToEntity(Ward createDto, Ward entity) throws ResourceNotFoundException {
		throw new UnsupportedOperationException(entityClass().getName() + " can't not be created");
	}

	@Override
	protected Ward updateDtoToEntity(Ward updateDto, Ward entity) throws ResourceNotFoundException {
		throw new UnsupportedOperationException(entityClass().getName() + " can't not be updated");
	}

}
