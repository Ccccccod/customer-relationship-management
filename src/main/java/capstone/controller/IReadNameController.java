/**
 * 
 */
package capstone.controller;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import capstone.entity.BaseEntity;
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
	default ResponseEntity<List<Map<String,Object>>> getAllName() {
//		List<Map<String,Object>> ts = getRepository().findAll().stream().map(t -> {
//			Map<String,Object> map = new LinkedHashMap<String, Object>();
//			map.put("id", t.getId());
//			map.put("name", t.getName());
//			return map;
//		}).collect(Collectors.toList());
		List<Map<String, Object>> idAndNames = getRepository().findIdNameAllBy().stream()
				.map(t -> {
					Map<String,Object> map = new LinkedHashMap<String, Object>();
					map.put("id", t.getId());
					map.put("name", t.getName());
					return map;
				}).collect(Collectors.toList());
		return ResponseEntity.ok(idAndNames);
	}
	
}
