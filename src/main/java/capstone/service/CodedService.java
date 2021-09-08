/**
 * 
 */
package capstone.service;

import java.io.Serializable;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import capstone.exception.ResourceExistedException;
import capstone.exception.ResourceNotFoundException;
import capstone.model.Coded;
import capstone.model.Identifiable;
import capstone.utils.RepositoryUtils;

/**
 * CodedService
 * @author Tuna
 */
public abstract class CodedService< //
		CreateDto extends Object & Identifiable<ID>, //
		UpdateDto extends Object & Identifiable<ID>, //
		Response extends Object & Identifiable<ID>, //
		Entity extends Object & Identifiable<ID> & Coded, //
		Repository extends JpaRepository<Entity, ID>, //
		ID extends Serializable //
> extends AbstractService<CreateDto, UpdateDto, Response, Entity, Repository, ID> {
	
	@Autowired
	protected RandomStringGenerator randomStringGenerator;
	
	@Override
	public Response create(CreateDto dto) throws ResourceNotFoundException, ResourceExistedException,
			IllegalArgumentException, IllegalAccessException, InstantiationException {
		this.logger.debug("create() with body {} of type {}", dto, dto.getClass());
        
		Entity entity = this.createDtoToEntity(dto, entityClass().newInstance());
		entity.setId(null);
		
		String code = randomStringGenerator.generate(10);
		entity.setCode(code);
		
		RepositoryUtils.checkExistedFields(entity, this.repository, entityClass());
		entity = this.repository.save(entity);
		
		Response response = this.entityToResponse(entity);
		return response;
	}

}
