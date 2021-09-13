/**
 * 
 */
package capstone.service;

import java.io.Serializable;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import capstone.model.Coded;
import capstone.model.Identifiable;

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
	Entity saveEntity(Entity entity) {
		String code = randomStringGenerator.generate(10);
		entity.setCode(code);
		return super.saveEntity(entity);
	}

}
