/**
 * 
 */
package capstone.service;

import org.springframework.stereotype.Service;

import capstone.entity.Country;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.CountryRepository;
import capstone.service.iservice.INamedService;

/**
 * CountryService
 * @author Tuna
 */
@Service
public class CountryService extends AbstractService<Country, Country, Country, Country, CountryRepository, Long> 
		implements INamedService<Country, CountryRepository, Long> {

	@Override
	protected Class<Country> entityClass() {
		return Country.class;
	}

	@Override
	protected Country entityToResponse(Country entity) {
		return entity;
	}

	@Override
	protected Country createDtoToEntity(Country createDto, Country entity) throws ResourceNotFoundException {
		throw new UnsupportedOperationException(entityClass().getName() + " can't not be created");
	}

	@Override
	protected Country updateDtoToEntity(Country updateDto, Country entity) throws ResourceNotFoundException {
		throw new UnsupportedOperationException(entityClass().getName() + " can't not be updated");
	}

}
