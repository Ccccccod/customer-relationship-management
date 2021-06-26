/**
 * 
 */
package capstone.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import capstone.entity.BaseEntity;
import capstone.entity.Identifiable;

/**
 * Simple CRUD Controller. DTO, model, entity are the same object T
 * @author Tuna
 *
 * @param <T> Entity to CRUD
 * @param <ID> ID of &ltT&gt
 */
@RequestMapping("/default")
public abstract class SimpleCRUDController<T extends BaseEntity<ID> & Identifiable<ID>, ID extends Serializable>
		implements ISimpleCRUDController<T, ID> {
	
	@Autowired
	private JpaRepository<T, ID> repository;
	
	@Override
	public JpaRepository<T, ID> getRepository() {
		return this.repository;
	}

}
