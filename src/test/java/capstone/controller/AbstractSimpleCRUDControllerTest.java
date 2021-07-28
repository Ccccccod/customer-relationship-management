/**
 * 
 */
package capstone.controller;

import java.io.Serializable;

import org.junit.jupiter.api.BeforeEach;
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

	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();
	}

}
