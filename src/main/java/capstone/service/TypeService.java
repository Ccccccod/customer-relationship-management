/**
 * 
 */
package capstone.service;

import org.springframework.stereotype.Service;

import capstone.entity.Type;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.TypeRepository;
import capstone.service.iservice.INamedService;

/**
 * TypeService
 * @author DELL
 * @author tuna
 */
@Service
public class TypeService extends AbstractService<Type, Type, Type, Type, TypeRepository, Long> 
implements INamedService<Type, TypeRepository, Long>{

	@Override
	protected Class<Type> entityClass() {
		return Type.class;
	}

	@Override
	protected Type entityToResponse(Type entity) {
		return entity;
	}

	@Override
	protected Type createDtoToEntity(Type d, Type entity) throws ResourceNotFoundException {
		return entity.toBuilder()
				.name(d.getName())
				.build();
	}

	@Override
	protected Type updateDtoToEntity(Type updateDto, Type entity) throws ResourceNotFoundException {
		return this.createDtoToEntity(updateDto, entity);

	}

}
