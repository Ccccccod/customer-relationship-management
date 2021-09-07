/**
 * 
 */
package capstone.service;

import org.springframework.stereotype.Service;

import capstone.entity.District;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.DistrictRepository;
import capstone.service.iservice.INamedService;

/**
 * DistrictService
 * @author Tuna
 */
@Service
public class DistrictService extends AbstractService<District, District, District, District, DistrictRepository, Long>
		implements INamedService<District, DistrictRepository, Long> {

	@Override
	protected Class<District> entityClass() {
		return District.class;
	}

	@Override
	protected District entityToResponse(District entity) {
		return entity;
	}

	@Override
	protected District createDtoToEntity(District createDto, District entity) throws ResourceNotFoundException {
		throw new UnsupportedOperationException(entityClass().getName() + " can't not be created");
	}

	@Override
	protected District updateDtoToEntity(District updateDto, District entity) throws ResourceNotFoundException {
		throw new UnsupportedOperationException(entityClass().getName() + " can't not be updated");
	}

}
