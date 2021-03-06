/**
 * 
 */
package capstone.service;

import org.springframework.stereotype.Service;

import capstone.entity.Source;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.SourceRepository;
import capstone.service.iservice.INamedService;

/**
 * SourceService
 * @author DELL
 * @author tuna
 */
@Service
public class SourceService extends AbstractService<Source, Source, Source, Source, SourceRepository, Long>
implements INamedService<Source, SourceRepository, Long>{

	@Override
	protected Class<Source> entityClass() {
		return Source.class;
	}

	@Override
	protected Source entityToResponse(Source entity) {
		return entity;
	}

	@Override
	protected Source createDtoToEntity(Source d, Source entity) throws ResourceNotFoundException {
		return entity.toBuilder()
				.name(d.getName())
				.build();
	}

	@Override
	protected Source updateDtoToEntity(Source updateDto, Source entity) throws ResourceNotFoundException {
		return this.createDtoToEntity(updateDto, entity);

	}

}
