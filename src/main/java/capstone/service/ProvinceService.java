/**
 * 
 */
package capstone.service;

import java.util.List;

import org.springframework.stereotype.Service;

import capstone.entity.Country;
import capstone.entity.Province;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.ProvinceRepository;
import capstone.service.iservice.INamedService;

/**
 * ProvinceService
 * @author Tuna
 */
@Service
public class ProvinceService extends AbstractService<Province, Province, Province, Province, ProvinceRepository, Long>
		implements INamedService<Province, ProvinceRepository, Long> {

	@Override
	protected Class<Province> entityClass() {
		return Province.class;
	}

	@Override
	protected Province entityToResponse(Province entity) {
		return entity;
	}

	@Override
	protected Province createDtoToEntity(Province createDto, Province entity) throws ResourceNotFoundException {
		throw new UnsupportedOperationException(entityClass().getName() + " can't not be created");
	}

	@Override
	protected Province updateDtoToEntity(Province updateDto, Province entity) throws ResourceNotFoundException {
		throw new UnsupportedOperationException(entityClass().getName() + " can't not be updated");
	}
	
	public List<Province> findByCountryId(Long countryId) throws ResourceNotFoundException {
		Country country = countryService.getEntityById(countryId);
		return repository.findByCountry(country);
	}

}
