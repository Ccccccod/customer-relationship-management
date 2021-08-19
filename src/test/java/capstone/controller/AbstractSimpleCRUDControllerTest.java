/**
 * 
 */
package capstone.controller;

import java.io.Serializable;

import org.junit.Before;
import org.springframework.data.jpa.repository.JpaRepository;

import capstone.entity.BaseEntity;

/**
 * @author Tuna
 *
 */
abstract class AbstractSimpleCRUDControllerTest<T extends BaseEntity<ID>, //
		Repository extends JpaRepository<T, ID>, //
		Controller extends AbstractSimpleCRUDController<T, Repository, ID>, //
		ID extends Serializable
> extends AbstractCRUDControllerTest<T, T, T, T, Repository, Controller, ID> {

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}
	
	@Override
	protected T createResource() {
		return resource();
	}

}
