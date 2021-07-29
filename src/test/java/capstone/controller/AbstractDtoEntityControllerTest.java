/**
 * 
 */
package capstone.controller;

import java.io.Serializable;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.data.jpa.repository.JpaRepository;

import capstone.dto.request.BaseDto;
import capstone.entity.BaseEntity;

/**AbstractDtoEntityController
 * @author Tuna
 *
 */
abstract class AbstractDtoEntityControllerTest<Dto extends BaseDto<ID>, //
		Entity extends BaseEntity<ID>, //
		Repository extends JpaRepository<Entity, ID>, //
		Controller extends AbstractDtoEntityController<Dto, Entity, Repository, ID>, //
		ID extends Serializable //
>
extends AbstractCRUDControllerTest<Dto, Dto, Entity, Entity, Repository, Controller, ID> {

	@Override
	@BeforeEach
	protected void setUp() throws Exception {
		
		super.setUp();
	}
	
}
