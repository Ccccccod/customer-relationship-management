/**
 * 
 */
package capstone.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import capstone.entity.BaseEntity;
import capstone.model.IdAndName;
import capstone.model.Named;
import capstone.model.Repositoried;
import capstone.repository.NamedJpaRepository;

/**
 * Controller to get all names
 * @author Tuna
 *
 */
interface IReadNameController< //
	T extends BaseEntity<ID> & Named, //
	Repository extends JpaRepository<T, ID> & NamedJpaRepository<T, ID>, //
	ID extends Serializable //
> //
		extends Repositoried<Repository> {

	@GetMapping("/name")
	default ResponseEntity<?> getAllName() {
		List<IdAndName<ID>> idAndNames = this.getRepository().findIdNameAllBy();
		return ResponseEntity.ok(idAndNames);
	}
	
}
