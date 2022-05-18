/**
 * 
 */
package capstone.service;

import org.springframework.stereotype.Service;

import capstone.entity.MaritalStatus;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.MaritalStatusRepository;
import capstone.service.iservice.INamedService;

/**
 * MaritalStatus
 * @author DELL
 * @author tuna
 */
@Service
public class MaritalStatusService extends
		AbstractService<MaritalStatus, MaritalStatus, MaritalStatus, MaritalStatus, MaritalStatusRepository, Long>
		implements INamedService<MaritalStatus, MaritalStatusRepository, Long> {

	@Override
	protected Class<MaritalStatus> entityClass() {
		return MaritalStatus.class;
	}

	@Override
	protected MaritalStatus entityToResponse(MaritalStatus entity) {
		return entity;
	}

	@Override
	protected MaritalStatus createDtoToEntity(MaritalStatus d, MaritalStatus entity)
			throws ResourceNotFoundException {
		return entity.toBuilder()
				.name(d.getName())
				.build();
	}

	@Override
	protected MaritalStatus updateDtoToEntity(MaritalStatus updateDto, MaritalStatus entity)
			throws ResourceNotFoundException {
	    return this.createDtoToEntity(updateDto, entity);

	}

}
