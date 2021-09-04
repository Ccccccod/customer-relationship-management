/**
 * 
 */
package capstone.service;

import org.springframework.stereotype.Service;

import capstone.entity.Income;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.IncomeRepository;

/**
 * IncomeService
 * @author DELL
 * @author tuna
 */
@Service
public class IncomeService extends AbstractService<Income, Income, Income, Income, IncomeRepository, Long>  {

	@Override
	protected Class<Income> entityClass() {
		return Income.class;
	}

	@Override
	protected Income entityToResponse(Income entity) {
		return entity;
	}

	@Override
	protected Income createDtoToEntity(Income d, Income entity) throws ResourceNotFoundException {
		return entity.toBuilder()
				.name(d.getName())
				.build();
	}

	@Override
	protected Income updateDtoToEntity(Income updateDto, Income entity) throws ResourceNotFoundException {
		return this.createDtoToEntity(updateDto, entity);

	}

}
